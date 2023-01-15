package io.inab.atdev.payroll.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("RUNNING!");
    }


}