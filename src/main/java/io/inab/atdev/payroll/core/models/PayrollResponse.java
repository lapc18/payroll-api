package io.inab.atdev.payroll.core.models;

public class PayrollResponse {

    private String email;

    private String datetime;

    private boolean isEmailSent;

    public PayrollResponse() {
    }

    public PayrollResponse(String email, String datetime) {
        this.email = email;
        this.datetime = datetime;
    }

    public PayrollResponse(String email, String datetime, boolean isEmailSent) {
        this.email = email;
        this.datetime = datetime;
        this.isEmailSent = isEmailSent;
    }

    public boolean isEmailSent() {
        return isEmailSent;
    }

    public void setEmailSent(boolean emailSent) {
        isEmailSent = emailSent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
