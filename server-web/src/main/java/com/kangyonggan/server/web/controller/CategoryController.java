package com.kangyonggan.server.web.controller;

import com.kangyonggan.server.biz.service.ServerCategoryService;
import com.kangyonggan.server.model.Category;
import com.kangyonggan.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author kangyonggan
 * @since 16/10/13
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    private static final String PATH_ROOT = "category/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_FORM = PATH_ROOT + "form";

    @Autowired
    private ServerCategoryService serverCategoryService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 栏目管理
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("categories", serverCategoryService.findAllCategories());
        return PATH_LIST;
    }

    /**
     * 添加栏目
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return PATH_FORM;
    }

    /**
     * 保存栏目
     *
     * @param category
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("category") @Valid Category category,
                       BindingResult result) {
        if (!result.hasErrors()) {
            serverCategoryService.saveCategory(category);
            return "redirect:/" + PATH_ROOT;
        } else {
            return PATH_FORM;
        }
    }

    /**
     * 修改栏目
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "{code:[\\w]+}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable("code") String code, Model model) {
        Category category = categoryService.findCategoryByCode(code).getEntity();

        model.addAttribute("category", category);
        return PATH_FORM;
    }

    /**
     * 更新栏目
     *
     * @param category
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("category") @Valid Category category,
                       BindingResult result) {
        if (!result.hasErrors()) {
            serverCategoryService.updateCategory(category);
            return "redirect:/" + PATH_ROOT;
        } else {
            return PATH_FORM;
        }
    }

    /**
     * 删除栏目
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "{code:[\\w]+}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("code") String code) {
        Category category = categoryService.findCategoryByCode(code).getEntity();
        serverCategoryService.deleteCategory(category);
        return "redirect:/" + PATH_ROOT;
    }

    /**
     * 恢复栏目
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "{code:[\\w]+}/recover", method = RequestMethod.GET)
    public String recover(@PathVariable("code") String code) {
        Category category = categoryService.findCategoryByCode(code).getEntity();
        serverCategoryService.recoverCategory(category);
        return "redirect:/" + PATH_ROOT;
    }

}
