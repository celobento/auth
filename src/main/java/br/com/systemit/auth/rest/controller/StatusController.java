package br.com.systemit.auth.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @GetMapping
    public String getStatus() {
        return "System available | " + new Date();
    }

}
