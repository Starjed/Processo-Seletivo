package com.processo.seletivo.controllers;

import com.processo.seletivo.services.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mock")
public class MockController {

    @Autowired
    private MockService mockService;

    @PostMapping
    public ResponseEntity<String> executarMock() {
        mockService.gerarMock();
        return ResponseEntity.ok("Mock executado com sucesso!");
    }
}
