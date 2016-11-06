package com.kangyonggan.server.biz.impl;

import com.kangyonggan.server.biz.service.ServerCategoryService;
import com.kangyonggan.server.model.AppConstants;
import com.kangyonggan.server.model.Category;
import com.kangyonggan.server.model.dto.ResponseDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/11
 */
@Service
@Transactional
public class ServerCategoryServiceImpl extends BaseService<Category> implements ServerCategoryService {

    @CacheEvict(cacheNames = "category", allEntries = true)
    @Override
    public void saveCategory(Category category) {
        super.insertSelective(category);
    }

    @CacheEvict(cacheNames = "category", allEntries = true)
    @Override
    public void updateCategory(Category category) {
        super.updateByPrimaryKeySelective(category);
    }

    @CacheEvict(cacheNames = "category", allEntries = true)
    @Override
    public void deleteCategory(Category category) {
        category.setIsDeleted(AppConstants.IS_DELETED_YES);
        super.updateByPrimaryKeySelective(category);
    }

    @CacheEvict(cacheNames = "category", allEntries = true)
    @Override
    public void recoverCategory(Category category) {
        category.setIsDeleted(AppConstants.IS_DELETED_NO);
        super.updateByPrimaryKeySelective(category);
    }

    @Cacheable(value = "category", key = "'is_deleted'")
    @Override
    public List<Category> findAllCategories() {
        Example example = new Example(Category.class);
        example.setOrderByClause("sort ASC");

        return super.selectByExample(example);
    }
}
