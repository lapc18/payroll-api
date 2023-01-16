package io.inab.atdev.payroll.core.models;

import java.time.LocalDateTime;
import java.util.Date;

public class MailResponse {

    public String email;

    public Date dateTime;

    private ExceptionResponse exception;

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
