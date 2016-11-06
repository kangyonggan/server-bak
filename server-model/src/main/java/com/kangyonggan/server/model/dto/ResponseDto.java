package com.kangyonggan.server.model.dto;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.server.model.AppConstants;
import com.kangyonggan.server.model.BaseModel;
import lombok.Data;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/11/5
 */
@Data
public class ResponseDto<T> extends BaseModel {

    /**
     * 响应码
     */
    private String respCode;

    /**
     * 响应消息
     */
    private String respMsg;

    /**
     * 响应数据
     */
    private T entity;

    /**
     * 响应数据
     */
    private List<T> data;

    /**
     * 响应数据
     */
    private PageInfo<T> page;

    public ResponseDto() {

    }

    public ResponseDto(T entity) {
        this.entity = entity;
        respCode = AppConstants.RESP_SUCCESS;
        respMsg = "请求成功";
    }

    public ResponseDto(List<T> data) {
        this.data = data;
        respCode = AppConstants.RESP_SUCCESS;
        respMsg = "请求成功";
    }

    public ResponseDto(PageInfo<T> page) {
        this.page = page;
        respCode = AppConstants.RESP_SUCCESS;
        respMsg = "请求成功";
    }
}
