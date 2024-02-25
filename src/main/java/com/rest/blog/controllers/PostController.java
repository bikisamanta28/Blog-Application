package com.rest.blog.controllers;

import com.rest.blog.config.AppConstant;
import com.rest.blog.payloads.PostDto;
import com.rest.blog.payloads.PostResponse;
import com.rest.blog.services.FileService;
import com.rest.blog.services.PostService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

@RestControllerAdvice
@RequestMapping("/api")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	private FileService fileService;
	
	
	@Value("${project.image}")
	private String path;
	
	
	//post blog............
	@PostMapping("/userId/{userId}/catId/{catId}")
	public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer catId){
		
	PostDto postdto=this.postService.savePost(postDto, userId, catId);
	
	//System.out.println(postdto.getUserdto());
		
   		return new ResponseEntity<PostDto>(postdto,HttpStatus.CREATED);
	}
	
	//update post blog
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		
		PostDto postDtos=this.postService.updatePostByPostId(postDto, postId);
		
		
		return new ResponseEntity<PostDto>(postDtos,HttpStatus.OK);
	}
	//get post By Post Id
	
	@GetMapping("/blog/{postId}")
	public ResponseEntity<PostDto>getPostByPostId(@PathVariable Integer postId){
		
		PostDto postDto=this.postService.getPostByPostId(postId);
		
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	//get all blog............
	@GetMapping("/blogs")
	public ResponseEntity <PostResponse> getAllBlog(@RequestParam(value = "pageNumber",defaultValue =AppConstant.PAGE_NUMBER,required = false) Integer pageNumber
			,@RequestParam(value = "pageSize",defaultValue =AppConstant.PAGE_SIZE,required = false) Integer pageSize
			,@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy
			,@RequestParam(value = "order",defaultValue = AppConstant.ORDER,required = false)String order) {

		
		System.out.println("this is get post controller");
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,order);

		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	//get all blog by catagory id
	@GetMapping("/catId/{catId}/blogs")
	public ResponseEntity<List<PostDto>>getPostsByCatagoryId(@PathVariable Integer catId){
		
	List<PostDto>postsDtos=this.postService.getAllPostByCatagory(catId);
		
	
	return new ResponseEntity<List<PostDto>>(postsDtos,HttpStatus.OK);
	}
	
	//get all blog by user id
	@GetMapping("/userId/{userId}/blogs")
	public ResponseEntity<List<PostDto>>getPostsByUserId(@PathVariable Integer userId){
		
	List<PostDto>postsDtos=this.postService.getAllPostByUserID(userId);
		
	
	return new ResponseEntity<List<PostDto>>(postsDtos,HttpStatus.OK);
	}
	
	//search by keyword
	@GetMapping("/search/{search}")
	public ResponseEntity<List<PostDto>> getPostByKeyword(@PathVariable("search") String keyword){
		
		List<PostDto>posts=this.postService.getPostBySearch(keyword);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//image file upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto>uploadPostImage(@RequestParam("image")MultipartFile image,@PathVariable Integer postId) throws IOException{
		
		PostDto postdto=this.postService.getPostByPostId(postId);
		String fileName=this.fileService.uploadImage(path, image);
		
		postdto.setPostImage(fileName);
	PostDto updatePost=this.postService.updatePostByPostId(postdto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping(value="post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")String imageName,HttpServletResponse response)throws IOException {
		
		InputStream resource=this.fileService.getResourceInputStream(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
