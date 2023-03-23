package com.github.houbb.segment.support.viterbi;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.log.integration.core.Log;
import com.github.houbb.log.integration.core.LogFactory;
import com.github.houbb.segment.constant.SegmentConst;
import com.github.houbb.segment.constant.enums.HmmStateEnum;

import java.util.List;
import java.util.Map;

/**
 * 维特比算法
 *
 * @author binbin.hou
 * @since 0.1.0
 */
public class Viterbi implements IViterbi {

    public Viterbi(){}

    private static final Log LOG = LogFactory.getLog(Viterbi.class);

    /**
     * 最小值
     *
     * （这里选择和结巴分词相同的分词概率。）
     * @since 0.1.0
     */
    private static final double MIN_FLOAT = -3.14e100;

    /**
     * 状态列表
     * B: 开始-0
     * M: 中间-1
     * E: 结束-2
     * S: 单个词-3
     *
     * @since 0.1.0
     */
    private static final int[] STATE_INDEX = {0, 1, 2, 3};

    /**
     * 初始化列表
     *
     * 1. 初始字符不可能是中间和结束。
     *
     * @since 0.1.0
     */
    private static final double[] START_PROB = {-0.26268660809250016, MIN_FLOAT,
            MIN_FLOAT, -1.4652633398537678};

    /**
     * 转换状态列表
     * BMES 之间的转换概率。
     * @since 0.1.0
     */
    private static final double[][] TRANS_PROB = {
            {MIN_FLOAT, -0.916290731874155, -0.510825623765990, MIN_FLOAT},
            {MIN_FLOAT, -1.2603623820268226, -0.33344856811948514, MIN_FLOAT},
            {MIN_FLOAT, 0.5897149736854513, MIN_FLOAT, -0.8085250474669937},
            {-0.7211965654669841, MIN_FLOAT, MIN_FLOAT, -0.6658631448798212}
    };

    /**
     * B 0
     * M 1
     * E 2
     * S 3
     * 当前状态的上一个可能的状态
     * @since 0.1.0
     */
    private static final int[][] PREV_STATUS = {
            {2, 3},
            {1, 0},
            {0, 1},
            {3, 2}
    };

    /**
     * 发射概率 map
     * @since 0.1.0
     */
    private static volatile Map<Integer, Map<Character, Double>> emitPropMap;

    /**
     * 执行分词
     * @param text 文本
     * @param formatText 格式化文本
     * @return 分词后的结束下标列表
     * @since 0.1.0
     */
    public static List<String> segment(final String text, final String formatText) {
        List<String> segmentList = Guavas.newArrayList();

        //1. fail-return
        if(text.length() == 1) {
            segmentList.add(text);
            return segmentList;
        }

        // 观察字符串序列
        char[] observerChars = formatText.toCharArray();
        int[] stateIndexArray = compute(observerChars, STATE_INDEX, START_PROB, TRANS_PROB);

        // 根据状态，构建最后的结果列表
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < text.length(); i++) {
            int stateIndex = stateIndexArray[i];

            stringBuilder.append(text.charAt(i));
            if(HmmStateEnum.SINGLE.index() == stateIndex
                || HmmStateEnum.END.index() == stateIndex) {
                String word = stringBuilder.toString();
                segmentList.add(word);

                // 清空长度
                stringBuilder.setLength(0);
            }
        }

        // 处理最后的结果
        if(stringBuilder.length() > 0) {
            String word = stringBuilder.toString();
            segmentList.add(word);
            stringBuilder.setLength(0);
        }

        return segmentList;
    }

    /**
     * 初始化发射概率 map
     * DCL 初始化 map
     * @return 结果 map
     * @since 0.1.0
     */
    private static Map<Integer, Map<Character, Double>> getEmitPropMap() {
        if(MapUtil.isNotEmpty(emitPropMap)) {
            return emitPropMap;
        }

        LOG.debug("[Segment]-[data veterbi] init start");
        synchronized (Viterbi.class) {
            if(MapUtil.isEmpty(emitPropMap)) {
                // 初始化
                emitPropMap = Guavas.newHashMap(4);
                emitPropMap.put(0, Guavas.<Character, Double>newHashMap());
                emitPropMap.put(1, Guavas.<Character, Double>newHashMap());
                emitPropMap.put(2, Guavas.<Character, Double>newHashMap());
                emitPropMap.put(3, Guavas.<Character, Double>newHashMap());

                List<String> lines = StreamUtil.readAllLines(SegmentConst.SEGMENT_EMIT_PROB_PATH);

                for(String line : lines) {
                    String[] entries = line.split(StringUtil.BLANK);
                    Integer index = Integer.valueOf(entries[0]);
                    Character character = entries[1].charAt(0);
                    Double prob = Double.valueOf(entries[2]);

                    // 存放对应的概率
                    emitPropMap.get(index).put(character, prob);
                }
            }
        }
        LOG.debug("[Segment]-[data veterbi] init end");
        return emitPropMap;
    }


    /**
     * 求解HMM模型
     *
     * 发射矩阵是稀疏的，如果不存在，默认返回 {@link #MIN_FLOAT}
     *
     * ps:
     *
     * （1）感觉结巴分词的处理很奇怪，所有的乘法都变成了加法。
     * （2）很多值被标准化为负值，使用乘法会导致值变得很大。
     *
     * 这样降低了计算的消耗，但是精确度如何保证？
     * 感觉首先需要知道对应的词库数据的来源。
     * @param observerChars       观测序列
     * @param states    隐状态
     * @param startProb 初始概率（隐状态）
     * @param transProb 转移概率（隐状态）
     * @return 最可能的序列
     */
    private static int[] compute(char[] observerChars, int[] states, double[] startProb,
                                 double[][] transProb) {
        double[][] V = new double[observerChars.length][states.length];
        int[][] path = new int[states.length][observerChars.length];

        for (int y : states) {
            final double emitProb = getEmitProb(y, observerChars[0]);
            // 这里用 + 替换乘法
            V[0][y] = startProb[y] + emitProb;
            path[y][0] = y;
        }

        for (int t = 1; t < observerChars.length; ++t) {
            int[][] newPath = new int[states.length][observerChars.length];

            for (int y : states) {
                double prob = MIN_FLOAT;
                int state;

                // 上一个状态列表
                int[] prevStates = PREV_STATUS[y];
                for (int y0 : prevStates) {
                    //V[t,隐状态Sj]  = V[t-1,隐状态Si] * transProb(隐状态Si->隐状态Sj) * emitProbMap(observerChars[t]  | 隐状态Si)
                    final double emitProb = getEmitProb(y, observerChars[t]);
                    // 这里用 + 替换乘法
                    double nprob = V[t - 1][y0] + transProb[y0][y] + emitProb;
                    if (nprob > prob) {
                        prob = nprob;
                        state = y0;
                        // 记录最大概率
                        V[t][y] = prob;
                        // 记录路径
                        System.arraycopy(path[state], 0, newPath[y], 0, t);
                        newPath[y][t] = y;
                    }
                }
            }

            path = newPath;
        }

        double prob = MIN_FLOAT;
        int state = 0;
        for (int y : states) {
            //最后一步v值决定最大可能隐状态序列
            if (V[observerChars.length - 1][y] > prob) {
                prob = V[observerChars.length - 1][y];
                state = y;
            }
        }

        return path[state];
    }

    /**
     * 获取发射概率
     * 1. 已有的词库概率是稀疏的，会存在没有的词的情形。
     * @param stateIndex 状态下标
     * @param ch 中文字符
     * @return 结果
     * @since 0.1.0
     */
    private static double getEmitProb(final int stateIndex,
                               final char ch) {
        emitPropMap = getEmitPropMap();

        Map<Character, Double> probMap = emitPropMap.get(stateIndex);

        Double prob = probMap.get(ch);
        if(ObjectUtil.isNotNull(prob)) {
            return prob;
        }

        return MIN_FLOAT;
    }

    @Override
    public List<String> viterbi(String text, String format) {
        return segment(text, format);
    }

    @Override
    public void destroy() {
        LOG.debug("[Segment]-[data veterbi] destroy start");

        synchronized (emitPropMap) {
            emitPropMap = Guavas.newHashMap();
        }

        LOG.debug("[Segment]-[data veterbi] destroy start");
    }

}
