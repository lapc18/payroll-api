package io.inab.atdev.payroll.controllers;

import io.inab.atdev.payroll.core.models.PayrollResponse;
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
import java.util.List;

@RestController
@RequestMapping("")
public class PayrollController {


    @Autowired
    private PayrollServiceImpl payrollService;

    @Value("${payroll.user.name}")
    private String user;

    @Value("${payroll.user.pwd}")
    private String pwd;

    @PostMapping("/process")
    public ResponseEntity<List<PayrollResponse>> process(
            @RequestParam("file") MultipartFile file,
            @RequestParam String credentials,
            @RequestParam String company,
            @RequestParam String country
    ) {

        String[] credentialArr = credentials.split("\\+");
        if(!credentialArr[0].contains(this.user) || !credentialArr[1].contains(this.pwd))
            ResponseEntity.status(HttpStatus.UNAUTHORIZED);

        try {
            var response = this.payrollService.process(file, country, company);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return (ResponseEntity<List<PayrollResponse>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
