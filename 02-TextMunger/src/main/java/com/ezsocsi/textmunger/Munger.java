package com.ezsocsi.textmunger;


import com.ezsocsi.utils.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Munger {

    private final ListUtils listUtils;

    public Munger(ListUtils listUtils) {
        this.listUtils = listUtils;
    }

    private List<String> splitSentenceToWords(String sentence) {
        String[] words = sentence.split("\\b");

        return Arrays.asList(words);
    }

    private String shuffleWord(String word) {
        List<Character> wordAsCharSequence = word.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        if (wordAsCharSequence.size() > 1) {
            List<Character> shuffledWordAsCharSequence = listUtils.shuffle(wordAsCharSequence, 1, wordAsCharSequence.size() - 1);
            return shuffledWordAsCharSequence.stream().map(Object::toString).collect(Collectors.joining());
        }

        return word;
    }

    private String concatenateWords(List<String> words) {
        return words.stream().collect(Collectors.joining());
    }

    public String munge(String sentence) {
        List<String> words = splitSentenceToWords(sentence);
        List<String> shuffledWords = new ArrayList<>();

        words.forEach(w -> shuffledWords.add(shuffleWord(w)));

        return concatenateWords(shuffledWords);
    }
}