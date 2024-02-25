package com.rest.blog.repositories;

import com.rest.blog.entities.Catagory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatagoryRepo extends JpaRepository<Catagory, Integer> {

}
