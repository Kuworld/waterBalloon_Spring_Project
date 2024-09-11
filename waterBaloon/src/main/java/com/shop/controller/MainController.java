package com.shop.controller;

import com.shop.item.entity.Category;
import com.shop.item.entity.Item;
import com.shop.item.entity.SubCategory;
import com.shop.item.service.ItemService;
import com.shop.question.entity.Question;
import com.shop.question.service.QuestionService;
import com.shop.item.repository.CategoryRepository;
import com.shop.item.repository.SubCategoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String home(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        logger.info("Session ID: " + (session != null ? session.getId() : "No Session"));
        List<Item> items = itemService.getItemsWithHighStock();
        model.addAttribute("items", items);
        List<Item> items2 = itemService.getItemsWithHighStock2();
        model.addAttribute("items2", items2);
        List<Item> items3 = itemService.getItemsWithHighStock3();
        model.addAttribute("items3", items3);
        return "index";
    }

    @GetMapping("/new_product")
    public String newProduct(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        logger.info("Session ID: " + (session != null ? session.getId() : "No Session"));
        return "new_product";
    }

    @GetMapping("/popular_product")
    public String popularProduct(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        logger.info("Session ID: " + (session != null ? session.getId() : "No Session"));
        return "popular_product";
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        logger.info("Logout successful, session should be invalidated.");
        return "logout-success";
    }

    @GetMapping("/{categoryName}_all")
    public String getCategoryItems(@PathVariable("categoryName") String categoryName, Model model) {
        logger.info("Category name: " + categoryName);
        Category category = categoryRepository.findByC1Name(categoryName);
        if (category == null) {
            logger.error("Category not found: " + categoryName);
            return "error"; // 카테고리를 찾지 못한 경우 오류 페이지로 리다이렉트
        }
        Integer c1Cat = category.getC1Cat();
        List<Item> items = itemService.getItemsByCategoryAndSubCategory(c1Cat, null);
        model.addAttribute("items", items);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("subCategoryName", null);
        return "category_items";
    }

    @GetMapping("/{categoryName}_{subCategoryName}")
    public String getSubCategoryItems(@PathVariable("categoryName") String categoryName,
                                      @PathVariable("subCategoryName") String subCategoryName, Model model) {
        logger.info("Category name: " + categoryName + ", Subcategory name: " + subCategoryName);
        Category category = categoryRepository.findByC1Name(categoryName);
        if (category == null) {
            logger.error("Category not found: " + categoryName);
            return "error"; // 카테고리를 찾지 못한 경우 오류 페이지로 리다이렉트
        }
        SubCategory subCategory = subCategoryRepository.findByC2Name(subCategoryName);
        if (subCategory == null) {
            logger.error("Subcategory not found: " + subCategoryName);
            return "error"; // 서브카테고리를 찾지 못한 경우 오류 페이지로 리다이렉트
        }
        Integer c1Cat = category.getC1Cat();
        Integer c2Cat = subCategory.getC2Cat();
        List<Item> items = itemService.getItemsByCategoryAndSubCategory(c1Cat, c2Cat);
        model.addAttribute("items", items);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("subCategoryName", subCategoryName);
        return "category_items";
    }

    @GetMapping("/item_details/{itemId}")
    public String getItemDetails(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.getItemById(itemId);
        if (item == null) {
            return "error";
        }
        List<Question> questions = questionService.getQuestionsByItemId(itemId);
        model.addAttribute("item", item);
        model.addAttribute("questions", questions);
        return "item_details";
    }
}
