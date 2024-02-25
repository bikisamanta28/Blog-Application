package com.rest.blog.services.postimpl;

import com.rest.blog.entities.Catagory;
import com.rest.blog.entities.Comment;
import com.rest.blog.entities.Post;
import com.rest.blog.entities.User;
import com.rest.blog.exceptions.ResourceNotFoundException;
import com.rest.blog.payloads.CommentDto;
import com.rest.blog.payloads.PostDto;
import com.rest.blog.payloads.PostResponse;
import com.rest.blog.repositories.CatagoryRepo;
import com.rest.blog.repositories.CommentRepo;
import com.rest.blog.repositories.PostRepo;
import com.rest.blog.repositories.UserRepo;
import com.rest.blog.services.PostService;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CatagoryRepo catagoryrepo;
	
	@Autowired
	CommentRepo commentRepo;

	@Autowired
	ModelMapper modelmapper;

	@Override
	public PostDto savePost(PostDto postDto, Integer userId, Integer catId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		Catagory catagory = this.catagoryrepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", "catagory id", catId));

		Post post = this.modelmapper.map(postDto, Post.class);

		post.setPostImage("default.jpg");
		post.setUser(user);
		post.setCatagory(catagory);
		post.setDate(new Date());
		Post posts = this.postRepo.save(post);

		return this.modelmapper.map(posts, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String order) {

		Sort sort = (order.equalsIgnoreCase("ascending")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = this.postRepo.findAll(p);

		List<Post> allposts = pagepost.getContent();

		// List<Post>posts=this.postRepo.findAll();

		List<PostDto> postDtos = allposts.stream().map(post -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setTotalElement(pagepost.getNumberOfElements());
		postResponse.setLastpage(pagepost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPostByCatagory(Integer catagoryId) {

		Catagory cat = this.catagoryrepo.findById(catagoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Catagory", " catagory Id", catagoryId));

		List<PostDto> postsDto = this.postRepo.findByCatagory(cat).stream()
				.map(postRepo -> this.modelmapper.map(postRepo, PostDto.class)).collect(Collectors.toList());

		return postsDto;
	}

	@Override
	public List<PostDto> getAllPostByUserID(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		List<PostDto> postsDto = this.postRepo.findByUser(user).stream()
				.map(postRepo -> this.modelmapper.map(postRepo, PostDto.class)).collect(Collectors.toList());

		return postsDto;
	}

	@Override
	public List<PostDto> getPostBySearch(String keyword) {

		List<Post> list = this.postRepo.findAll();
		List<PostDto> listD = list.stream().filter(post -> post.getPostTitle().contains(keyword))
				.map(object -> this.modelmapper.map(object, PostDto.class)).collect(Collectors.toList());

		// .map(post -> modelmapper.map(post,
		// PostDTO.class)).collect(Collectors.toList());
		if (listD.isEmpty()) {
			throw new ResourceNotFoundException("user havent posted anything", " following keywords",
					Long.parseLong(keyword));
		}

		return listD;

	}

	@Override
	public PostDto getPostByPostId(Integer postId) {

		Post post = this.postRepo.getById(postId);
		Set<Comment>comments=this.commentRepo.findByPost(post);
		 
		 Set<CommentDto> commentDtos=comments.stream().map((comment)->this.modelmapper.map(comment, CommentDto.class)).collect(Collectors.toSet());
        PostDto postDto=this.modelmapper.map(post, PostDto.class);
       
        postDto.setComments(commentDtos);
        return postDto;
	}

	@Override
	public PostDto updatePostByPostId(PostDto postdto, Integer postId) {

		Post posts = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post not found", postId));

		// Post post=this.modelmapper.map(postdto, Post.class);
		posts.setPostContent(postdto.getPostContent());

		posts.setPostImage(postdto.getPostImage() != null ? postdto.getPostImage() : "default.jpg");

		posts.setPostTitle(postdto.getPostTitle());

		return this.modelmapper.map(this.postRepo.save(posts), PostDto.class);
	}

//	public PostDto postToDto(Post post) {
//		CatagoryDto catagoryDto = new CatagoryDto();
//		catagoryDto.setCatagoryDescription(post.getCatagory().getCategoryDescription());
//		catagoryDto.setCategoryTitle(post.getCatagory().getCatagoryTitle());
//		catagoryDto.setCatagoryId(post.getPostId());
//
//		PostDto postDto = new PostDto();
//		postDto.setPostTitle(post.getPostTitle());
//		postDto.setCatagory(catagoryDto);
//		postDto.setUser(this.modelmapper.map(post.getUser(), UserDto.class));
//		postDto.setPostImage(post.getPostImage());
//		postDto.setPostContent(post.getPostContent());
//		postDto.setDate(post.getDate());
//		return postDto;
//	}

}
