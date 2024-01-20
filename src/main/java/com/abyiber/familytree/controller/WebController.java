package com.abyiber.familytree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.abyiber.familytree.dto.PersonForm;
import com.abyiber.familytree.service.TreeService;

@Controller
public class WebController {

    @Autowired
    private TreeService treeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("personForm", new PersonForm());
        model.addAttribute("message", "Hello World!");
        return "index";
    }

    @PostMapping("/addPerson")
    public String addPerson(@ModelAttribute PersonForm personForm, Model model) {
        // Call service method to add person
        treeService.addPerson(personForm.getName(), personForm.getMotherName(), personForm.getFatherName());

        // Optionally, add attributes to the model or handle redirects
        // For example, redirecting to the homepage
        return "redirect:/";
    }

}
