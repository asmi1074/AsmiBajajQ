package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    // Hardcoded user details as per assignment
    private static final String USER_ID = "asmi_gupta_10072004";
    private static final String EMAIL = "asmigupta230828@acropolis.in";
    private static final String ROLL_NUMBER = "0827IT231034";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            if (item == null || item.isEmpty()) {
                continue;
            }

            // Check if the item is a number (can be negative or multi-digit)
            if (isNumber(item)) {
                long num = Long.parseLong(item);
                sum += num;

                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                // Item contains only alphabetic characters
                alphabets.add(item);
            } else {
                // Item is a special character or mix
                specialCharacters.add(item);
            }
        }

        // Build concat_string: all alphabetical characters in reverse order, alternating caps
        String concatString = buildConcatString(alphabets);

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sum))
                .concatString(concatString)
                .build();
    }

    /**
     * Checks if a string represents a valid integer number.
     */
    private boolean isNumber(String item) {
        try {
            Long.parseLong(item);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string contains only alphabetic characters.
     */
    private boolean isAlphabetic(String item) {
        for (char c : item.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds the concatenated string from all alphabetical characters in the input.
     * Steps:
     * 1. Extract all individual alphabetical characters from the alphabets list.
     * 2. Reverse the order of characters.
     * 3. Apply alternating caps (1st uppercase, 2nd lowercase, 3rd uppercase, ...).
     */
    private String buildConcatString(List<String> alphabets) {
        // Collect all individual characters from all alphabet entries
        List<Character> allChars = new ArrayList<>();
        for (String alpha : alphabets) {
            for (char c : alpha.toCharArray()) {
                allChars.add(c);
            }
        }

        // Reverse the list
        Collections.reverse(allChars);

        // Apply alternating caps: index 0 -> uppercase, index 1 -> lowercase, ...
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allChars.size(); i++) {
            char c = allChars.get(i);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }

        return sb.toString();
    }
}
