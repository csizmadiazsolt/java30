package com.ezsocsi.fizzbuzz;


import java.util.Arrays;
import java.util.List;

import com.ezsocsi.utils.ConsoleUtils;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FizzBuzzTest {
    private ArgParser argParser;
    private FizzBuzzGenerator fizzBuzzGenerator;
    private ConsoleUtils consoleUtils;

    @Before
    public void setup() {
        argParser = mock(ArgParser.class);
        fizzBuzzGenerator = mock(FizzBuzzGenerator.class);
        consoleUtils = mock(ConsoleUtils.class);

        Integer lowerLimit = 4;
        Integer upperLimit = 7;
        List<String> generatedList = Arrays.asList("4", "Buzz", "Fizz", "7");

        when(argParser.getLowerLimit()).thenReturn(lowerLimit);
        when(argParser.getUpperLimit()).thenReturn(upperLimit);

        when(fizzBuzzGenerator.generate(lowerLimit, upperLimit)).thenReturn(generatedList);
    }

    @Test
    public void returns_correct_fizz_buzz_list() {
        FizzBuzz fizzBuzz = new FizzBuzz(argParser, fizzBuzzGenerator, consoleUtils);
        List<String> generatedList = Arrays.asList("4", "Buzz", "Fizz", "7");
        when(argParser.parseArgs("4", "7")).thenReturn(true);

        fizzBuzz.fizzBuzz("4", "7");

        verify(consoleUtils).printlnList(generatedList, ", ");
    }

    @Test
    public void returns_argument_parse_error() {
        FizzBuzz fizzBuzz = new FizzBuzz(argParser, fizzBuzzGenerator, consoleUtils);
        when(argParser.parseArgs("4", "7")).thenReturn(false);

        fizzBuzz.fizzBuzz("4", "7");

        verify(consoleUtils).printlnError("unable to parse arguments, expected: <int> <int>.");
    }
}
