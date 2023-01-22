package io.inab.atdev.payroll.core.models;

import java.time.LocalDateTime;
import java.util.Date;

public class MailResponse {

    private String email;

    private Date dateTime;

    private ExceptionResponse exception;

    private boolean emailSent;

    public MailResponse() {
    }

    public MailResponse(String email, Date dateTime) {
        this.email = email;
        this.dateTime = dateTime;
    }

    public MailResponse(String email, Date dateTime, ExceptionResponse exception) {
        this.email = email;
        this.dateTime = dateTime;
        this.exception = exception;
    }

    public MailResponse(String email, Date dateTime, ExceptionResponse exception, boolean emailSent) {
        this.email = email;
        this.dateTime = dateTime;
        this.exception = exception;
        this.emailSent = emailSent;
    }

    public boolean isEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public ExceptionResponse getException() {
        return exception;
    }

    public void setException(ExceptionResponse exception) {
        this.exception = exception;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
