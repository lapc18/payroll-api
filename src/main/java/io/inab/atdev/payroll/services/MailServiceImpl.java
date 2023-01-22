package io.inab.atdev.payroll.services;

import io.inab.atdev.payroll.core.interfaces.MailService;
import io.inab.atdev.payroll.core.models.ExceptionResponse;
import io.inab.atdev.payroll.core.models.MailDetails;
import io.inab.atdev.payroll.core.models.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Calendar;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public MailResponse sendEmail(MailDetails details) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(details.getFrom());
            message.setTo(details.getTo());
            message.setText(details.getBody());
            message.setSubject(details.getSubject());
            message.setSentDate(Calendar.getInstance().getTime());

            this.mailSender.send(message);
            return new MailResponse(details.getTo(), Calendar.getInstance().getTime());
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse();
            if(e instanceof MailSendException)
                exceptionResponse.setMessage("An error has occurred sending the e-mail. Please check the content and the stacktrace.");

            exceptionResponse.setCode("500");
            exceptionResponse.setDateTime(Calendar.getInstance().getTime());
            exceptionResponse.setStacktrace(Arrays.asList(e.getStackTrace()));
            return new MailResponse(details.getTo(), Calendar.getInstance().getTime(), exceptionResponse);
        }
    }

    @Override
    public MailResponse sendEmailWithAttachment(MailDetails details, String attachmentName) {
        MailResponse response;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

            message.setFrom(details.getFrom());
            message.setTo(details.getTo());
            message.setText(details.getBody());
            message.setSubject(details.getSubject());
            message.setSentDate(Calendar.getInstance().getTime());


            message.addAttachment(attachmentName, new ByteArrayResource(details.getAttachment()));
            this.mailSender.send(mimeMessage);
            response = new MailResponse(details.getTo(), Calendar.getInstance().getTime());
            response.setEmailSent(true);
            return response;
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse();
            if(e instanceof MessagingException)
                exceptionResponse.setMessage("An error has occurred creating the MIME content. Please check the attachment and the stacktrace.");
            else if(e instanceof MailSendException)
                exceptionResponse.setMessage("An error has occurred sending the e-mail. Please check the content and the stacktrace.");

            exceptionResponse.setCode("500");
            exceptionResponse.setDateTime(Calendar.getInstance().getTime());
            exceptionResponse.setStacktrace(Arrays.asList(e.getStackTrace()));
            return new MailResponse(details.getTo(), Calendar.getInstance().getTime(), exceptionResponse, false);
        }
    }
}

