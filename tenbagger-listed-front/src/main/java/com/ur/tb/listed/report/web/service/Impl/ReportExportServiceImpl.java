package com.ur.tb.listed.report.web.service.Impl;

import com.convertapi.client.Config;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.Param;
import com.ur.tb.listed.model.AttachFileVO;
import com.ur.tb.listed.model.MailVO;
import com.ur.tb.listed.model.Stock;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service("ReportExportService")
@Slf4j
public class ReportExportServiceImpl implements ReportExportService {

    @Value("${tenbagger.front.url}")
    private String frontUrl;

    @Value("${tenbagger.report.temppath}")
    private String tempPath;

    @Value("${tenbagger.api.url}")
    private String apiUrl;

    @Value("${tenbagger.api.stock.detail.url}")
    private String stockDetailUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailServiceImpl mailService;

    @Override
    @Async
    public void makepdfbulk(String email, List<String> isuSrtCds) throws Exception {
        Date now = new Date();
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String emailId = email.substring(0 ,email.indexOf("@"));
        String makeDirPath = tempPath + "/" + df.format(now)+"_"+emailId;


        if(!new File(tempPath).exists()) {
            try {
                new File(tempPath).mkdir();
            } catch (Exception e) {
                log.error(CommonUtil.getPrintStackTrace(e));
            }
        }


        if(!new File(makeDirPath).exists()) {
            try {
                new File(makeDirPath).mkdir();
            } catch (Exception e) {
                log.error(CommonUtil.getPrintStackTrace(e));
            }
        }

        File rtvCsvFile = new File(
                makeDirPath+"/"
                        +df.format(now)+"_리포트결과.csv"
                );
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(rtvCsvFile,true));
            String csvData = "";
            csvData = "종목명, 종목코드, 사업자번호, 리포트링크";
            bw.write(csvData);
            bw.newLine();
            for(String isuSrtCd : isuSrtCds) {
                ResponseEntity<Stock> response = restTemplate.exchange(
                        apiUrl+stockDetailUrl
                        , HttpMethod.GET
                        ,null
                        ,new ParameterizedTypeReference<Stock>(){}
                        ,isuSrtCd
                );
                Stock stockDetail = response.getBody();
                csvData = stockDetail.getIsuKorNm()+","
                        +"'"+isuSrtCd+","
                        +"'"+stockDetail.getCorp().getBizno()+","
                        +frontUrl+"/report/stockReport?isuSrtCd="+isuSrtCd+"&bizNo="+stockDetail.getCorp().getBizno();
                bw.write(csvData);
                bw.newLine();
                if(checkReportFile(makeDirPath+"/"+stockDetail.getIsuKorNm()+"("+isuSrtCd+").pdf")) {

                try {
                    Config.setDefaultSecret("qin8mmv6k7Hj693p");
                    ConvertApi.convert("web", "pdf",
                            new Param("Url", frontUrl+"/report/stockReport?isuSrtCd="+isuSrtCd+"&bizNo="+stockDetail.getCorp().getBizno()),
                            new Param("FileName", stockDetail.getIsuKorNm()+"("+isuSrtCd+")"),
                            new Param("PageRange", "2-100"),
                            new Param("ConversionDelay", "25"),
                            new Param("ViewportWidth", "1180"),
                            new Param("ViewportHeight", "1900"),
                            new Param("PageSize", "a4"),
                            new Param("PageWidth", "210"),
                            new Param("PageHeight", "310"),
                            new Param("MarginTop", "0"),
                            new Param("MarginRight", "0"),
                            new Param("MarginBottom", "0"),
                            new Param("MarginLeft", "0")
                    ).get().saveFilesSync(Paths.get(makeDirPath));
                }catch (Exception e) {
                    log.info(CommonUtil.getPrintStackTrace(e));
                }
                }
            }
        } catch (Exception e) {
            System.out.println(CommonUtil.getPrintStackTrace(e));
        } finally {
            try {
                if(bw != null) {
                    bw.flush();
                    bw.close();
                }
            }catch (Exception e) {

            }
        }
        makeZip(Paths.get(makeDirPath), tempPath+"/"+df.format(now)+"_"+emailId+".zip");

        String mailSubject = "[개발팀] 리포트 추출 요청의 건_"+df.format(now).substring(0,8);
        String mailContent = "<p>안녕하세요.</p><br>" +
                "<p>개발팀 심민구 입니다.</p><br>" +
                "<p>요청하신 리포트 추출 완료하여 링크 전달 드리오니 아래 링크 클릭 부탁 드립니다.</p><br>" +
                "<p>이상입니다.</p><br>" +
                "<p>감사합니다.</p><br>"+
                "<a href='https://localhost:8888/convertreport/download/"+df.format(now)+"_"+emailId+"'>리포트 다운</a><br>";
        String fromAddress = "mksim@unicornranch.co.kr";
        String[] toAddressArr = {"mksim@unicornranch.co.kr",email};
        List<String> toAddressList = Arrays.asList(toAddressArr);
        List<String> ccAddressList = new ArrayList<>();
        List<String> bccAddressList = new ArrayList<>();
        List<AttachFileVO> atchFileList = new ArrayList<>();
//        List<AttachFileVO> atchFileList = new ArrayList<>(Arrays.asList(new AttachFileVO(makeDirPath+"/"+df.format(now)+"_"+emailId+".zip", df.format(now)+"_"+emailId+".zip")));

        mailService.sendMail(new MailVO(fromAddress, toAddressList, ccAddressList, bccAddressList, mailSubject, mailContent, true, atchFileList));
        deleteFolder(makeDirPath);
    }

    private boolean checkReportFile(String filePath) throws  Exception{
        boolean rtv = false;
        File file = new File(filePath);
        if(!file.exists()){
            rtv =  true;
        }
        return rtv;
    }

    private void makeZip(Path srcDir, String zipFileName) {
        try(ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName))) {

            Files.walkFileTree(srcDir, new SimpleFileVisitor<>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                    if (attributes.isSymbolicLink()) {
                        return FileVisitResult.CONTINUE;
                    }

                    try (FileInputStream fis = new FileInputStream(file.toFile())) {

                        Path targetFile = srcDir.relativize(file);
                        zos.putNextEntry(new ZipEntry(targetFile.toString()));

                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }
                        zos.closeEntry();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    log.info("Unable to zip : %s%n%s%n", file, exc);
                    return FileVisitResult.CONTINUE;
                }
            });

        }catch (Exception e) {
            log.error(CommonUtil.getPrintStackTrace(e));
        }
    }

    private void deleteFolder(String folerPath) {
        try {
            File folder = new File(folerPath);
            while (folder.exists()){
                File[] files = folder.listFiles();
                for(File file : files) {
                    file.delete();
                }
            }
            if(folder.list().length == 0 && folder.isDirectory()) {
                folder.delete();
            }
        } catch (Exception e) {
            log.error(CommonUtil.getPrintStackTrace(e));
        }
    }

}
