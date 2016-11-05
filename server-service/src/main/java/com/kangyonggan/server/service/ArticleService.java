package com.kangyonggan.server.service;

import com.kangyonggan.server.model.Article;
import com.kangyonggan.server.model.dto.ResponseDto;

/**
 * @author kangyonggan
 * @since 16/10/11
 */
public interface ArticleService {

    /**
     * 根据文章id查询
     *
     * @param id
     * @return
     */
    ResponseDto<Article> findArticleById(Long id);

    /**
     * 分页查询
     *
     * @param pageNum
     * @param categoryCode
     * @return
     */
    ResponseDto<Article> findArticlesByPage(int pageNum, String categoryCode);

    /**
     * 全文检索
     *
     * @param pageNum
     * @param key
     * @return
     */
    ResponseDto<Article> searchArticles(int pageNum, String key);


}
