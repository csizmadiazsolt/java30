package com.ezsocsi.utility;


import java.util.Optional;

public class ArgUtility {
    public boolean checkNumberOfArgs(int numberOfExpectedArgs, String... args) {
        return args.length == numberOfExpectedArgs;
    }

    public Optional<String> getArgument(int argumentNumber, String... args) {
        Optional<String> argument;

        try {
            argument = Optional.of(args[argumentNumber]);
        } catch (ArrayIndexOutOfBoundsException e) {
            argument = Optional.empty();
        }

        return argument;
    }
}
