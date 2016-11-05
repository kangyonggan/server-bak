package com.kangyonggan.server.mapper;

import com.kangyonggan.server.model.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends MyMapper<Article> {

    /**
     * 全文检索文章
     *
     * @param key
     * @return
     */
    List<Article> selectArticles(@Param("key") String key);

    /**
     * 分页查询文章
     *
     * @param categoryCode
     * @return
     */
    List<Article> findArticlesByPage(@Param("categoryCode") String categoryCode);
}