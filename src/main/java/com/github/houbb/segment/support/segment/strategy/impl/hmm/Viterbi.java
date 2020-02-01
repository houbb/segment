package com.github.houbb.segment.support.segment.strategy.impl.hmm;

/**
 * 维特比算法
 * @author binbin.hou
 * @since 0.1.0
 */
public final class Viterbi {

    private Viterbi(){}

    /**
     * 求解HMM模型
     *
     * @param obs       观测序列
     * @param states    隐状态
     * @param startProb 初始概率（隐状态）
     * @param transProb 转移概率（隐状态）
     * @param emitProb  发射概率 （隐状态表现为显状态的概率）
     * @return 最可能的序列
     */
    static int[] compute(int[] obs, int[] states, double[] startProb,
                          double[][] transProb, double[][] emitProb) {
        double[][] V = new double[obs.length][states.length];
        int[][] path = new int[states.length][obs.length];

        for (int y : states) {
            V[0][y] = startProb[y] * emitProb[y][obs[0]];
            path[y][0] = y;
        }

        for (int t = 1; t < obs.length; ++t) {
            int[][] newPath = new int[states.length][obs.length];

            for (int y : states) {
                double prob = -1;
                int state;
                for (int y0 : states) {
                    //V[t,隐状态Sj]  = V[t-1,隐状态Si] * transProb(隐状态Si->隐状态Sj) * emitProb(obs[t]  | 隐状态Si)
                    double nprob = V[t - 1][y0] * transProb[y0][y] * emitProb[y][obs[t]];
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

        double prob = -1;
        int state = 0;
        for (int y : states) {
            //最后一步v值决定最大可能隐状态序列
            if (V[obs.length - 1][y] > prob) {
                prob = V[obs.length - 1][y];
                state = y;
            }
        }

        return path[state];
    }

}
