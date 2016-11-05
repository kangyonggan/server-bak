package com.kangyonggan.server.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kangyonggan.server.mapper.ArticleMapper;
import com.kangyonggan.server.model.AppConstants;
import com.kangyonggan.server.model.Article;
import com.kangyonggan.server.model.dto.ResponseDto;
import com.kangyonggan.server.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/11
 */
@Log4j2
@Service("articleService")
@Transactional
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Cacheable(value = "article", key = "'article_id_' + #id")
    @Override
    public ResponseDto<Article> findArticleById(Long id) {
        /**
         * 入参日志
         */
        log.info("根据id查询文章入参:id={}", id);

        /**
         * 准备响应数据
         */
        ResponseDto<Article> responseDto = new ResponseDto();
        String respCode = AppConstants.RESP_SUCCESS;
        String respMsg = "响应成功";
        Article article = null;

        /**
         * 参数校验
         */
        if (id == null || id == 0) {
            /**
             * 回写数据
             */
            responseDto.setRespCode(AppConstants.RESP_FAILURE);
            responseDto.setRespMsg("请求参数不合法");
            return responseDto;
        }

        try {
            article = new Article();
            article.setIsDeleted(AppConstants.IS_DELETED_NO);
            article.setId(id);

            article = super.selectOne(article);
        } catch (Exception e) {
            respCode = AppConstants.RESP_EXCEPTION;
            respMsg = "根据id查询文章异常";
            log.error(respMsg, e);

        }

        /**
         * 回写数据
         */
        responseDto.setRespCode(respCode);
        responseDto.setRespMsg(respMsg);
        responseDto.setEntity(article);

        /**
         * 出参日志
         */
        log.info("根据id查询文章出参:{}", responseDto);

        return responseDto;
    }

    @Override
    public ResponseDto<Article> findArticlesByPage(int pageNum, String categoryCode) {
        /**
         * 入参日志
         */
        log.info("分页查询文章入参:pageNum={}, categoryCode", pageNum, categoryCode);

        /**
         * 准备响应数据
         */
        ResponseDto<Article> responseDto = new ResponseDto();
        String respCode = AppConstants.RESP_SUCCESS;
        String respMsg = "响应成功";
        PageInfo<Article> page = new PageInfo();

        /**
         * 参数校验
         */
        if (pageNum == 0) {
            /**
             * 回写数据
             */
            responseDto.setRespCode(AppConstants.RESP_FAILURE);
            responseDto.setRespMsg("请求参数不合法");
            return responseDto;
        }

        try {
            PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
            List<Article> articles = articleMapper.findArticlesByPage(categoryCode);
            page.setList(articles);
        } catch (Exception e) {
            respCode = AppConstants.RESP_EXCEPTION;
            respMsg = "分页查询文章异常";
            log.error(respMsg, e);

        }

        /**
         * 回写数据
         */
        responseDto.setRespCode(respCode);
        responseDto.setRespMsg(respMsg);
        responseDto.setPage(page);

        /**
         * 出参日志
         */
        log.info("分页查询文章出参:{}", responseDto);

        return responseDto;
    }

    @Override
    public ResponseDto<Article> searchArticles(int pageNum, String key) {
        /**
         * 入参日志
         */
        log.info("全文检索文章入参:pageNum={}, key", pageNum, key);

        /**
         * 准备响应数据
         */
        ResponseDto<Article> responseDto = new ResponseDto();
        String respCode = AppConstants.RESP_SUCCESS;
        String respMsg = "响应成功";
        PageInfo<Article> page = new PageInfo();

        /**
         * 参数校验
         */
        if (pageNum == 0 || StringUtils.isEmpty(key)) {
            /**
             * 回写数据
             */
            responseDto.setRespCode(AppConstants.RESP_FAILURE);
            responseDto.setRespMsg("请求参数不合法");
            return responseDto;
        }

        try {
            PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
            List<Article> articles = articleMapper.selectArticles(key);
            page.setList(articles);
        } catch (Exception e) {
            respCode = AppConstants.RESP_EXCEPTION;
            respMsg = "全文检索文章异常";
            log.error(respMsg, e);

        }

        /**
         * 回写数据
         */
        responseDto.setRespCode(respCode);
        responseDto.setRespMsg(respMsg);
        responseDto.setPage(page);

        /**
         * 出参日志
         */
        log.info("全文检索文章出参:{}", responseDto);

        return responseDto;
    }

}
