package com.kangyonggan.server.biz.impl;

import com.kangyonggan.server.model.AppConstants;
import com.kangyonggan.server.model.Category;
import com.kangyonggan.server.model.dto.ResponseDto;
import com.kangyonggan.server.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/11
 */
@Log4j2
@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Cacheable(value = "category", key = "'category_code_' + #code")
    @Override
    public ResponseDto<Category> findCategoryByCode(String code) {
        /**
         * 入参日志
         */
        log.info("根据代码查询栏目入参:code={}", code);

        /**
         * 准备响应数据
         */
        ResponseDto<Category> responseDto = new ResponseDto();
        String respCode = AppConstants.RESP_SUCCESS;
        String respMsg = "响应成功";
        Category category = null;

        /**
         * 参数校验
         */
        if (StringUtils.isEmpty(code)) {
            /**
             * 回写数据
             */
            responseDto.setRespCode(AppConstants.RESP_FAILURE);
            responseDto.setRespMsg("请求参数不合法");
            return responseDto;
        }

        try {
            category = new Category();
            category.setIsDeleted(AppConstants.IS_DELETED_NO);
            category.setCode(code);

            category = super.selectOne(category);
        } catch (Exception e) {
            respCode = AppConstants.RESP_EXCEPTION;
            respMsg = "根据代码查询栏目异常";
            log.error(respMsg, e);

        }

        /**
         * 回写数据
         */
        responseDto.setRespCode(respCode);
        responseDto.setRespMsg(respMsg);
        responseDto.setEntity(category);

        /**
         * 出参日志
         */
        log.info("根据代码查询栏目出参:{}", responseDto);

        return responseDto;
    }

    @Cacheable(value = "category", key = "'category_all'")
    @Override
    public ResponseDto<Category> findAllCategories() {
        /**
         * 入参日志
         */
        log.info("查找所有栏目入参:没有参数");

        /**
         * 准备响应数据
         */
        ResponseDto<Category> responseDto = new ResponseDto();
        String respCode = AppConstants.RESP_SUCCESS;
        String respMsg = "响应成功";
        List<Category> categories = new ArrayList();

        try {
            Example example = new Example(Category.class);
            example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
            example.setOrderByClause("sort ASC");

            super.selectByExample(example);
        } catch (Exception e) {
            respCode = AppConstants.RESP_EXCEPTION;
            respMsg = "查找所有栏目异常";
            log.error(respMsg, e);

        }

        /**
         * 回写数据
         */
        responseDto.setRespCode(respCode);
        responseDto.setRespMsg(respMsg);
        responseDto.setData(categories);

        /**
         * 出参日志
         */
        log.info("查找所有栏目出参:{}", responseDto);

        return responseDto;
    }
}
