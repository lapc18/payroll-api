package io.inab.atdev.payroll.services;

import io.inab.atdev.payroll.core.interfaces.MailService;
import io.inab.atdev.payroll.core.models.MailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Calendar;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Boolean sendEmail(MailDetails details) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(details.getFrom());
            message.setTo(details.getTo());
            message.setText(details.getBody());
            message.setSubject(details.getSubject());
            message.setSentDate(Calendar.getInstance().getTime());

            this.mailSender.send(message);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public Boolean sendEmailWithAttachment(MailDetails details) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

            message.setFrom(details.getFrom());
            message.setTo(details.getTo());
            message.setText(details.getBody());
            message.setSubject(details.getSubject());
            message.setSentDate(Calendar.getInstance().getTime());

            //TODO: remove this and implement generated PDF
            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
            String filename = file.getFilename() != null ? file.getFilename() : "Voucher";
            message.addAttachment(filename, file);

            this.mailSender.send(mimeMessage);
            return  true;
        } catch (MessagingException e) {
            return false;
        }
    }
}

