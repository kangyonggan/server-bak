package com.kangyonggan.server.biz.impl;

import com.kangyonggan.server.model.AppConstants;
import com.kangyonggan.server.model.Category;
import com.kangyonggan.server.model.dto.ResponseDto;
import com.kangyonggan.server.service.CategoryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author kangyonggan
 * @since 16/10/11
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Cacheable(cacheNames = "category", key = "'category_code_' + #code")
    @Override
    public ResponseDto<Category> findCategoryByCode(String code) {
        Category category = new Category();
        category.setCode(code);

        category = super.selectOne(category);

        return new ResponseDto(category);
    }

    @Cacheable(value = "category", key = "'not_deleted'")
    @Override
    public ResponseDto<Category> findAllCategories() {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
        example.setOrderByClause("sort ASC");

        return new ResponseDto(super.selectByExample(example));
    }
}
