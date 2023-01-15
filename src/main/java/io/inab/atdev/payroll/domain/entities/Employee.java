package io.inab.atdev.payroll.domain.entities;

public class Employee {

    private String full_name;
    private String email;
    private String position;
    private double health_discount_amount;
    private double social_discount_amount;
    private double taxes_discount_amount;
    private double other_discount_amount;
    private double gross_salary;
    private double gross_payment;
    private double net_payment;
    private String period;

    public Employee() {
    }

    public Employee(String full_name, String email, String position, double health_discount_amount, double social_discount_amount, double taxes_discount_amount, double other_discount_amount, double gross_salary, double gross_payment, double net_payment, String period) {
        this.full_name = full_name;
        this.email = email;
        this.position = position;
        this.health_discount_amount = health_discount_amount;
        this.social_discount_amount = social_discount_amount;
        this.taxes_discount_amount = taxes_discount_amount;
        this.other_discount_amount = other_discount_amount;
        this.gross_salary = gross_salary;
        this.gross_payment = gross_payment;
        this.net_payment = net_payment;
        this.period = period;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getHealth_discount_amount() {
        return health_discount_amount;
    }

    public void setHealth_discount_amount(double health_discount_amount) {
        this.health_discount_amount = health_discount_amount;
    }

    public double getSocial_discount_amount() {
        return social_discount_amount;
    }

    public void setSocial_discount_amount(double social_discount_amount) {
        this.social_discount_amount = social_discount_amount;
    }

    public double getTaxes_discount_amount() {
        return taxes_discount_amount;
    }

    public void setTaxes_discount_amount(double taxes_discount_amount) {
        this.taxes_discount_amount = taxes_discount_amount;
    }

    public double getOther_discount_amount() {
        return other_discount_amount;
    }

    public void setOther_discount_amount(double other_discount_amount) {
        this.other_discount_amount = other_discount_amount;
    }

    public double getGross_salary() {
        return gross_salary;
    }

    public void setGross_salary(double gross_salary) {
        this.gross_salary = gross_salary;
    }

    public double getGross_payment() {
        return gross_payment;
    }

    public void setGross_payment(double gross_payment) {
        this.gross_payment = gross_payment;
    }

    public double getNet_payment() {
        return net_payment;
    }

    public void setNet_payment(double net_payment) {
        this.net_payment = net_payment;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
