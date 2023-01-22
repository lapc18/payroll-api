package io.inab.atdev.payroll.controllers;

import io.inab.atdev.payroll.core.helpers.CSVHelper;
import io.inab.atdev.payroll.core.models.MailDetails;
import io.inab.atdev.payroll.domain.entities.Employee;
import io.inab.atdev.payroll.services.MailServiceImpl;
import io.inab.atdev.payroll.services.PDFGeneratorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    public MailServiceImpl mailService;

    @Autowired
    public PDFGeneratorImpl pdfGeneratorService;

    @GetMapping("")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("RUNNING!");
    }

    @PostMapping("/email")
    public ResponseEntity<?> testEmail() {
        var test = this.mailService.sendEmail(new MailDetails(
                "devlapc18@gmail.com",
                "real testing",
                "real message shady"
                ));
        return ResponseEntity.ok(test);
    }

    @PostMapping("/csv")
    public ResponseEntity<?> testCsv(@RequestParam("file") MultipartFile file) {

        List<Employee> test = null;
        try {
            test = new LinkedList<Employee>(Objects.requireNonNull(CSVHelper.toList(file, Employee.class)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(test);
    }

    @Value("${spring.mail.username}")
    private String from;

    @Value(value = "classpath:static/atdev.png")
    private Resource company;

    @GetMapping("/res")
    public ResponseEntity<?> res() {

        return ResponseEntity.ok(company);
    }

    @PostMapping("/pdf")
    public ResponseEntity<?> testPDF(@RequestParam("file") MultipartFile file) {

        List<Employee> test = null;
        try {
            test = new LinkedList<Employee>(Objects.requireNonNull(CSVHelper.toList(file, Employee.class)));
            var arr = test.stream().findFirst();
            var base64 = new org.apache.tomcat.util.codec.binary.Base64();
            var asd = this.company.getFile();
            byte img[] = Files.readAllBytes(asd.toPath());
            String strToBase64 = base64.encodeAsString(img);

            Map<String, Object> map = new HashMap<>();
            map.put("employee", arr.get());
            map.put("company", strToBase64);

            String filename = "payroll_" + Calendar.getInstance().getTime() + ".pdf";
            var byteArrayInputStreams= this.pdfGeneratorService
                    .generatePdfFile("/email-template-es.html", map, filename);

            var details = new MailDetails(
                    "devlapc18@gmail.com",
                    this.from,
                    "real testing with attachment 2.0",
                    "real message shady with attachment"
            );
            details.setAttachment(byteArrayInputStreams);
            this.mailService.sendEmailWithAttachment(details, filename);

            return ResponseEntity.ok(map);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}