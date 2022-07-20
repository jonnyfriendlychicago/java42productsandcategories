package com.jonfriend.java42productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java42productsandcategories.models.CategoryMdl;
import com.jonfriend.java42productsandcategories.models.ProductMdl;

@Repository
public interface ProductRpo extends CrudRepository<ProductMdl, Long> {
	List<ProductMdl> findAll();
	ProductMdl findByIdIs(Long id);
	List<ProductMdl> findAllByCategories(CategoryMdl categoryMdl);
	List<ProductMdl> findByCategoriesNotContains(CategoryMdl categoryMdl);
}
