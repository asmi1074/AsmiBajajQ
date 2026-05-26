package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;

/**
 * Service interface for BFHL data processing operations.
 */
public interface BfhlService {

    /**
     * Processes the input data array and returns a structured response
     * containing categorized numbers, alphabets, special characters,
     * sum of numbers, and concatenated string.
     *
     * @param request the request containing the data array
     * @return BfhlResponse with all processed fields
     */
    BfhlResponse processData(BfhlRequest request);
}
