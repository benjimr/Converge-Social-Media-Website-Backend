package com.converge.dataStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends BaseStorage 
{
	public String fullName;
	public String userName;
	public String passwordHash;
	public Date joined = new Date(Long.MIN_VALUE);
	public List<Post> posts = new ArrayList<Post>();
	public List<Comment> comments = new ArrayList<Comment>();
	public List<Category> categories  = new ArrayList<Category>();
	
	public String getFullName()
	{
		return fullName;
	}
	
	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}
	
	
	public String getUserName() 
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	
	public String getPasswordHash() 
	{
		return passwordHash;
	}
	
	public void setPasswordHash(String passwordHash) 
	{
		this.passwordHash = passwordHash;
	}
	
	public Date getJoined() 
	{
		return joined;
	}
	
	public void setJoined(Date joined)
	{
		this.joined = joined;
	}
	
	
	public List<Post> getPosts() 
	{
		return posts;
	}
	
	public void setPosts(List<Post> posts)
	{
		this.posts = posts;
	}
	
	
	public List<Comment> getComments() 
	{
		return comments;
	}
	
	public void setComments(List<Comment> comments) 
	{
		this.comments = comments;
	}
	
	
	public List<Category> getCategories() 
	{
		return categories;
	}
	
	public void setCategories(List<Category> categories)
	{
		this.categories = categories;
	}
}
