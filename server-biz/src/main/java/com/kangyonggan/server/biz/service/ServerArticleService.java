package com.kangyonggan.server.biz.service;

import com.kangyonggan.server.model.Article;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/11/6
 */
public interface ServerArticleService {

    /**
     * 保存文章
     *
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 更新文章
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 删除文章
     *
     * @param article
     */
    void deleteArticle(Article article);

    /**
     * 恢复文章
     *
     * @param article
     */
    void recoverArticle(Article article);

    /**
     * 按条件搜索文章
     *
     * @param pageNum
     * @param code
     * @param title
     * @return
     */
    List<Article> searchArticles(int pageNum, String code, String title);
}
