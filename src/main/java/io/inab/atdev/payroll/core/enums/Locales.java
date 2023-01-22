package io.inab.atdev.payroll.core.enums;

public enum Locales {

    LOCALE_DO("do"),
    LOCALE_US("en");

    public final String locale;

    private Locales(String value) {
        this.locale = value;
    }

    @Override
    public String toString() {
        return this.locale;
    }
}
