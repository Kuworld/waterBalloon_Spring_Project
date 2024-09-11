/*
 * package com.shop.item.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.shop.item.entity.SubCategory; import
 * com.shop.item.service.SubCategoryService;
 * 
 * @RestController public class CategoryController {
 * 
 * @Autowired private SubCategoryService subCategoryService;
 * 
 * @GetMapping("/subcategories") public List<SubCategory>
 * getSubCategories(@RequestParam("categoryId") Integer categoryId){
 * 
 * return subCategoryService.getSubCategoriesByCategoryId(categoryId);
 * 
 * }
 * 
 * 
 * }
 */