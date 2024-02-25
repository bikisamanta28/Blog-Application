package com.rest.blog.services;

import com.rest.blog.payloads.CatagoryDto;

import java.util.List;



public interface CatagoryService {

	
	CatagoryDto postCatagory(CatagoryDto catagoryDto);
	CatagoryDto UpdateCatagoryById(CatagoryDto catagoryDto,Integer catId);
	CatagoryDto getCatagoryById(Integer catId);
    List<CatagoryDto>getAllCatagory();
    void deleteCatagory(Integer catId);
}
