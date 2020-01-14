Nlp.

# 分词

## 词库

简体中文词库，不关心词性。

支持最大匹配模式，最快返回模式。

精确，用户，浏览器模式。

## 可替换性

字典的可替换性。

## 并行

# 繁简体

分词与繁简体之间的关系？

直接全部对应为简体，然后进行分词。
分词的实现策略：
1. 根据贪心算法获取（跳过 dag hmm 环节）
2. 根据简体（繁体）词组构成前缀树
3. 前缀树内存占用较多问题。词语有个特征，2-4个字，能否直接利用集合判断。
4. 性能进一步提升。繁简体转换时，直接根据3构建对应的 key，减少一次判断。
5. 繁体简体混合在一起的场景。（最后考虑）

不改变以前的接口方法。

1. 繁简体分词分离。

2. 使用动态规划（分治算法）提升性能

3. 加入多线程

4. 当文本的内容较多时，进行拆分。

# 大小写

# 全角半角

# 数字格式

# 英文格式

# 停止词


# 词性

# 词频统计

主要词 摘要。

数量统计 频率统计