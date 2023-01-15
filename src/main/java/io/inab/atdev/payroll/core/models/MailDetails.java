package io.inab.atdev.payroll.core.models;


import org.springframework.beans.factory.annotation.Value;

public class MailDetails {

    private String to;

    @Value("${spring.mail.username}")
    private String from;
    private String subject;
    private String attachment;
    private String body;

    public MailDetails() {
    }

    public MailDetails(String to, String from, String subject, String body) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
    }

    public MailDetails(String to, String from, String subject, String attachment, String body) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.attachment = attachment;
        this.body = body;
    }

    public MailDetails(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

