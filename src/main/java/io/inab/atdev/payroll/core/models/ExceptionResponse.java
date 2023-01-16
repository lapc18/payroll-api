package io.inab.atdev.payroll.core.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ExceptionResponse {

    private String message;

    private String code;

    private Date dateTime;

    private List stacktrace;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message, String code, Date dateTime) {
        this.message = message;
        this.code = code;
        this.dateTime = dateTime;
    }

    public ExceptionResponse(String message, String code, Date dateTime, List stacktrace) {
        this.message = message;
        this.code = code;
        this.dateTime = dateTime;
        this.stacktrace = stacktrace;
    }

    public List getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(List stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
