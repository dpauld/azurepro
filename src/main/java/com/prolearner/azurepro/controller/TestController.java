package com.prolearner.azurepro.controller;

import com.prolearner.azurepro.model.TestEntity;
import com.prolearner.azurepro.repository.TestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    // using a get method for testing purposes only - in production, you would want to use a POST or PUT for creating records
    @GetMapping("/add-item")
    public List<TestEntity> addItem() {
        // Drop a test log into the DB to ensure write access works
        testRepository.save(new TestEntity("Successfully connected to Postgres using Azure Key Vault secrets!"));
        // Return everything to prove read access works
        return testRepository.findAll();
    }

    @DeleteMapping("/delete-items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        boolean existed = testRepository.existsById(id);
        if (existed) {
            testRepository.deleteById(id);
        }
        String message = existed
                ? "Item with id " + id + " has been removed."
                : "Item with id " + id + " not found.";

        List<TestEntity> items = testRepository.findAll();

        List<Object> body = new ArrayList<>();
        body.add(message);
        body.add(items);

        return ResponseEntity.ok(body); // HTTP 200 with JSON body
    }

    @GetMapping("items/{id}")
    public ResponseEntity<?>  getItem(@PathVariable Long id) {
        return testRepository.findById(id)
                .map(item -> ResponseEntity.ok(item)) // HTTP 200 with item in body
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); // HTTP 404 with message;
    }


    @GetMapping("/items")
    public List<TestEntity> getItems() {
        // Return everything to prove read access works
        return testRepository.findAll();
    }

}
