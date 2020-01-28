# 面向开发者的引导类

相比较于面向用户的工具类静态方法，引导类更加灵活优雅。

不需要提供一个又一个指定不同参数的方法，基于 fluent-api 设计。

## 注意

为了适应后期的特性，引导类相对可能会变化。

相关代码参见 [SegmentBsTest.java](https://github.com/houbb/segment/blob/master/src/test/java/com/github/houbb/segment/test/bs/SegmentBsTest.java)

## 获取分词，下标等信息

暂时没有实现词性标注，准备下个版本实现。

```java
final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

List<ISegmentResult> resultList = SegmentBs.newInstance().segment(string);
Assert.assertEquals("[这[0,1), 是[1,2), 一个[2,4), 伸手不见五指[4,10), 的[10,11), 黑夜[11,13), 。[13,14), 我[14,15), 叫[15,16), 孙悟空[16,19), ，[19,20), 我[20,21), 爱[21,22), 北京[22,24), ，[24,25), 我[25,26), 爱[26,27), 学习[27,29), 。[29,30)]", resultList.toString());
```

## 只获取分词信息

```java
final String string = "这是一个伸手不见五指的黑夜。我叫孙悟空，我爱北京，我爱学习。";

List<String> resultList = SegmentBs.newInstance().segment(string, SegmentResultHandlers.word());
Assert.assertEquals("[这, 是, 一个, 伸手不见五指, 的, 黑夜, 。, 我, 叫, 孙悟空, ，, 我, 爱, 北京, ，, 我, 爱, 学习, 。]", resultList.toString());
```

其中 `SegmentResultHandlers` 用来指定对于分词结果的处理实现，便于保证 api 的统一性。

| 方法 | 实现 | 说明 |
|:---|:---|:---|
| `common()` | SegmentResultHandler | 默认实现，返回 ISegmentResult 列表 |
| `word()` | SegmentResultWordHandler | 只返回分词字符串列表 |

## 返回词性

直接指定 `wordType` 属性为真即可。

```java
final String string = "我爱学习";

List<ISegmentResult> resultList = SegmentBs
                .newInstance()
                .wordType(WordTypes.first())
                .segment(string);

Assert.assertEquals("[我[0,1)/r, 爱[1,2)/v, 学习[2,4)/v]", resultList.toString());
```

其中 `r`、`v` 就是词性，代表的含义参见[词性说明](https://github.com/houbb/segment/blob/master/doc/user/word_type.md)。

或者参考枚举类 [WordTypeEnum](https://github.com/houbb/segment/blob/master/src/main/java/com/github/houbb/segment/constant/enums/WordTypeEnum.java)
