package com.github.houbb.segment.test.dict;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Ignore
public class DictFormatTest {

    @Test
    public void toLowerCaseTest() {
        List<String> lines = StreamUtil.readAllLines("/format.txt");

        List<String> resultLines = CollectionUtil.distinct(
                CollectionUtil.toList(lines, new IHandler<String, String>() {
                    @Override
                    public String handle(String s) {
                        return StringUtil.toHalfWidth(s).toLowerCase();
                    }
                })
        );

        for(String line : resultLines) {
            System.out.println(line);
        }
    }

}
