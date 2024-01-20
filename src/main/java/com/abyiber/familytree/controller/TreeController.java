package com.abyiber.familytree.controller;

import com.abyiber.familytree.dto.PersonForm;
import com.abyiber.familytree.service.TreeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @PostMapping(path = "/addPerson", consumes = { "multipart/form-data" })
    public String addPerson(@ModelAttribute PersonForm personForm) {
        System.out.println("Received Name: " + personForm.getName());
        System.out.println("Received Mother's Name: " + personForm.getMotherName());
        System.out.println("Received Father's Name: " + personForm.getFatherName());

        treeService.addPerson(personForm.getName(), personForm.getMotherName(), personForm.getFatherName());

        return "redirect:/"; // Redirect to the homepage
    }

    @GetMapping("/descendants")
    public ResponseEntity<List<String>> getDescendants(@RequestParam(name = "name") String name) {
        List<String> descendants = treeService.getDescendants(name);
        return ResponseEntity.ok(descendants);
    }

    @GetMapping("/ancestors")
    public ResponseEntity<List<String>> getAncestors(@RequestParam(name = "name") String name) {
        List<String> ancestors = treeService.getAncestors(name);
        return ResponseEntity.ok(ancestors);
    }

    @GetMapping("/familyTree")
    public ResponseEntity<List<String>> getFamilyTree(@RequestParam(name = "name") String name) {
        List<String> familyTree = treeService.getFamilyTree(name);
        return ResponseEntity.ok(familyTree);
    }
}
