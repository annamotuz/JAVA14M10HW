package org.example.Words;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordsCounter {

    public static void main(String[] args) {
        String filePath = "file_words_amount.txt";
        Map<String, Integer> wordFrequencyMap = getWordFrequency(filePath);

        List<Map.Entry<String, Integer>> sortedWordFrequencyList = new ArrayList<>(wordFrequencyMap.entrySet());
        sortedWordFrequencyList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        for (Map.Entry<String, Integer> entry : sortedWordFrequencyList) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    public static Map<String, Integer> getWordFrequency(String filePath) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] words = line.split("\\s+");

                for (String word : words) {

                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();


                    if (!word.isEmpty()) {
                        wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordFrequencyMap;
    }
}
