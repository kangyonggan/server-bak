package com.kangyonggan.server.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "article_index")
@Data
public class ArticleIndex extends BaseModel {

    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文章ID
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 标题
     */
    private String title;

    /**
     * 栏目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 内容
     */
    private String body;
}