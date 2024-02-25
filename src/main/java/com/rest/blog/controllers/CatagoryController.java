package com.rest.blog.controllers;

import com.rest.blog.payloads.CatagoryDto;
import com.rest.blog.services.CatagoryService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/catagory")
public class CatagoryController {

	@Autowired
	private CatagoryService catagoryService;
	
	@PostMapping("/")
	public ResponseEntity<CatagoryDto>createCatagory(@Valid @RequestBody CatagoryDto catagoryDto){
		
		//System.out.println("thisssssssssss"+catagoryDto.getCatagoryTitle());
		CatagoryDto catagoryDto2=this.catagoryService.postCatagory(catagoryDto);
		
		//System.out.println("this is catagory control "+catagoryDto2.getCategoryTitle());
		
		return new ResponseEntity<CatagoryDto>(catagoryDto2,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CatagoryDto>updateCatagory(@Valid @RequestBody CatagoryDto catagoryDto,@PathVariable("catId")Integer catId){
		
		//System.out.println(catagoryDto.getCategoryTitle());
		CatagoryDto catagoryDto2=this.catagoryService.UpdateCatagoryById(catagoryDto,catId);
		
		//System.out.println("this is catagory control "+catagoryDto2.getCategoryTitle());
		
		return new ResponseEntity<CatagoryDto>(catagoryDto2,HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<CatagoryDto>> getAllCatagory(){
//		List<CatagoryDto>catagories=this.catagoryService.getAllCatagory();
		
		return ResponseEntity.ok(this.catagoryService.getAllCatagory());
	}
	@GetMapping("/{catId}")
	public ResponseEntity<CatagoryDto>getCatagoryById(@PathVariable("catId")Integer catId){
		
		CatagoryDto catagoryDto=this.catagoryService.getCatagoryById(catId);
		
		return ResponseEntity.ok(catagoryDto);
	}
}
