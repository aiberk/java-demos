package com.abyiber.familytree.controller;

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

    @PostMapping("/addPerson")
    public String addPerson(@RequestParam(name = "name") String name,
            @RequestParam(name = "motherName", required = false) String motherName,
            @RequestParam(name = "fatherName", required = false) String fatherName) {
        treeService.addPerson(name, motherName, fatherName);
        return "Person added successfully";
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
