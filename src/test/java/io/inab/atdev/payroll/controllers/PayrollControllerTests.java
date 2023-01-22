package io.inab.atdev.payroll.controllers;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PayrollControllerTests {

    @Autowired
    private PayrollController controller;

    private File file;

    @Test
    public void runProcessWithOkData() {
        Assertions.assertDoesNotThrow(() -> {
            final String path = "src/test/resources/static/test.csv";
            this.file = new File(path);
            MockMultipartFile mockMultipartFile
                    = new MockMultipartFile(
                    "file",
                    "realSlimTestShadyWithWrongData.csv",
                    MediaType.TEXT_PLAIN_VALUE,
                    Files.readAllBytes(this.file.toPath())
            );

            var response = this.controller.process(mockMultipartFile, "root+root", "atdev", "us");

            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        });
    }

    @Test
    public void runProcessWithWrongCredentialsData() {
        Assertions.assertDoesNotThrow(() -> {
            final String path = "src/test/resources/static/test.csv";
            this.file = new File(path);
            MockMultipartFile mockMultipartFile
                    = new MockMultipartFile(
                    "file",
                    "realSlimTestShadyWithWrongData.csv",
                    MediaType.TEXT_PLAIN_VALUE,
                    Files.readAllBytes(this.file.toPath())
            );

            var response = this.controller.process(mockMultipartFile, "roafdfot+asdas", "af af a", "uafdads");
            assertThat(response.getBody()).isNull();
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        });

    }

    @Test
    public void runProcessWithNullFileData() {
        Assertions.assertDoesNotThrow(() -> {
            var response = this.controller.process(null, "root+root", "af af a", "do");
            assertThat(response.getBody()).isNull();
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        });
    }

}
