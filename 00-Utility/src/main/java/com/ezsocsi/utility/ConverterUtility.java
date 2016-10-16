package com.ezsocsi.utility;


import java.util.Optional;

public class ConverterUtility {
    public Optional<Integer> stringToInteger(String input) {
        Optional<Integer> output;

        try {
            output = Optional.of(Integer.valueOf(input));
        } catch (NumberFormatException e) {
            output = Optional.empty();
        }

        return output;
    }
}
