package com.shop.user.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.shop.item.dto.SubCategoryDTO;
import com.shop.item.entity.Category;
import com.shop.item.entity.Item;
import com.shop.item.service.CategoryService;
import com.shop.item.service.ItemService;
import com.shop.item.service.SubCategoryService;
import com.shop.user.entity.Users;
import com.shop.user.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private ItemService itemService;

	@Value("${upload.path}")
	private String uploadPath;

	@GetMapping("/dashboard")
	public String dashboard() {
		return "admin/dashboard"; // Ensure this corresponds to your Thymeleaf template
	}

	@GetMapping("/add_item")
	public String showAddItemForm(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("item", new Item());
		return "admin/add_item";
	}

	@PostMapping("/add_item")
	public String addItem(@RequestParam("category") Integer categoryId,
			@RequestParam("subCategory") Integer subCategoryId, @RequestParam("iName") String iName,
			@RequestParam("iPrice") Integer iPrice, @RequestParam("iMaker") String iMaker,
			@RequestParam("iImg1") MultipartFile iImg1, @RequestParam("iImg2") MultipartFile iImg2,
			@RequestParam("iImg3") MultipartFile iImg3, @RequestParam("iContent") String iContent,
			@RequestParam("iContentDet") String iContentDet, @RequestParam("iStock") Integer iStock,
			@RequestParam("iRegstat") Integer iRegstat) {

		Item item = new Item();
		item.setCategory(categoryService.getCategoryById(categoryId));
		item.setSubCategory(subCategoryService.getSubCategoryById(subCategoryId));
		item.setIName(iName);
		item.setIPrice(iPrice);
		item.setIMaker(iMaker);
		item.setIContent(iContent);
		item.setIContentDet(iContentDet);
		item.setIStock(iStock);
		item.setIRegstat(iRegstat);

		try {
			if (!iImg1.isEmpty()) {
				String img1Filename = saveImage(iImg1);
				item.setIImg1(img1Filename);
			}
			if (!iImg2.isEmpty()) {
				String img2Filename = saveImage(iImg2);
				item.setIImg2(img2Filename);
			}
			if (!iImg3.isEmpty()) {
				String img3Filename = saveImage(iImg3);
				item.setIImg3(img3Filename);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// Handle error
		}

		itemService.saveItem(item);
		return "redirect:/admin/dashboard";
	}

	@GetMapping("/subcategories")
	@ResponseBody
	public ResponseEntity<List<SubCategoryDTO>> getSubCategories(@RequestParam("categoryId") Integer categoryId) {
		List<SubCategoryDTO> subCategories = subCategoryService.getSubCategoriesByCategoryId(categoryId);
		return ResponseEntity.ok(subCategories);
	}

	private String saveImage(MultipartFile file) throws IOException {
		String filename = file.getOriginalFilename();
		Path uploadDir = Paths.get(uploadPath);
		if (!Files.exists(uploadDir)) {
			Files.createDirectories(uploadDir);
		}
		Path filePath = uploadDir.resolve(filename);
		Files.copy(file.getInputStream(), filePath);
		return filename;
	}

	@GetMapping("/items")
	public String showItems(@RequestParam(value = "searchTerm", required = false) String searchTerm,
			@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
		List<Category> categories = categoryService.getAllCategories();
		List<Item> items;

		if (searchTerm != null && !searchTerm.isEmpty()) {
			items = itemService.searchItemsByName(searchTerm);
		} else if (categoryId != null) {
			items = itemService.getItemsByCategoryAndSubCategory(categoryId, null);
		} else {
			items = itemService.getAllItems();
		}

		model.addAttribute("categories", categories);
		model.addAttribute("items", items);
		return "admin/items";
	}

	@GetMapping("/edit_item/{id}")
	public String showEditItemForm(@PathVariable("id") Long id, Model model) {
		Item item = itemService.getItemById(id);
		List<Category> categories = categoryService.getAllCategories();
		List<SubCategoryDTO> subCategories = subCategoryService
				.getSubCategoriesByCategoryId(item.getCategory().getC1Cat());
		model.addAttribute("item", item);
		model.addAttribute("categories", categories);
		model.addAttribute("subCategories", subCategories);
		return "admin/edit_item";
	}

	@PostMapping("/edit_item")
	public String editItem(@RequestParam("id") Long id,
			@RequestParam("category") Integer categoryId,
			@RequestParam("subCategory") Integer subCategoryId, 
			@RequestParam("iName") String iName,
			@RequestParam("iPrice") Integer iPrice,
			@RequestParam("iStock") Integer iStock,
			@RequestParam("iContent") String iContent,
			@RequestParam("iImg1") MultipartFile iImg1,
			@RequestParam("iImg2") MultipartFile iImg2,
			@RequestParam("iImg3") MultipartFile iImg3) {

		Item item = itemService.getItemById(id);
		item.setCategory(categoryService.getCategoryById(categoryId));
		item.setSubCategory(subCategoryService.getSubCategoryById(subCategoryId));
		item.setIName(iName);
		item.setIPrice(iPrice);
		item.setIStock(iStock);
		item.setIContent(iContent);

		try {
			if (!iImg1.isEmpty()) {
				String img1Filename = saveImage(iImg1);
				item.setIImg1(img1Filename);
			}
			if (!iImg2.isEmpty()) {
				String img2Filename = saveImage(iImg2);
				item.setIImg2(img2Filename);
			}
			if (!iImg3.isEmpty()) {
				String img3Filename = saveImage(iImg3);
				item.setIImg3(img3Filename);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// Handle error
		}

		itemService.saveItem(item);
		return "redirect:/admin/items";
	}

	@GetMapping("/delete_item/{id}")
	public String deleteItem(@PathVariable("id") Long id) {
		itemService.deleteItemById(id);
		return "redirect:/admin/items";
	}
	
	 @Autowired
	    private UserService usersService;

	    @GetMapping("/users")
	    public String showUsers(@RequestParam(value = "searchTerm", required = false) String searchTerm, Model model) {
	        List<Users> users;
	        if (searchTerm != null && !searchTerm.isEmpty()) {
	            users = usersService.searchUsersByUsername(searchTerm);
	        } else {
	            users = usersService.getAllUsers();
	        }
	        model.addAttribute("users", users);
	        return "admin/users";
	    }

	    @GetMapping("/edit_user/{id}")
	    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
	        Users user = usersService.getUserById(id);
	        model.addAttribute("user", user);
	        return "admin/edit_user";
	    }

	    @PostMapping("/edit_user")
	    public String editUser(@ModelAttribute Users user) {
	        usersService.saveUser(user);
	        return "redirect:/admin/users";
	    }

	    @GetMapping("/delete_user/{id}")
	    public String deleteUser(@PathVariable("id") Long id) {
	        usersService.deleteUserById(id);
	        return "redirect:/admin/users";
	    }
	
}
