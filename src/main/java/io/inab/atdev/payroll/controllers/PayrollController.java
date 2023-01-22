package io.inab.atdev.payroll.controllers;

import io.inab.atdev.payroll.services.PayrollServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("")
public class PayrollController {


    @Autowired
    private PayrollServiceImpl payrollService;

    @Value("${user.name}")
    private String user;

    @Value("${user.pwd}")
    private String pwd;

    @PostMapping("/process")
    public ResponseEntity<?> process(
            @RequestParam("file") MultipartFile file,
            @RequestParam String credentials,
            @RequestParam String company,
            @RequestParam String country
    ) {

        String[] credentialArr = credentials.split("/+", 2);
        if(!credentialArr[0].contains(this.user) || !credentialArr[1].contains(this.pwd))
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);

        try {
            var response = this.payrollService.process(file, country, company);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
