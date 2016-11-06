package com.kangyonggan.server.biz.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.server.biz.service.ServerArticleService;
import com.kangyonggan.server.biz.util.FenCi;
import com.kangyonggan.server.mapper.ArticleIndexMapper;
import com.kangyonggan.server.mapper.ArticleMapper;
import com.kangyonggan.server.model.AppConstants;
import com.kangyonggan.server.model.Article;
import com.kangyonggan.server.model.ArticleIndex;
import com.kangyonggan.server.model.Category;
import com.kangyonggan.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/11/6
 */
@Service
@Transactional
public class ServerArticleServiceImpl extends BaseService<Article> implements ServerArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleIndexMapper articleIndexMapper;

    @CacheEvict(cacheNames = "article", allEntries = true)
    @Override
    public void saveArticle(Article article) {
        Category category = categoryService.findCategoryByCode(article.getCategoryCode()).getEntity();
        article.setCategoryName(category.getName());

        super.insertSelective(article);

        saveArticleIndex(article);
    }

    @CacheEvict(cacheNames = "article", allEntries = true)
    @Override
    public void updateArticle(Article article) {
        Category category = categoryService.findCategoryByCode(article.getCategoryCode()).getEntity();

        article.setCategoryName(category.getName());

        super.updateByPrimaryKeySelective(article);

        updateArticleIndex(article);
    }

    @CacheEvict(cacheNames = "article", allEntries = true)
    @Override
    public void deleteArticle(Article article) {
        article.setIsDeleted(AppConstants.IS_DELETED_YES);
        super.updateByPrimaryKeySelective(article);
    }

    @CacheEvict(cacheNames = "article", allEntries = true)
    @Override
    public void recoverArticle(Article article) {
        article.setIsDeleted(AppConstants.IS_DELETED_NO);
        super.updateByPrimaryKeySelective(article);
    }

    @Override
    public List<Article> searchArticles(int pageNum, String code, String title) {
        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        return articleMapper.selectArticlesWithCondition(code, title);
    }

    /**
     * 保存文章索引
     *
     * @param article
     */
    private void saveArticleIndex(Article article) {
        ArticleIndex articleIndex = new ArticleIndex();

        articleIndex.setArticleId(article.getId());
        articleIndex.setBody(FenCi.process(article.getBody()));
        articleIndex.setCategoryName(FenCi.process(article.getCategoryName()));
        articleIndex.setTitle(FenCi.process(article.getTitle()));

        articleIndexMapper.insertSelective(articleIndex);
    }

    /**
     * 更新文章索引
     *
     * @param article
     */
    private void updateArticleIndex(Article article) {
        ArticleIndex articleIndex = new ArticleIndex();
        articleIndex.setArticleId(article.getId());
        articleIndex = articleIndexMapper.selectOne(articleIndex);

        articleIndex.setBody(FenCi.process(article.getBody()));
        articleIndex.setCategoryName(FenCi.process(article.getCategoryName()));
        articleIndex.setTitle(FenCi.process(article.getTitle()));

        articleIndexMapper.updateByPrimaryKeySelective(articleIndex);
    }
}
