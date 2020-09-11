package com.springboot.netty.tcp.solution;

import java.io.Serializable;

/**
 * 自定义协议内容
 * 协议包
 */
public class MessageProtocol implements Serializable {


    private int len;  //关键
    private byte[] content;

    /**
     * @return the len
     */
    public int getLen() {
        return len;
    }

    /**
     * @param len the len to set
     */
    public void setLen(int len) {
        this.len = len;
    }

    /**
     * @return the content
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(byte[] content) {
        this.content = content;
    }
}
