package io.inab.atdev.payroll.core.interfaces;

import io.inab.atdev.payroll.core.models.MailDetails;
import io.inab.atdev.payroll.core.models.MailResponse;

public interface MailService {

    MailResponse sendEmail(MailDetails details);

    MailResponse sendEmailWithAttachment(MailDetails details);
}

