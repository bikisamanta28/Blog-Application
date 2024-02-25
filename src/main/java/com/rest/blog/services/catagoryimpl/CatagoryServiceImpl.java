package com.rest.blog.services.catagoryimpl;

import com.rest.blog.entities.Catagory;
import com.rest.blog.exceptions.ResourceNotFoundException;
import com.rest.blog.payloads.CatagoryDto;
import com.rest.blog.repositories.CatagoryRepo;
import com.rest.blog.services.CatagoryService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatagoryServiceImpl implements CatagoryService {

	@Autowired
	private CatagoryRepo catagoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CatagoryDto postCatagory(CatagoryDto catagoryDto) {

//		Catagory catagory = this.dtoTocatagory(catagoryDto);
//
//		Catagory catagories = this.catagoryRepo.save(catagory);
//		System.out.println("tis is catagories "+catagories.getCatagoryTitle());
		// return this.catagoryToDto(catagories);

		Catagory catagory = this.modelMapper.map(catagoryDto, Catagory.class);

		Catagory catagories = this.catagoryRepo.save(catagory);

		return this.catagoryToDto(catagories);
	}



@Override
	public CatagoryDto UpdateCatagoryById(CatagoryDto catagoryDto, Integer catId) {

		Catagory catagory = this.catagoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", "Catagory Id", catId));

		catagory.setCatagoryTitle(catagoryDto.getCatagoryTitle());
		catagory.setCatagoryDescription(catagoryDto.getCatagoryDescription());
		Catagory updaCatagory = catagoryRepo.save(catagory);

		return this.modelMapper.map(updaCatagory, CatagoryDto.class);
	}

	@Override
	public CatagoryDto getCatagoryById(Integer catId) {

		Catagory catagory = this.catagoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", "Catagory Id", catId));

		return this.catagoryToDto(catagory);
	}

	@Override
	public List<CatagoryDto> getAllCatagory() {

		List<Catagory> catagories = this.catagoryRepo.findAll();

		List<CatagoryDto> updateCatagory = catagories.stream().map(cat -> this.catagoryToDto(cat))
				.collect(Collectors.toList());
		return updateCatagory;
	}

	@Override
	public void deleteCatagory(Integer catId) {

	}

	private Catagory dtoTocatagory(CatagoryDto catagoryDto) {

		Catagory catagory=this.modelMapper.map(catagoryDto, Catagory.class);
		//Catagory catagory = new Catagory();

//		catagory.setCatagoryTitle(catagoryDto.getCategoryTitle());
//		catagory.setCategoryDescription(catagoryDto.getCatagoryDescription());

		return catagory;
	}

	private CatagoryDto catagoryToDto(Catagory catagory) {

	 CatagoryDto catagorydto=this.modelMapper.map(catagory, CatagoryDto.class);
//		CatagoryDto catagorydto = new CatagoryDto();
//		catagorydto.setCatagoryId(catagory.getCatagoryId());
//		catagorydto.setCategoryTitle(catagory.getCatagoryTitle());
//		catagorydto.setCatagoryDescription(catagory.getCategoryDescription());

		return catagorydto;
	}

}
