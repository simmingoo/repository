package com.ur.tb.listed.report.web.service.Impl;

import com.sun.xml.messaging.saaj.packaging.mime.internet.MimeUtility;
import com.ur.tb.listed.model.AttachFileVO;
import com.ur.tb.listed.model.MailVO;
import com.ur.tb.listed.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service()
@Slf4j
public class MailServiceImpl  {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public void sendMail(MailVO mailVO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // use multipart (true)

            InternetAddress[] toAddress = CommonUtil.mailListToArray(mailVO.getToAddressList());
            InternetAddress[] ccAddress = CommonUtil.mailListToArray(mailVO.getCcAddressList());
            InternetAddress[] bccAddress = CommonUtil.mailListToArray(mailVO.getBccAddressList());

            mimeMessageHelper.setSubject(MimeUtility.encodeText(mailVO.getSubject(), "UTF-8", "B")); // Base64 encoding
            mimeMessageHelper.setText(mailVO.getContent(), mailVO.isUseHtmlYn());
            mimeMessageHelper.setFrom(new InternetAddress(mailVO.getFromAddress(), mailVO.getFromAddress(), "UTF-8"));
            mimeMessageHelper.setTo(toAddress);
            mimeMessageHelper.setCc(ccAddress);
            mimeMessageHelper.setBcc(bccAddress);

            if(!CollectionUtils.isEmpty(mailVO.getAttachFileList())) {
                for(AttachFileVO attachFileDto: mailVO.getAttachFileList()) {
                    FileSystemResource fileSystemResource = new FileSystemResource(new File(attachFileDto.getRealFileNm()));
                    mimeMessageHelper.addAttachment(MimeUtility.encodeText(attachFileDto.getAttachFileNm(), "UTF-8", "B"), fileSystemResource);
                }
            }
            javaMailSender.send(mimeMessage);
            log.info("MailServiceImpl.sendMail() :: SUCCESS");
        } catch (Exception e) {
            log.info("MailServiceImpl.sendMail() :: FAILED");
            e.printStackTrace();
        }

    }
}
