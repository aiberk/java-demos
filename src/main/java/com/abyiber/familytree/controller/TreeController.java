package com.abyiber.familytree.controller;

import com.abyiber.familytree.dto.PersonForm;
import com.abyiber.familytree.service.TreeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    // @PostMapping(path = "/addPerson", consumes = { "multipart/form-data" })
    // public String addPerson(@ModelAttribute PersonForm personForm) {
    // System.out.println("Received Name: " + personForm.getName());
    // System.out.println("Received Mother's Name: " + personForm.getMotherName());
    // System.out.println("Received Father's Name: " + personForm.getFatherName());

    // treeService.addPerson(personForm.getName(), personForm.getMotherName(),
    // personForm.getFatherName());

    // return "redirect:/"; // Redirect to the homepage
    // }

    @PostMapping(path = "/addPerson", consumes = { "multipart/form-data" })
    public ResponseEntity<?> addPerson(@ModelAttribute PersonForm personForm) {
        System.out.println("Received Name: " + personForm.getName());
        System.out.println("Received Mother's Name: " + personForm.getMotherName());
        System.out.println("Received Father's Name: " + personForm.getFatherName());

        treeService.addPerson(personForm.getName(), personForm.getMotherName(), personForm.getFatherName());

        // Create a map to send back the response
        Map<String, String> response = new HashMap<>();
        response.put("name", personForm.getName());
        response.put("motherName", personForm.getMotherName());
        response.put("fatherName", personForm.getFatherName());

        // Return the response
        return ResponseEntity.ok(response);
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
