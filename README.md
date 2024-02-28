# Segment

[Segment](https://github.com/houbb/segment) 是基于结巴分词词库实现的更加灵活，高性能的 java 分词实现。

愿景：成为 java 最好用的分词工具。

[![Build Status](https://travis-ci.com/houbb/segment.svg?branch=master)](https://travis-ci.com/houbb/segment)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/segment/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/segment)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/segment/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/segment)

> [在线体验](https://houbb.github.io/opensource/segment)

## 创作目的

分词是做 NLP 相关工作，非常基础的一项功能。

[jieba-analysis](https://github.com/huaban/jieba-analysis) 作为一款非常受欢迎的分词实现，个人实现的 [opencc4j](https://github.com/houbb/opencc4j) 之前一直使用其作为分词。

但是随着对分词的了解，发现结巴分词对于一些配置上不够灵活。

（1）有很多功能无法指定关闭，比如 HMM 对于繁简体转换是无用的，因为繁体词是固定的，不需要预测。

（2）最新版本的词性等功能好像也被移除了，但是这些都是个人非常需要的。

（3）对于中文繁体分词支持不友好。

所以自己重新实现了一遍，希望实现一套更加灵活，更多特性的分词框架。

而且 jieba-analysis 的更新似乎停滞了，个人的实现方式差异较大，所以建立了全新的项目。

## Features 特点

- 面向用户的极简静态 api 设计

- 面向开发者 fluent-api 设计，让配置更加优雅灵活

- 详细的中文代码注释，便于源码阅读

- 基于 DFA 实现的高性能分词

- 基于 HMM 的新词预测

- 支持不同的分词模式

- 支持全角半角/英文大小写/中文繁简体格式处理

- 允许用户自定义词库

- 简单的词性标注实现

- 支持字典等资源的主动释放

### v-0.3.0 最新变更

- 新增 wordCount 工具方法

> [变更日志](https://github.com/houbb/segment/blob/master/CHANGELOG.md)

# 快速入门

## 准备

jdk1.7+

maven 3.x+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>segment</artifactId>
    <version>0.3.0</version>
</dependency>
```

相关代码参见 [SegmentHelperTest.java](https://github.com/houbb/segment/blob/master/src/test/java/com/github/houbb/segment/test/util/SegmentHelperTest.java)

## 默认分词示例

返回分词，下标等信息。

```java
final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

List<ISegmentResult> resultList = SegmentHelper.segment(string);
Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList.toString());
```

## 指定返回形式

有时候我们根据自己的应用场景，需要选择不同的返回形式。

`SegmentResultHandlers` 用来指定对于分词结果的处理实现，便于保证 api 的统一性。

| 方法 | 实现 | 说明                         |
|:---|:---|:---------------------------|
| `common()` | SegmentResultHandler | 默认实现，返回 ISegmentResult 列表  |
| `word()` | SegmentResultWordHandler | 只返回分词字符串列表                 |
| `wordCount()` | SegmentResultWordHandler | key: 分词字符串; value: 分词出现的次数 |

### 默认模式

默认分词形式，等价于下面的写法

```java
List<ISegmentResult> resultList = SegmentHelper.segment(string, SegmentResultHandlers.common());
```

### 只获取分词信息

```java
final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

List<String> resultList = SegmentHelper.segment(string, SegmentResultHandlers.word());
Assert.assertEquals("[这是, 一个, 伸手不见五指, 的, 黑夜, 。, 我, 叫, 孙悟空, ，, 我爱, 北京, ，, 我爱, 学习, 。]", resultList.toString());
```

### 统计分词出现的次数

我们通过 `SegmentResultHandlers.wordCount()` 指定统计出现次数的方法。

```java
final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

Map<String, Integer> wordCount = SegmentHelper.segment(string, SegmentResultHandlers.wordCount());
Assert.assertEquals(2, wordCount.get("我爱").intValue());
Assert.assertEquals(1, wordCount.get("黑夜").intValue());
```

当然，这个在相似度等计算中出现频率较高。

因此提供工具方法 `SegmentHelper.wordCount()`，上面的方法等价于：

```java
Map<String, Integer> wordCount = SegmentHelper.wordCount(string);
```

# 引导类

## 说明

针对灵活的配置，引入了 `SegmentBs` 作为引导类，解决工具类方法配置参数过多的问题。

## 示例如下

```java
final String text = "自定义一个很长的分词，开心！";

List<ISegmentResult> resultList = SegmentBs.newInstance()
        // 分词实现策略
        .segment(Segments.defaults())
        // 分词词组数据
        .segmentData(SegmentPhraseDatas.mixed())
        // 分词模式
        .segmentMode(SegmentModes.dict())
        // 格式化处理
        .segmentFormat(SegmentFormats.defaults())
        // 词性标注实现
        .posTagging(SegmentPosTaggings.simple())
        // 词性标注数据
        .posData(SegmentPosDatas.mixed())
        // 对文本进行分词处理
        .segment(text, SegmentResultHandlers.common());
Assert.assertEquals("[自定义一个很长的分词[0,10)/un, ，[10,11)/un, 开心[11,13)/a, ！[13,14)/un]", resultList.toString());
```

所有的内置方法都是基于接口，可以自行定义实现。

# 分词模式

## 分词模式简介

分词模式可以通过类 `SegmentModes` 工具类获取。

| 序号 | 方法 | 准确度 | 性能 | 备注 |
|:---|:---|:---|:---|:---|
| 1 | search() | 高 | 一般 | 结巴分词的默认模式 |
| 2 | dict() | 较高 | 一般 | 和 search 模式类似，但是缺少 HMM 新词预测 |
| 3 | index() | 一般 | 高 | 尽可能多的返回词组信息，提高召回率 |
| 4 | greedyLength() | 一般 | 高 | 贪心最大长度匹配，对准确度要求不高时可采用。 |

## 使用方式

针对灵活的配置，引入了 `SegmentBs` 作为引导类，解决工具类方法配置参数过多的问题。

测试代码参见 [SegmentModeTest.java](https://github.com/houbb/segment/blob/master/src/test/java/com/github/houbb/segment/test/bs/SegmentBsModeTest.java)

## search 模式

`segmentMode()` 指定分词模式，不指定时默认就是 `SegmentModes.search()`。

```java
final String string = "这是一个伸手不见五指的黑夜。";

List<ISegmentResult> resultList = SegmentBs.newInstance()
       .segmentMode(SegmentModes.search())
       .segment(string);

Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
```

## dict 模式

只依赖词库实现分词，没有 HMM 新词预测功能。

```java
final String string = "这是一个伸手不见五指的黑夜。";

List<ISegmentResult> resultList = SegmentBs.newInstance()
        .segmentMode(SegmentModes.dict())
        .segment(string);
Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
```

## index 模式

这里主要的区别就是会返回 `伸手`、`伸手不见` 等其他词组。

```java
final String string = "这是一个伸手不见五指的黑夜。";

List<ISegmentResult> resultList = SegmentBs.newInstance()
        .segmentMode(SegmentModes.index())
        .segment(string);
Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手[4,6), 伸手不见[4,8), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
```

## GreedyLength 模式

这里使用贪心算法实现，准确率一般，性能较好。

```java
final String string = "这是一个伸手不见五指的黑夜。";

List<ISegmentResult> resultList = SegmentBs.newInstance()
        .segmentMode(SegmentModes.greedyLength())
        .segment(string);
Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14)]", resultList.toString());
```

# 格式化处理

## 格式化接口

可以通过 `SegmentFormats` 工具类获取对应的格式化实现，在分词时指定即可。

| 序号 | 方法 | 名称 | 说明 |
|:---|:---|:---|:---|
| 1 | defaults() | 默认格式化 | 等价于小写+半角处理。 |
| 2 | lowerCase() | 字符小写格式化 | 英文字符处理时统一转换为小写 |
| 3 | halfWidth() | 字符半角格式化 | 英文字符处理时统一转换为半角 |
| 4 | chineseSimple() | 中文简体格式化 |  用于支持繁体中文分词 |
| 5 | none() | 无格式化 |  无任何格式化处理 |
| 6 | chains(formats) | 格式化责任链 | 你可以针对上述的格式化自由组合，同时允许自定义格式化。 |

## 默认格式化

全角半角+英文大小写格式化处理，默认开启。

这里的 `Ｑ` 为全角大写，默认会被转换处理。

```java
String text = "阿Ｑ精神";
List<ISegmentResult> segmentResults = SegmentHelper.segment(text);

Assert.assertEquals("[阿Ｑ[0,2), 精神[2,4)]", segmentResults.toString());
```

## 中文繁体分词

无论是结巴分词还是当前框架，默认对繁体中文的分词都不友好。

### 默认分词示例

显然和简体中文的分词形式不同。

```java
String text = "這是一個伸手不見五指的黑夜";

List<String> defaultWords = SegmentBs.newInstance()
        .segment(text, SegmentResultHandlers.word());
Assert.assertEquals("[這是, 一, 個, 伸手, 不見, 五指, 的, 黑夜]", defaultWords.toString());
```

### 启用中文繁体分词

指定分词中文格式化，可以得到符合我们预期的分词。

```java
String text = "這是一個伸手不見五指的黑夜";

List<String> defaultWords = SegmentBs.newInstance()
        .segmentFormat(SegmentFormats.chineseSimple())
        .segment(text, SegmentResultHandlers.word());
Assert.assertEquals("[這是, 一個, 伸手不見五指, 的, 黑夜]", defaultWords.toString());
```

## 格式化责任链

格式化的形式可以有很多，我们可以根据自己的需求自由组合。

比如我们想同时启用默认格式化+中文简体格式化。

```java
final String text = "阿Ｑ，這是一個伸手不見五指的黑夜";

List<String> defaultWords = SegmentBs.newInstance()
        .segmentFormat(SegmentFormats.chains(SegmentFormats.defaults(),
                SegmentFormats.chineseSimple()))
        .segment(text, SegmentResultHandlers.word());
Assert.assertEquals("[阿Ｑ, ，, 這是, 一個, 伸手不見五指, 的, 黑夜]", defaultWords.toString());
```

# 自定义词库

为了适应更多的应用场景，segment 支持自定义词典。

## 定义方式

`resources` 或者项目根目录新建文件 `segment_phrase_dict_define.txt`

要求编码：UTF-8

内容格式如下：

```
彩霞 78 n
```

第一个词是我们自定义的词，必填。

第二个为这个词出现的词频，选填，默认为 3。

第三个为词性，选填，默认为 un。（未知）

三者用英文空格（` `）隔开。

## 优先级

用户自定义的词优先级更高，会覆盖系统原有的相同词。

# 词性标注

## 说明

目前支持最简单版本的词性标注，暂定为 alpha 版本，后续引入基于 HMM 实现的词性标注。

## 使用例子

```java
final String string = "这是一个伸手不见五指的黑夜。";

List<ISegmentResult> resultList = SegmentBs.newInstance()
        .posTagging(SegmentPosTaggings.simple())
        .segment(string);

Assert.assertEquals("[这是[0,2)/un, 一个[2,4)/mq, 伸手不见五指[4,10)/i, 的[10,11)/ude1, 黑夜[11,13)/n, 。[13,14)/w]", resultList.toString());
```

# 主动释放资源 

## 说明

分词是基于字典实现的，为了提升性能，字典初始化之后会缓存到内存中。 这对于 java web 服务端是没有太大问题的。

有安卓客户端小伙伴反应，希望分词使用一次之后，可以主动释放资源。

此功能为此而实现。

## 使用

方法在引导类中可以使用，如下：

```java
// 初始化引导类
final SegmentBs segmentBs = SegmentBs.newInstance();

// 主动释放资源
segmentBs.destroy();
```

### 例子

实际例子：

```java
// 基本特性
final SegmentBs segmentBs = SegmentBs.newInstance();

final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";
List<ISegmentResult> resultList = segmentBs.segment(string);
Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList.toString());

// 资源释放
segmentBs.destroy();

// 重新处理
List<ISegmentResult> resultList2 = segmentBs.segment(string);
Assert.assertEquals("[这是[0,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我爱[20,22), 北京[22,24), ，[24,25), 我爱[25,27), 学习[27,29), 。[29,30)]", resultList2.toString());
```

为了便于使用，**资源释放之后，如果再次分词，会重新初始化相关资源**。

### 日志

为了便于研发观察，自适应日志输出对应的字典加载和销毁信息。

格式如下：

```
[DEBUG] [2023-03-23 11:16:53.010] [main] [c.g.h.s.s.t.i.SegmentTrieTree.getTrieTree] - [Segment]-[data-trie] init start
[DEBUG] [2023-03-23 11:16:53.480] [main] [c.g.h.s.s.t.i.SegmentTrieTree.getTrieTree] - [Segment]-[data-trie] init end
...

[DEBUG] [2023-03-23 11:16:53.543] [main] [c.g.h.s.s.t.i.SegmentTrieTree.destroy] - [Segment]-[data-trie] destroy start
[DEBUG] [2023-03-23 11:16:53.543] [main] [c.g.h.s.s.t.i.SegmentTrieTree.destroy] - [Segment]-[data-trie] destroy end
...
```

# Benchmark 性能对比

## 性能对比

性能对比基于 jieba 1.0.2 版本，测试条件保持一致，保证二者都做好预热，然后统一处理。

验证下来，默认模式性能略优于 jieba 分词，贪心模式是其性能 3 倍左右。

备注：

（1）默认模式和结巴 Search 模式一致。

后期考虑 HMM 也可以配置是否开启，暂定为默认开启

（2）后期将引入多线程提升性能。

代码参见 [BenchmarkTest.java](https://github.com/houbb/segment/blob/master/src/test/java/com/github/houbb/segment/test/benchmark/BenchmarkTest.java)

## 性能对比图

相同长文本，循环 1W 次耗时。（Less is Better）

![benchmark](https://github.com/houbb/segment/blob/master/benchmark.png)

# 后期 Road-Map

## 核心特性

- HMM 词性标注

- HMM 实体标注

- CRF 算法实现

- N 元组算法实现

## 优化

- 多线程的支持，性能优化

- 双数组 DFA 实现，降低内存消耗

# 创作感谢

感谢 [jieba](https://github.com/fxsjy/jieba) 分词提供的词库，以及 [jieba-analysis](https://github.com/huaban/jieba-analysis) 的相关实现。

# NLP 开源矩阵

[pinyin 汉字转拼音](https://github.com/houbb/pinyin)

[pinyin2hanzi 拼音转汉字](https://github.com/houbb/pinyin2hanzi)

[segment 高性能中文分词](https://github.com/houbb/segment)

[opencc4j 中文繁简体转换](https://github.com/houbb/opencc4j)

[nlp-hanzi-similar 汉字相似度](https://github.com/houbb/nlp-hanzi-similar)

[word-checker 拼写检测](https://github.com/houbb/word-checker)

[sensitive-word 敏感词](https://github.com/houbb/sensitive-word)
