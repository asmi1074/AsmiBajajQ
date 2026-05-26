package com.bajaj.bfhl.controller;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import com.bajaj.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl
     * Processes the input data array and returns categorized results.
     */
    @PostMapping
    public ResponseEntity<BfhlResponse> processData(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /bfhl
     * Returns the operation code (optional, useful for health check).
     */
    @GetMapping
    public ResponseEntity<OperationCodeResponse> getOperationCode() {
        return ResponseEntity.ok(new OperationCodeResponse(1));
    }

    /**
     * Simple response class for GET endpoint.
     */
    record OperationCodeResponse(int operation_code) {}
}
