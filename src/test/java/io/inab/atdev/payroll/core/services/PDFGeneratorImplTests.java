package io.inab.atdev.payroll.core.services;

import io.inab.atdev.payroll.core.helpers.CSVHelper;
import io.inab.atdev.payroll.domain.entities.Employee;
import io.inab.atdev.payroll.services.PDFGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;


import java.io.File;
import java.nio.file.Files;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class PDFGeneratorImplTests {

    @Autowired
    private PDFGeneratorImpl pdfGeneratorService;

    public File resource;


    @BeforeEach
    public void contextLoads() {

        final String path = "src/test/resources/static/test.csv";
        this.resource = new File(path);;

        assertThat(this.pdfGeneratorService).isNotNull();
        assertThat(this.resource).isNotNull();
        Assertions.assertDoesNotThrow(() -> assertThat(this.resource.getAbsolutePath().endsWith("/static/test.csv")));
    }

    @Test
    public void generatePDFTestWithTemplatePresent() {

        Assertions.assertDoesNotThrow(() -> {
            MockMultipartFile mockMultipartFile
                    = new MockMultipartFile(
                    "file",
                    "realSlimTestShady.csv",
                    MediaType.TEXT_PLAIN_VALUE,
                    Files.readAllBytes(this.resource.toPath())
            );
            var employees = new LinkedList<Employee>(Objects.requireNonNull(CSVHelper.toList(mockMultipartFile, Employee.class)));
            Map<String, Object> map = new HashMap<>();
            map.put("employee", employees.getFirst());
            map.put("company", "");

            String filename = "payroll_" + Calendar.getInstance().getTime() + ".pdf";
            var byteArrayInputStreams= this.pdfGeneratorService
                    .generatePdfFile("/email-template-es.html", map, filename);


            assertThat(byteArrayInputStreams).isNotNull();
            assertThat(byteArrayInputStreams).isNotEmpty();
            assertThat(byteArrayInputStreams).isInstanceOf(byte[].class);

        });
    }

    @Test
    public void generatePDFTestWithNoTemplatePresent() {

        Assertions.assertDoesNotThrow(() -> {
            MockMultipartFile mockMultipartFile
                    = new MockMultipartFile(
                    "file",
                    "realSlimTestShady.csv",
                    MediaType.TEXT_PLAIN_VALUE,
                    Files.readAllBytes(this.resource.toPath())
            );
            var employees = new LinkedList<Employee>(Objects.requireNonNull(CSVHelper.toList(mockMultipartFile, Employee.class)));
            Map<String, Object> map = new HashMap<>();
            map.put("employee", employees.getFirst());
            map.put("company", "");

            String filename = "payroll_" + Calendar.getInstance().getTime() + ".pdf";
            Assertions.assertThrows(RuntimeException.class, () -> this.pdfGeneratorService
                    .generatePdfFile("/email-template.html", map, filename));
        });
    }
}
