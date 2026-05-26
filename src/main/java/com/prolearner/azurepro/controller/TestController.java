package com.prolearner.azurepro.controller;

import com.prolearner.azurepro.model.TestEntity;
import com.prolearner.azurepro.repository.TestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TestController {

    private final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping("/test")
    public List<TestEntity> runTest() {
        // Drop a test log into the DB to ensure write access works
        testRepository.save(new TestEntity("Successfully connected to Postgres using Azure Key Vault secrets!"));

        // Return everything to prove read access works
        return testRepository.findAll();
    }
}
