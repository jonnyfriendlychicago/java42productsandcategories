package com.jonfriend.java42productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java42productsandcategories.models.CategoryMdl;
import com.jonfriend.java42productsandcategories.models.ProductMdl;

@Repository
public interface CategoryRpo extends CrudRepository<CategoryMdl, Long> {
	List<CategoryMdl> findAll();
	CategoryMdl findByIdIs(Long id);
	List<CategoryMdl> findAllByProducts(ProductMdl productMdl);
	List<CategoryMdl> findByProductsNotContains(ProductMdl productMdl);
}
