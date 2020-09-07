package com.springboot.schedule.common;

import java.io.Serializable;


/**
 *  通用的返回对象.
 * @param <T>
 */
public class GeneralResult<T> implements Serializable {
    private static final long serialVersionUID = 6511269520709785304L;
    private String status;
    private String message;
    private T payload;



    public GeneralResult(String status, String message, T payload) {
        this.setStatus(status);
        this.setMessage(message);
        this.setPayload(payload);
    }
    public GeneralResult(String status, T payload) {
        this.setStatus(status);
        this.setPayload(payload);
    }
    public GeneralResult(String status, String message) {
        this.setStatus(status);
        this.setMessage(message);
    }


    public static <T> GeneralResult success(){
        return new GeneralResult("200","success");
    }

    public static GeneralResult failure(String message){
        return new GeneralResult("400",message);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return this.payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}

