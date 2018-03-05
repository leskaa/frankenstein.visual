package wordcloud;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WordFrequencies {
    private Map<String, Integer> wordMap;

    public WordFrequencies() {
        wordMap = new HashMap<>();
    }

    public WordFrequencies(String sent) {
        this();
        this.setData(sent);
    }

    public void setData(String data) {
        String[] words = data.split("\\s");
        for (String w :
                words) {
            w = w.replaceAll("[^A-Za-z]", "");
            wordMap.putIfAbsent(w, 0);
            wordMap.put(w, wordMap.get(w) + 1);
        }
    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        wordMap.forEach((k, v) -> output.append(k + "\t" + v + "\n"));
        return output.toString() + "\n\n";
    }
}