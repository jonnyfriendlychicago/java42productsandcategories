package com.jonfriend.java42productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java42productsandcategories.models.PersonMdl;

@Repository
public interface PersonRpo extends CrudRepository<PersonMdl, Long>{
	List<PersonMdl> findAll(); 

	// end repo
}
