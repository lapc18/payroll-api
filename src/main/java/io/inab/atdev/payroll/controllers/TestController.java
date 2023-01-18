package io.inab.atdev.payroll.controllers;

import io.inab.atdev.payroll.core.helpers.CSVHelper;
import io.inab.atdev.payroll.core.models.MailDetails;
import io.inab.atdev.payroll.domain.entities.Employee;
import io.inab.atdev.payroll.services.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    public MailServiceImpl mailService;

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


}