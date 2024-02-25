package com.rest.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import antlr.debug.NewLineListener;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import javassist.expr.NewArray;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
			throws ServletException, IOException ,NullPointerException{
		// TODO Auto-generated method stub
		
		
	//	1. get token 
		System.out.println(request.getHeaderNames());
		String requestToken=request.getHeader("Authorization");
		
		System.out.println(requestToken);
		
		String username=null;
		String token=null;
		//&& requestToken.startsWith("Bearer")
		if(requestToken!=null && requestToken.startsWith("Bearer") ) {
			
			token=requestToken.substring(7);
			
			System.out.println("request token inside if block "+token);
			try {
				username=this.jwtTokenHelper.getUsernameFromToken(token);
				
			} catch (IllegalArgumentException e) {
				System.out.println("unable to get jwt token");
			}catch (ExpiredJwtException e) {
				System.out.println("unable to get jwt token");
			}catch (MalformedJwtException e) {
				System.out.println("unable to get jwt token");
			}
			catch (NullPointerException e) {
				throw new NullPointerException("null pointer exception oooooo");
			}
			
		}else {
			System.out.println("jwt token does not begin Bearer");
		}
		
		//validation
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userdetails=this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(token, userdetails)) {
				//going good
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				System.out.println("invalid jwt token");
			}
		}else {
			System.out.println("userName is null or context is not null");
		}
			
			filterchain.doFilter(request, response);
	}

}
