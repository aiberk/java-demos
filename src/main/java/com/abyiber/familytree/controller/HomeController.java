package com.abyiber.familytree.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Family Tree Application!";
    }

    @GetMapping("/yo")
    public String home2() {
        return "Welcome to home2!";
    }
}
