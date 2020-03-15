package com.github.houbb.segment.constant.enums;

/**
 * 分词类型枚举
 * @author binbin.hou
 * @since 0.1.4
 */
public enum SegmentPosEnum {

    /**
     * 枚举描述
     * @since 0.1.4
     */
    N("n", "名词"),
    NR("nr", "人名"),
    NR1("nr1", "汉语姓氏"),
    NR2("nr2", "汉语名字"),
    NRJ("nrj", "日语人名"),
    NRF("nrf", "音译人名"),
    NS("ns", "地名"),
    NSF("nsf", "音译地名"),
    NT("nt", "机构团体名"),
    NZ("nz", "其它专名"),
    NL("nl", "名词性惯用语"),
    NG("ng", "名词性语素"),
    NW("nw", "新词"),
    T("t", "时间词"),
    TG("tg", "时间词性语素"),
    S("s", "处所词"),
    F("f", "方位词"),
    V("v", "动词"),
    VD("vd", "副动词"),
    VN("vn", "名动词"),
    VSHI("vshi", "动词“是”"),
    VYOU("vyou", "动词“有”"),
    VF("vf", "趋向动词"),
    VX("vx", "形式动词"),
    VI("vi", "不及物动词（内动词）"),
    VL("vl", "动词性惯用语"),
    VG("vg", "动词性语素"),
    A("a", "形容词"),
    AD("ad", "副形词"),
    AN("an", "名形词"),
    AG("ag", "形容词性语素"),
    AL("al", "形容词性惯用语"),
    B("b", "区别词"),
    BL("bl", "区别词性惯用语"),
    Z("z", "状态词"),
    R("r", "代词"),
    RR("rr", "人称代词"),
    RZ("rz", "指示代词"),
    RZT("rzt", "时间指示代词"),
    RZS("rzs", "处所指示代词"),
    RZV("rzv", "谓词性指示代词"),
    RY("ry", "疑问代词"),
    RYT("ryt", "时间疑问代词"),
    RYS("rys", "处所疑问代词"),
    RYV("ryv", "谓词性疑问代词"),
    RG("rg", "代词性语素"),
    M("m", "数词"),
    MQ("mq", "数量词"),
    Q("q", "量词"),
    QV("qv", "动量词"),
    QT("qt", "时量词"),
    D("d", "副词"),
    P("p", "介词"),
    PBA("pba", "介词“把”"),
    PBEI("pbei", "介词“被”"),
    C("c", "连词"),
    CC("cc", "并列连词"),
    U("u", "助词"),
    UZHE("uzhe", "着"),
    ULE("ule", "了"),
    UGUO("uguo", "过"),
    UDE1("ude1", "的"),
    UDE2("ude2", "地"),
    UDE3("ude3", "得"),
    USUO("usuo", "所"),
    UDENG("udeng", "等"),
    UYY("uyy", "一样"),
    UDH("udh", "的话"),
    ULS("uls", "来讲"),
    UZHI("uzhi", "之"),
    ULIAN("ulian", "连"),
    E("e", "叹词"),
    Y("y", "语气词(delete"),
    O("o", "拟声词"),
    H("h", "前缀"),
    K("k", "后缀"),
    X("x", "字符串"),
    XX("xx", "非语素字"),
    XU("xu", "网址URL"),
    W("w", "标点符号"),
    WKZ("wkz", "左括号，全角：（"),
    WKY("wky", "右括号，全角：）"),
    WYZ("wyz", "左引号，全角：“"),
    WYY("wyy", "右引号，全角：”"),
    WJ("wj", "句号，全角：。"),
    WW("ww", "问号，全角：？"),
    WT("wt", "叹号，全角：！"),
    WD("wd", "逗号，全角：，"),
    WF("wf", "分号，全角：；"),
    WN("wn", "顿号，全角：、"),
    WM("wm", "冒号，全角：："),
    WS("ws", "省略号，全角：……"),
    WP("wp", "破折号，全角：——"),
    WB("wb", "百分号千分号，全角：％"),
    WH("wh", "单位符号，全角：￥"),
    UN("un", "未知"),
    ;

    /**
     * 编码
     * @since 0.0.1
     */
    private final String code;

    /**
     * 描述
     * @since 0.0.1
     */
    private final String desc;

    SegmentPosEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    @Override
    public String toString() {
        return "SegmentPosEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

}
