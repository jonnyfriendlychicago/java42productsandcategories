package com.jonfriend.java42productsandcategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jonfriend.java42productsandcategories.models.PublicationMdl;

@Repository
public interface PublicationRpo extends CrudRepository<PublicationMdl, Long> {
	List<PublicationMdl> findAll(); 
}
