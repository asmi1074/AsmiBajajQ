package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceImplTest {

    private BfhlService bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
    }

    // ======================================================================
    // Test Case: Example A from the assignment
    // Input:  ["A", "1", "334", "4", "R", "5"]
    // ======================================================================
    @Test
    @DisplayName("Example A - Mixed numbers and alphabets")
    void testExampleA() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("A", "1", "334", "4", "R", "5"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("asmi_gupta_10072004", response.getUserId());
        assertEquals("asmigupta230828@acropolis.in", response.getEmail());
        assertEquals("0827IT231034", response.getRollNumber());

        // Odd numbers: 1, 5
        assertEquals(Arrays.asList("1", "5"), response.getOddNumbers());

        // Even numbers: 334, 4
        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());

        // Alphabets: A, R
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());

        // Special characters: none
        assertTrue(response.getSpecialCharacters().isEmpty());

        // Sum: 1 + 334 + 4 + 5 = 344
        assertEquals("344", response.getSum());

        // Concat string: Chars=[A,R], reversed=[R,A], alternating caps = "Ra"
        assertEquals("Ra", response.getConcatString());
    }

    // ======================================================================
    // Test Case: Example B from the assignment
    // Input:  ["2", "4", "5", "1", "3", "a", "r", "t", "*", "-", "9", "92", "7"]
    // ======================================================================
    @Test
    @DisplayName("Example B - Numbers, lowercase alphabets, and special chars")
    void testExampleB() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("2", "4", "5", "1", "3", "a", "r", "t", "*", "-", "9", "92", "7")
        );
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());

        // Odd numbers: 5, 1, 3, 9, 7
        assertEquals(Arrays.asList("5", "1", "3", "9", "7"), response.getOddNumbers());

        // Even numbers: 2, 4, 92
        assertEquals(Arrays.asList("2", "4", "92"), response.getEvenNumbers());

        // Alphabets: a, r, t
        assertEquals(Arrays.asList("a", "r", "t"), response.getAlphabets());

        // Special characters: *, -
        assertEquals(Arrays.asList("*", "-"), response.getSpecialCharacters());

        // Sum: 2+4+5+1+3+9+92+7 = 123
        assertEquals("123", response.getSum());

        // Concat string: Chars=[a,r,t], reversed=[t,r,a], alternating caps = "TrA"
        assertEquals("TrA", response.getConcatString());
    }

    // ======================================================================
    // Test Case: Example C from the assignment
    // Input:  ["A", "ABCD", "DOE"]
    // ======================================================================
    @Test
    @DisplayName("Example C - Only alphabets with multi-char strings")
    void testExampleC() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());

        // No numbers
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());

        // Alphabets: A, ABCD, DOE
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());

        // No special characters
        assertTrue(response.getSpecialCharacters().isEmpty());

        // Sum: 0
        assertEquals("0", response.getSum());

        // Concat string: Chars=[A, A,B,C,D, D,O,E], reversed=[E,O,D,D,C,B,A,A]
        // Alternating caps: E,o,D,d,C,b,A,a => "EoDdCbAa"
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    // ======================================================================
    // Test Case: Empty data array
    // ======================================================================
    @Test
    @DisplayName("Empty data array should return empty results")
    void testEmptyDataArray() {
        BfhlRequest request = new BfhlRequest(Collections.emptyList());
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    // ======================================================================
    // Test Case: Only numbers
    // ======================================================================
    @Test
    @DisplayName("Only numbers - no alphabets or special chars")
    void testOnlyNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("10", "3", "7", "20"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("3", "7"), response.getOddNumbers());
        assertEquals(Arrays.asList("10", "20"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("40", response.getSum());
        assertEquals("", response.getConcatString());
    }

    // ======================================================================
    // Test Case: Only special characters
    // ======================================================================
    @Test
    @DisplayName("Only special characters")
    void testOnlySpecialCharacters() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("@", "#", "$", "!"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(Arrays.asList("@", "#", "$", "!"), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    // ======================================================================
    // Test Case: Single alphabet character
    // ======================================================================
    @Test
    @DisplayName("Single alphabet - concat string should be uppercase")
    void testSingleAlphabet() {
        BfhlRequest request = new BfhlRequest(Collections.singletonList("z"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Collections.singletonList("z"), response.getAlphabets());
        assertEquals("Z", response.getConcatString());
    }

    // ======================================================================
    // Test Case: Negative numbers
    // ======================================================================
    @Test
    @DisplayName("Negative numbers should be handled correctly")
    void testNegativeNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("-3", "-4", "5"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("-3", "5"), response.getOddNumbers());
        assertEquals(Collections.singletonList("-4"), response.getEvenNumbers());
        assertEquals("-2", response.getSum());
    }

    // ======================================================================
    // Test Case: User details are always present
    // ======================================================================
    @Test
    @DisplayName("User details should always be present in response")
    void testUserDetailsPresent() {
        BfhlRequest request = new BfhlRequest(Collections.singletonList("1"));
        BfhlResponse response = bfhlService.processData(request);

        assertNotNull(response.getUserId());
        assertNotNull(response.getEmail());
        assertNotNull(response.getRollNumber());
        assertFalse(response.getUserId().isEmpty());
        assertFalse(response.getEmail().isEmpty());
        assertFalse(response.getRollNumber().isEmpty());
    }
}
