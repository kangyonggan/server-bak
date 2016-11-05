package com.kangyonggan.server.service;


import com.kangyonggan.server.model.Category;
import com.kangyonggan.server.model.dto.ResponseDto;

/**
 * @author kangyonggan
 * @since 16/10/11
 */
public interface CategoryService {

    /**
     * 根据栏目代码查询
     *
     * @param code
     * @return
     */
    ResponseDto<Category> findCategoryByCode(String code);

    /**
     * 查找所有栏目
     *
     * @return
     */
    ResponseDto<Category> findAllCategories();

}
