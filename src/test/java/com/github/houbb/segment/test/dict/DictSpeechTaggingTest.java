package com.github.houbb.segment.test.dict;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 半角
 * 小写
 * 去空格
 *
 * 概率高的放在前面。
 * @author binbin.hou
 * @since 0.1.4
 */
@Ignore
public class DictSpeechTaggingTest {

    @Test
    public void wordTypeEnumTest() {
        List<String> lines = FileUtil.readAllLines("D:\\_github\\segment\\src\\test\\resources\\nature.txt");

        String template = "%s(\"%s\", \"%s\"),";
        for(String line : lines) {
            if(StringUtil.isEmptyTrim(line) || line.startsWith("#")) {
                continue;
            }

            String[] strings = line.split(" ");
            String name = strings[0].toUpperCase();
            String code = strings[0].toLowerCase();
            String desc = strings[1];

            System.out.println(String.format(template, name, code, desc));
        }
    }

    @Test
    public void dictOptimizeTest() {
        final String s = "D:\\_github\\segment\\src\\test\\resources\\tag\\segment_pos_tagging_system.txt";
        final String t = "D:\\_github\\segment\\src\\main\\resources\\segment_pos_tagging_system.txt";

        List<String> lines = FileUtil.readAllLines(s);
        List<String> results = Guavas.newArrayList(lines.size());

        for(String line : lines) {
            List<String> entries = StringUtil.splitToList(line, " ");

            // 半角小写
            String word = StringUtil.toHalfWidth(entries.get(0).toLowerCase());

            int totalSize = entries.size();
            List<String> tagFreq = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 1; i < totalSize; i++) {
                String text = entries.get(i).trim();

                if(i % 2 == 0) {
                    stringBuilder.append(":").append(text);
                    tagFreq.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                } else {
                    stringBuilder.append(text);
                }
            }
            String result = word + " " +StringUtil.join(tagFreq, ",");
            results.add(result);
        }

        results = CollectionUtil.sort(results);
        FileUtil.write(t, results);
    }

    /**
     * 对原始的词性字典的进行扩充
     * @since 0.1.4
     */
    @Test
    public void dictExtraTest() {
        final String s = "D:\\_github\\segment\\src\\main\\resources\\segment_dict.txt";
        final String t = "D:\\_github\\segment\\src\\main\\resources\\segment_pos_tagging_system.txt";

        List<String> lines = FileUtil.readAllLines(t);
        List<String> results = Guavas.newArrayList(lines);
        List<String> dictLines = FileUtil.readAllLines(s);

        List<String> wordList = CollectionUtil.toList(results, new IHandler<String, String>() {
            @Override
            public String handle(String s) {
                return s.split(" ")[0];
            }
        });


        for(String line : dictLines) {
            System.out.println(line);
            String[] strings = line.split(" ");
            String word = strings[0];
            if(!wordList.contains(word)) {
                String result = word + " " + strings[2]+":1";
                results.add(result);
            }
        }

        results = CollectionUtil.sort(results);
        FileUtil.write(t, results);
    }

}
