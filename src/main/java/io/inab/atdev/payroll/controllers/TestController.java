package io.inab.atdev.payroll.controllers;

import io.inab.atdev.payroll.core.models.MailDetails;
import io.inab.atdev.payroll.services.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        boolean test = this.mailService.sendEmail(new MailDetails(
                "devlapc18@gmail.com",
                "real testing",
                "real message shady"
                ));
        return ResponseEntity.ok(test);
    }


}