package io.inab.atdev.payroll.services;

import io.inab.atdev.payroll.core.models.MailDetails;
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
        assertThat(this.mailService.sendEmail(details)).isTrue();
    }
}
