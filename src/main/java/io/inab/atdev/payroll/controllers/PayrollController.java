package io.inab.atdev.payroll.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process")
public class PayrollController {




    @PostMapping("")
    public ResponseEntity<?> process() {

        return ResponseEntity.ok("OK");
    }
}
