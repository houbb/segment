# Segment

[Segment](https://github.com/houbb/segment ) 是基于结巴分词词库实现的更加灵活，高性能的 java 分词实现。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/segment/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/segment)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/segment/blob/master/LICENSE.txt)

> [变更日志](https://github.com/houbb/segment/blob/master/CHANGELOG.md)

## 创作目的

分词是做 NLP 相关工作，非常基础的一项功能。

[jieba-analysis](https://github.com/huaban/jieba-analysis) 作为一款非常受欢迎的分词实现，个人实现的 [opencc4j](https://github.com/houbb/opencc4j) 之前一直使用其作为分词。

但是随着对分词的了解，发现结巴分词对于一些配置上不够灵活。

有很多功能无法指定关闭，比如 HMM 对于繁简体转换是无用的，因为繁体词是固定的，不需要预测。

最新版本的词性等功能好像也被移除了，但是这些都是个人非常需要的。

所以自己重新实现了一遍，希望实现一套更加灵活，更多特性的分词框架。

而且 jieba-analysis 的更新似乎停滞了，个人的实现方式差异较大，所以建立了全新的项目。

## Features 特点

- 极单的 api 设计，基于 fluent-api 让调用更加优雅

- 基于 DFA 实现的高性能分词

- 允许指定自定义词库

- 支持返回词性

默认关闭，惰性加载，不对性能和内存有影响。

# 快速入门

## 准备

jdk1.7+

maven 3.x+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>segment</artifactId>
    <version>0.0.4</version>
</dependency>
```

## 使用示例

相关代码参见 [SegmentBsTest.java](https://github.com/houbb/segment/blob/master/src/test/java/com/github/houbb/segment/test/bs/SegmentBsTest.java)

### 获取分词，下标等信息

暂时没有实现词性标注，准备下个版本实现。

```java
final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

List<ISegmentResult> resultList = SegmentBs.newInstance().segment(string);
Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我[20,21), 爱[21,22), 北京[22,24), ，[24,25), 我[25,26), 爱[26,27), 学习[27,29), 。[29,30)]", resultList.toString());
```

### 只获取分词信息

```java
final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

List<String> resultList = SegmentBs.newInstance().segment(string, SegmentResultHandlers.word());
Assert.assertEquals("[这, 是, 一个, 伸手不见五指, 的, 黑夜, 。, 我, 叫, 孙悟空, ，, 我, 爱, 北京, ，, 我, 爱, 学习, 。]", resultList.toString());
```

其中 `SegmentResultHandlers` 用来指定对于分词结果的处理实现，便于保证 api 的统一性。

| 方法 | 实现 | 说明 |
|:---|:---|:---|
| common() | SegmentResultHandler | 默认实现，返回 ISegmentResult 列表 |
| word() | SegmentResultWordHandler | 只返回分词字符串列表 |

# 返回词性

## 使用示例

直接指定 `wordType` 属性为真即可。

```java
final String string = "我爱学习";

List<ISegmentResult> resultList = SegmentBs
                .newInstance()
                .wordType(true)
                .segment(string);

Assert.assertEquals("[我[0,1)/r, 爱[1,2)/v, 学习[2,4)/v]", resultList.toString());
```

## 词性说明

r/v 就是词性，每一个代表的含义详情如下。

| 编码 | 描述 |
|:---|:---|
| Ag | 形语素 |
| a | 形容词 |
| ad | 副形词 |
| an | 名形词 |
| b | 区别词 |
| c | 连词 |
| dg | 副语素 |
| d | 副词 |
| e | 叹词 |
| f | 方位词 |
| g | 语素 |
| h | 前接成分 |
| i | 成语 |
| j | 简称略语 |
| k | 后接成分 |
| l | 习用语 |
| m | 数词 |
| Ng | 名语素 |
| n | 名词 |
| nr | 人名 |
| ns | 地名 |
| nt | 机构团体 |
| nz | 其他专名 |
| o | 拟声词 |
| p | 介词 |
| q | 量词 |
| r | 代词 |
| s | 处所词 |
| tg | 时语素 |
| t | 时间词 |
| u | 助词 |
| vg | 动语素 |
| v | 动词 |
| vd | 副动词 |
| vn | 名动词 |
| w | 标点符号 |
| x | 非语素字 |
| y | 语气词 |
| z | 状态词 |
| un | 未知词 |

可以参见对应的枚举类 [WordTypeEnum](https://github.com/houbb/segment/blob/master/src/main/java/com/github/houbb/segment/constant/enums/WordTypeEnum.java)

# Benchmark 性能对比

## 性能对比

性能对比基于 jieba 1.0.2 版本，测试条件保持一致，保证二者都做好预热，然后统一处理。

验证下来，分词的**性能是 jieba 的两倍左右**。

原因也很简单，暂时没有引入词频和 HMM。

代码参见 [BenchmarkTest.java](https://github.com/houbb/segment/blob/master/src/test/java/com/github/houbb/segment/test/benchmark/BenchmarkTest.java)

## 性能对比图

相同长文本，循环 1W 次耗时。（Less is Better）

![benchmark](https://github.com/houbb/segment/blob/master/benchmark.png)

# 后期 Road-Map

## 核心特性

- 基于词频修正

- HMM 算法实现新词预测

- 常见的分词模式

- 停顿词/人名/地名/机构名/数字... 各种常见的词性标注

## 格式处理

- 全角半角处理

- 繁简体处理

# 创作感谢

感谢 [jieba](https://github.com/fxsjy/jieba) 分词提供的词库，以及 [jieba-analysis](https://github.com/huaban/jieba-analysis) 的相关实现。