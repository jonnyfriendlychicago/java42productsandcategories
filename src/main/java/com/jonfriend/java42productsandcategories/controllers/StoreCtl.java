package com.jonfriend.java42productsandcategories.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jonfriend.java42productsandcategories.models.CategoryMdl;
import com.jonfriend.java42productsandcategories.models.ProductMdl;
import com.jonfriend.java42productsandcategories.services.CategorySrv;
import com.jonfriend.java42productsandcategories.services.ProductSrv;
import com.jonfriend.java42productsandcategories.services.UserSrv;

//public class StoreCtl {
//
//}

@Controller
public class StoreCtl {

	@Autowired
	private ProductSrv productSrv;
	
	@Autowired
	private CategorySrv categorySrv;
	
	@Autowired
	private UserSrv userSrv;

// ***************************************************
// applicable-to-all methods	
// ***************************************************
	
	// display store page with list of product and category
	@GetMapping("/store")
	public String index(
			Model model
			, HttpSession session
			) {
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		List<ProductMdl> intVar1 = productSrv.allProducts();
		model.addAttribute("productList", intVar1);

		List<CategoryMdl> intVar2 = categorySrv.allCategories();
		model.addAttribute("categoryList", intVar2);
		
		return "store/store.jsp";
	}

// ***************************************************
// product methods	
// ***************************************************
	
	// display create-new product page
	@GetMapping("/store/product/new")
	public String newProduct(
			@ModelAttribute("product") ProductMdl productMdl
			) {
		return "store/product/create.jsp";
	}
	
	// process the create-new product 
	@PostMapping("/store/product/new")
	public String addNewProduct(
			@Valid @ModelAttribute("product") ProductMdl productMdl
			, BindingResult result
			, Model model) {
		
		if(result.hasErrors()) {
			return "store/product/create.jsp";
		}else {
			productSrv.addProduct(productMdl);
			return "redirect:/store";
		}
	}
	
	// view/manage one product
	@GetMapping("/store/product/{id}")
	public String showProduct(
			@PathVariable("id") Long id
			, Model model
			) {
		
		ProductMdl intVar = productSrv.findById(id);
		
		model.addAttribute("product", intVar);
		model.addAttribute("assignedCategories", categorySrv.getAssignedProducts(intVar));
		model.addAttribute("unassignedCategories", categorySrv.getUnassignedProducts(intVar));
		
		return "/store/product/record.jsp";
	}
	
	// process edits to that one product
	@PostMapping("/store/product/{id}")
	public String editProduct(
			@PathVariable("id") Long id
			, @RequestParam(value="categoryId") Long catId
			,  Model model
			) {
		
		ProductMdl product = productSrv.findById(id);
		CategoryMdl category = categorySrv.findById(catId);
		
		product.getCategoryMdl().add(category);
		
		productSrv.updateProduct(product);
		
		model.addAttribute("assignedCategories", categorySrv.getAssignedProducts(product));
		model.addAttribute("unassignedCategories", categorySrv.getUnassignedProducts(product));
		return "redirect:/store/product/" + id;
	}
	
// ***************************************************
// category methods	
// ***************************************************
	
	// display create-new category page
	@GetMapping("/store/category/new")
	public String newCategory(
			@ModelAttribute("category") CategoryMdl categoryMdl
			) {
		return "store/category/create.jsp";}
	
	// process the create-new category
	@PostMapping("/store/category/new")
	public String addNewCategory(
			@Valid @ModelAttribute("category") CategoryMdl categoryMdl
			, BindingResult result
			, Model model) {
		
		if(result.hasErrors()) {
			return "store/category/create.jsp";
		} else {
			categorySrv.addCategory(categoryMdl);
			return "redirect:/store";
		}	
	}
	
	// view/manage one category
	@GetMapping("/store/category/{id}")
	public String showCategory(
			@PathVariable("id") Long id
			, Model model
			) {
		CategoryMdl category = categorySrv.findById(id);
		
		model.addAttribute("assignedProducts", productSrv.getAssignedCategories(category));
		model.addAttribute("unassignedProducts", productSrv.getUnassignedCategories(category));
		model.addAttribute("category", categorySrv.findById(id));
		
		return "/store/category/record.jsp";
	}
	
	// process edits to that one category
	@PostMapping("/store/category/{id}")
	public String editCategory(
			@PathVariable("id") Long id
			, @RequestParam(value="productId") Long productId
			, Model model
			) {
		
		CategoryMdl category = categorySrv.findById(id);
		ProductMdl product = productSrv.findById(productId);
		
		category.getProductMdl().add(product);
		categorySrv.updateCategory(category);
		model.addAttribute("assignedProducts", productSrv.getAssignedCategories(category));
		model.addAttribute("unassignedProducts", productSrv.getUnassignedCategories(category));
		return "redirect:/store/category/" + id;
	}

// end ctl
}