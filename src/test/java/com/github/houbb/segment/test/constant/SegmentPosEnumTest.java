package com.github.houbb.segment.test.constant;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.segment.constant.enums.SegmentPosEnum;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
@Ignore
public class SegmentPosEnumTest {

    @Test
    public void genEnumTest() {
        final String format = "     /**\n" +
                "     * %s\n" +
                "     */\n" +
                "    %s(\"%s\", \"%s\"),";

        List<String> lines = StreamUtil.readAllLines("/word-tag.txt");

        for(String line : lines) {
            List<String> entries = StringUtil.splitToList(line, " ");

            String code = entries.get(0);
            String enumName = code.toUpperCase();
            String desc = entries.get(1);
            String remark = entries.get(2);

            String result = String.format(format, remark, enumName, code, desc);
            System.out.println(result);
        }
    }

    @Test
    public void genMdTest() {
        SegmentPosEnum[] segmentPosEnums = SegmentPosEnum.values();
        for(SegmentPosEnum segmentPosEnum : segmentPosEnums) {
            System.out.println(String.format("| %s | %s |", segmentPosEnum.code(), segmentPosEnum.desc()));
        }
    }

}
