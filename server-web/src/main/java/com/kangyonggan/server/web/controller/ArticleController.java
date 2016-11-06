package com.kangyonggan.server.web.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.server.biz.service.ServerArticleService;
import com.kangyonggan.server.model.Article;
import com.kangyonggan.server.model.Category;
import com.kangyonggan.server.model.dto.ResponseDto;
import com.kangyonggan.server.service.ArticleService;
import com.kangyonggan.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/10/13
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    private static final String PATH_ROOT = "article/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_FORM = PATH_ROOT + "form";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ServerArticleService serverArticleService;

    @Autowired
    private ArticleService articleService;

    /**
     * 文章管理
     *
     * @param pageNum
     * @param code
     * @param title
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "code", required = false, defaultValue = "") String code,
                       @RequestParam(value = "title", required = false, defaultValue = "") String title,
                       Model model) {
        List<Article> articles = serverArticleService.searchArticles(pageNum, code, title);
        PageInfo<Article> page = new PageInfo(articles);
        ResponseDto<Category> responseDto = categoryService.findAllCategories();

        model.addAttribute("page", page);
        model.addAttribute("categories", responseDto.getData());
        return PATH_LIST;
    }

    /**
     * 添加文章
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        ResponseDto<Category> responseDto = categoryService.findAllCategories();

        model.addAttribute("article", new Article());
        model.addAttribute("categories", responseDto.getData());
        return PATH_FORM;
    }

    /**
     * 保存文章
     *
     * @param article
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("article") @Valid Article article,
                       BindingResult result) {
        if (!result.hasErrors()) {
            serverArticleService.saveArticle(article);
            return "redirect:/" + PATH_ROOT;
        } else {
            return PATH_FORM;
        }
    }

    /**
     * 修改文章
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        ResponseDto<Article> articleDto = articleService.findArticleById(id);
        ResponseDto<Category> responseDto = categoryService.findAllCategories();

        model.addAttribute("article", articleDto.getEntity());
        model.addAttribute("categories", responseDto.getData());
        return PATH_FORM;
    }

    /**
     * 更新文章
     *
     * @param article
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("article") @Valid Article article,
                         BindingResult result) {
        if (!result.hasErrors()) {
            serverArticleService.updateArticle(article);
            return "redirect:/" + PATH_ROOT;
        } else {
            return PATH_FORM;
        }
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        ResponseDto<Article> articleDto = articleService.findArticleById(id);
        serverArticleService.deleteArticle(articleDto.getEntity());
        return "redirect:/" + PATH_ROOT;
    }

    /**
     * 恢复文章
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/recover", method = RequestMethod.GET)
    public String recover(@PathVariable("id") Long id) {
        ResponseDto<Article> articleDto = articleService.findArticleById(id);
        serverArticleService.recoverArticle(articleDto.getEntity());
        return "redirect:/" + PATH_ROOT;
    }

}
