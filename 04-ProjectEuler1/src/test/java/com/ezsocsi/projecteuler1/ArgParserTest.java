package com.ezsocsi.projecteuler1;


import com.ezsocsi.utils.ArgUtils;
import com.ezsocsi.utils.ConverterUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArgParserTest {
    private ArgParser argParser;

    private static final int NUMBER_OF_ARGS = 1;
    private final static String[] ARGUMENTS_1 = {"1000", "10000"};
    private final static String[] ARGUMENTS_2 = {"Not numeric argument"};
    private final static String[] ARGUMENTS_3 = {"100"};

    @Before
    public void setup() {
        ArgUtils argUtils = mock(ArgUtils.class);
        ConverterUtils converterUtils = mock(ConverterUtils.class);
        argParser = new ArgParser(argUtils, converterUtils);

        when(argUtils.checkNumberOfArgs(NUMBER_OF_ARGS, ARGUMENTS_1)).thenReturn(false);
        when(argUtils.checkNumberOfArgs(NUMBER_OF_ARGS, ARGUMENTS_2)).thenReturn(true);
        when(argUtils.checkNumberOfArgs(NUMBER_OF_ARGS, ARGUMENTS_3)).thenReturn(true);

        when(argUtils.getArgument(0, ARGUMENTS_2)).thenReturn(Optional.of(ARGUMENTS_2[0]));
        when(argUtils.getArgument(0, ARGUMENTS_3)).thenReturn(Optional.of(ARGUMENTS_3[0]));

        when(converterUtils.stringToInteger(ARGUMENTS_2[0])).thenReturn(Optional.empty());
        when(converterUtils.stringToInteger(ARGUMENTS_3[0])).thenReturn(Optional.of(12));
    }

    @Test
    public void returns_false_because_of_too_much_arguments() {
        assertFalse(argParser.parseArgs(ARGUMENTS_1));
    }

    @Test
    public void returns_false_because_arguments_are_parsed_are_not_numeric() {
        assertFalse(argParser.parseArgs(ARGUMENTS_2));
    }

    @Test
    public void returns_true_because_input_argument_is_valid() {
        assertTrue(argParser.parseArgs(ARGUMENTS_3));
    }
}
