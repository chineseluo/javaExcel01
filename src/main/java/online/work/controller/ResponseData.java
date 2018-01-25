/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package online.work.controller;

import java.io.Serializable;

public class ResponseData implements Serializable {
    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "-1";
    private String status = SUCCESS_CODE;
    private Object data;

    public boolean isSuccess() {
        return this.status.equalsIgnoreCase(SUCCESS_CODE) ? true : false;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setError(Object data) {
        this.status = ERROR_CODE;
        this.data = data;
    }

    public void setError(String status, Object data) {
        this.status = status;
        this.data = data;
        System.out.println("status:"+status);
        System.out.println("data:"+data);
    }
}

