package com.kangyonggan.server.model.dto;

import com.github.pagehelper.PageInfo;
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
}
