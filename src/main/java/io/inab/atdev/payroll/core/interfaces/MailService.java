package io.inab.atdev.payroll.core.interfaces;

import io.inab.atdev.payroll.core.models.MailDetails;

public interface MailService {

    Boolean sendEmail(MailDetails details);

    Boolean sendEmailWithAttachment(MailDetails details);
}

