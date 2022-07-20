package com.jonfriend.java42productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java42productsandcategories.models.ExpenseMdl;

@Repository
public interface ExpenseRpo extends CrudRepository<ExpenseMdl, Long>{
	
	List<ExpenseMdl> findAll(); 
	


// end repo
}
