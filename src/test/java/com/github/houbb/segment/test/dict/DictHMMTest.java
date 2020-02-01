package com.github.houbb.segment.test.dict;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.segment.exception.SegmentException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Ignore
public class DictHMMTest {

    /**
     * 状态列表
     * @since 0.1.0
     */
    private static final char[] STATE_CHARS = {'B', 'M', 'E', 'S'};

    /**
     * 获取状态的下标
     * @param c 状态
     * @return 结果
     * @since 0.1.0
     */
    private int getStateIndex(final char c) {
        for(int i = 0; i < STATE_CHARS.length; i++) {
            if(STATE_CHARS[i] == c) {
                return i;
            }
        }

        throw new SegmentException("Not support state for " + c);
    }

    @Test
    public void emitProbTest() {
        final String targetPath = "D:\\github\\segment\\src\\main\\resources\\segment_emit_prob.txt";
        List<String> stringList = StreamUtil.readAllLines("/segment_emit_prob_old.txt");

        List<String> resultLines = new ArrayList<>();

        // BMES
        int index = -1;

        for(String line : stringList) {
            if(line.length() == 1) {
                index = getStateIndex(line.charAt(0));
                continue;
            }

            String newLine = index + " " + line;
            resultLines.add(newLine);
        }

        // 排序
        Collections.sort(resultLines);

        FileUtil.write(targetPath, resultLines);
    }



}
