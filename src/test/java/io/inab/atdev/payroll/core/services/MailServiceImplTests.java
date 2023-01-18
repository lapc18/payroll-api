package io.inab.atdev.payroll.core.services;

import io.inab.atdev.payroll.core.models.MailDetails;
import io.inab.atdev.payroll.core.models.MailResponse;
import io.inab.atdev.payroll.services.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MailServiceImplTests {

    @Autowired
    private MailServiceImpl mailService;

    @Test
    public void contextLoads() {
        assertThat(this.mailService).isNotNull();
    }

    @Test
    public void sendSimpleEmail() {
        MailDetails details = new MailDetails("devlapc18@gmail.com", "AtDev: testing API Email Service Impl", "Unit test of simple email");
        MailResponse response = this.mailService.sendEmail(details);
        assertThat(response).isNotNull();
        assertThat(response.getException()).isNull();
    }

    @Test
    public void notSendSimpleEmail() {
        MailDetails details = new MailDetails("devlapc18@", "AtDev: testing API Email Service Impl", "Unit test of simple email");
        MailResponse response = this.mailService.sendEmail(details);
        assertThat(response).isNotNull();
        assertThat(response.getException()).isNotNull();
    }
}
