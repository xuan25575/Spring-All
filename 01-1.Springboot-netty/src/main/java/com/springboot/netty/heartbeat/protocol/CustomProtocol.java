package com.springboot.netty.heartbeat.protocol;

import java.io.Serializable;

/**
 *
 * 自定义协议
 * 服务端与客户端采用的是自定义的 POJO 进行通讯的。
 * 所以需要在客户端进行编码，服务端进行解码，也都只需要各自实现一个编解码器即可。
 */
public class CustomProtocol implements Serializable {

    private static final long serialVersionUID = 4671171056588401542L;
    private long id ;
    private String content ;

    public CustomProtocol() {
    }

    public CustomProtocol(long id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CustomProtocol{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
