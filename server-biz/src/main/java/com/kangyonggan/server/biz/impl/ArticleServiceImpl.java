package com.kangyonggan.server.biz.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kangyonggan.server.mapper.ArticleMapper;
import com.kangyonggan.server.model.AppConstants;
import com.kangyonggan.server.model.Article;
import com.kangyonggan.server.model.dto.ResponseDto;
import com.kangyonggan.server.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/11
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Cacheable(value = "article", key = "'article_id_' + #id")
    @Override
    public ResponseDto<Article> findArticleById(Long id) {
        return new ResponseDto(super.selectByPrimaryKey(id));
    }

    @Cacheable(value = "article", key = "'article_pageNum_' + #pageNum + '_categoryCode' + #categoryCode")
    @Override
    public ResponseDto<Article> findArticlesByPage(int pageNum, String categoryCode) {
        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        List<Article> articles = articleMapper.findArticlesByPage(categoryCode);

        return new ResponseDto(new PageInfo(articles));
    }

    @Cacheable(value = "article", key = "'article_pageNum_' + #pageNum + '_key' + #key")
    @Override
    public ResponseDto<Article> searchArticles(int pageNum, String key) {
        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        List<Article> articles = articleMapper.selectArticles(key);

        return new ResponseDto(new PageInfo(articles));
    }

}
