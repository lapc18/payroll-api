package io.inab.atdev.payroll.core.services;

import io.inab.atdev.payroll.core.enums.Locales;
import io.inab.atdev.payroll.core.models.PayrollResponse;
import io.inab.atdev.payroll.services.PayrollServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PayrollServiceImplTests {


    @Autowired
    private PayrollServiceImpl payrollService;

    private File file;
    final private String locale = Locales.LOCALE_US.toString();
    final private String company = "atdev";



    @BeforeEach
    public void contextLoads() {
        final String path = "src/test/resources/static/test.csv";
        this.file = new File(path);;

        assertThat(this.payrollService).isNotNull();
        assertThat(this.file).isNotNull();
    }

    @Test
    public void processPayrollWithWrongData() {
        Assertions.assertDoesNotThrow(() -> {
            final String path = "src/test/resources/static/testWithWrongData.csv";
            this.file = new File(path);;
            MockMultipartFile mockMultipartFile
                    = new MockMultipartFile(
                    "file",
                    "realSlimTestShadyWithWrongData.csv",
                    MediaType.TEXT_PLAIN_VALUE,
                    Files.readAllBytes(this.file.toPath())
            );

            var response = this.payrollService.process(mockMultipartFile, this.locale, this.company);
            var okResponses = response.stream().filter(PayrollResponse::isEmailSent);
            var wrongResponses = response.stream().filter(res -> !res.isEmailSent());

            assertThat(response).isNotNull();
            assertThat(okResponses).isNotNull();
            assertThat(wrongResponses).isNotNull();
            assertThat(response.size()).isEqualTo(3);
            assertThat(okResponses.count()).isEqualTo(2);
            assertThat(wrongResponses.count()).isEqualTo(1);
        });
    }

    @Test
    public void processPayrollWithOkData() {
        Assertions.assertDoesNotThrow(() -> {
            MockMultipartFile mockMultipartFile
                    = new MockMultipartFile(
                    "file",
                    "realSlimTestShady.csv",
                    MediaType.TEXT_PLAIN_VALUE,
                    Files.readAllBytes(this.file.toPath())
            );

            var response = this.payrollService.process(mockMultipartFile, this.locale, this.company);

            assertThat(response).isNotNull();
            assertThat(response.size()).isEqualTo(3);

            response.forEach(res -> {
                assertThat(res.isEmailSent()).isEqualTo(true);
            });
        });
    }

}
