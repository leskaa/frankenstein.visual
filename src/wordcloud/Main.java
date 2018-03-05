package wordcloud;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.palette.ColorPalette;
import org.apache.log4j.BasicConfigurator;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {
            BasicConfigurator.configure();
            Scanner input = new Scanner(new File("resources/frankensteinText"));
            StringBuilder data = new StringBuilder();
            while (input.hasNext()) {
                data.append(input.nextLine()).append(" ");
            }
            input = new Scanner(new File("resources/top1000words"));
            List<String> commonWords = new ArrayList<>();
            while (input.hasNext()) {
                commonWords.add(input.nextLine());
            }
            //System.out.println(data.toString());
            WordFrequencies frequencyMap = new WordFrequencies();
            frequencyMap.setData(data.toString());
            System.out.println(frequencyMap);
            List<WordFrequency> wordFrequencies = new ArrayList<>();
            for (Map.Entry<String, Integer> entry :
                    frequencyMap.getWordMap().entrySet()) {
                if (commonWords.contains(entry.getKey().toLowerCase())) {
                    entry.setValue(0);
                }
                if (entry.getValue() > 2 && entry.getKey().length() > 4) {
                    wordFrequencies.add(new WordFrequency(entry.getKey(), entry.getValue()));
                }
            }
            final Dimension dimension = new Dimension(600, 554);
            final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
            wordCloud.setPadding(1);
            wordCloud.setBackground(new PixelBoundryBackground("resources/frankenstein.png"));
            wordCloud.setColorPalette(new ColorPalette(new Color(0x56F67B), new Color(0x0ADF3D), new Color(0x079629), new Color(0x05721F), new Color(0xC5F0C7)));
            wordCloud.setFontScalar(new LinearFontScalar(3, 28));
            wordCloud.build(wordFrequencies);
            wordCloud.writeToFile("resources/frankensteinOutput.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
