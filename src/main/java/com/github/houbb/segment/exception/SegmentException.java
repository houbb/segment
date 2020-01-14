package com.github.houbb.segment.exception;

/**
 * 分词运行时异常
 * @author binbin.hou
 * @since 0.0.1
 */
public class SegmentException extends RuntimeException {

    public SegmentException() {
    }

    public SegmentException(String message) {
        super(message);
    }

    public SegmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public SegmentException(Throwable cause) {
        super(cause);
    }

    public SegmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
