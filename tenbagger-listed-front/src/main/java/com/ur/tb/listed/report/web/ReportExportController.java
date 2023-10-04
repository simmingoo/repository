package com.ur.tb.listed.report.web;

import com.ur.tb.listed.report.web.service.Impl.ReportExportService;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/convertreport")
public class ReportExportController {

    @Value("${tenbagger.report.uploadpath}")
    private String uploadPath;

    @Value("${tenbagger.report.temppath}")
    private String tempPath;

    @Resource
    private ReportExportService reportExportService;

    @RequestMapping("/stockbulk")
    public String stockbulk(ModelMap model) {
        return "convertreport/stockbulk";
    }

    @RequestMapping(value = "/stockbulkAct", method = RequestMethod.POST)
    public String stockblukAct(@RequestParam("file") MultipartFile uploadFile,
                               HttpServletRequest request,
                               ModelMap model) throws Exception{
        model.addAttribute("result", true);
        model.addAttribute("resultMsg", "파일 생성 후 이메일로 전송 됩니다.");
        try {
            String email = request.getParameter("email");
            String origFileName = uploadFile.getOriginalFilename();
            String fileExt = origFileName.substring(origFileName.lastIndexOf(".")+1,origFileName.length());
            if(fileExt.equalsIgnoreCase("txt") || fileExt.equalsIgnoreCase("csv")) {
                if(!new File(uploadPath).exists()){
                    try {
                        new File(uploadPath).mkdir();
                    } catch (Exception e) {
                        System.out.println(CommonUtil.getPrintStackTrace(e));
                    }
                }
                String fileName = UUID.randomUUID().toString()+"."+fileExt;
                String filePath = uploadPath+"/"+fileName;
                uploadFile.transferTo(new File(filePath));
                List<String> isuSrtCds = Files.readAllLines(Paths.get(filePath));
                reportExportService.makepdfbulk(email,isuSrtCds);
            } else {
                model.addAttribute("result", false);
                model.addAttribute("resultMsg", "미 지원 확장자 입니다.");
                return "convertreport/stockbulkresult";
            }
        } catch (Exception e) {
            System.out.println(CommonUtil.getPrintStackTrace(e));
            model.addAttribute("result", false);
            model.addAttribute("resultMsg", e.getMessage());
        }
        return "convertreport/stockbulkresult";
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Object> download(@PathVariable("fileName") String fileName) {
        String path = tempPath+"/"+fileName+".zip";
        try {
            Path filePath = Paths.get(path);
            org.springframework.core.io.Resource resource = new InputStreamResource(Files.newInputStream(filePath));

            File file = new File(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());

            return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
        }catch (Exception e) {
            log.error(CommonUtil.getPrintStackTrace(e));
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
        }

    }

}
