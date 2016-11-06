package com.kangyonggan.server.biz.service;

import com.kangyonggan.server.model.Category;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/11/6
 */
public interface ServerCategoryService {

    /**
     * 保存栏目
     *
     * @param category
     */
    void saveCategory(Category category);

    /**
     * 更新栏目
     *
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 删除栏目
     *
     * @param category
     */
    void deleteCategory(Category category);

    /**
     * 恢复栏目
     *
     * @param category
     */
    void recoverCategory(Category category);

    /**
     * 查询所有栏目
     *
     * @return
     */
    List<Category> findAllCategories();

}
