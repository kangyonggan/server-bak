package com.kangyonggan.server.model;

import java.io.Serializable;

/**
 * 系统常量
 *
 * @author kangyonggan
 * @since 2016/10/11
 */
public interface AppConstants extends Serializable {

    /**
     * 分页大小
     */
    int PAGE_SIZE = 10;

    /**
     * 是否删除
     */
    byte IS_DELETED_NO = 0;
    byte IS_DELETED_YES = 1;

    /**********响应码**********/
    /**
     * 成功
     */
    String RESP_SUCCESS = "S";
    /**
     * 失败
     */
    String RESP_FAILURE = "F";
    /**
     * 异常
     */
    String RESP_EXCEPTION = "E";
    /**
     * 处理中
     */
    String RESP_HANDLE_IN = "I";
    /**********响应码**********/

}
