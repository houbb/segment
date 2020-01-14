# Segment

更加简单，灵活的分词实现。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/segment/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/segment)
[![Coverage Status](https://coveralls.io/repos/github/houbb/segment/badge.svg)](https://coveralls.io/github/houbb/segment)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/segment/blob/master/LICENSE.txt)

> [变更日志](CHANGELOG.md)

## 创作目的

分词是做 NLP 相关工作，非常基础的一项功能。

[jieba-analysis](https://github.com/huaban/jieba-analysis) 作为一款非常受欢迎的分词实现，我个人实现的 [opencc4j](https://github.com/houbb/opencc4j) 之前一直使用其作为分词。

但是随着对分词的了解，发现结巴分词对于一些配置上不够灵活。有很多功能无法指定关闭，比如 HMM 对于繁简体转换是无用的，因为繁体词是固定的，不需要预测。

最新版本的词性等功能好像也被移除了，但是这些都是个人非常需要的。

所以自己重新实现了一遍，希望实现一套更加灵活，更多特性的分词框架。

## Features 特点

- 基于 DFA 实现的高性能分词

- 允许用户自定义词库

- 更加简单灵活的实现

# 快速入门

## 准备

jdk1.7+

maven 3.x+

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>segment</artifactId>
    <version>${最新版本}</version>
</dependency>
```

## 使用示例



```java

```


# Benchmark 性能对比


# 后期 Road-Map

- 增加分治算法处理词频

- 增加 HMM 算法实现新词预测

- 繁简体处理

# 创作感谢


