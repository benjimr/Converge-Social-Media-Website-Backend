package com.converge.dataStorage;

import java.util.Date;

public class Comment extends BaseStorage
{
	String body;
	int likes;
	int dislikes;
	Date creationDate;
	String creator;
	String commentedOn;
	
	public String getBody() 
	{
		return body;
	}
	
	public void setBody(String body) 
	{
		this.body = body;
	}
	
	
	public int getLikes() 
	{
		return likes;
	}
	
	public void setLikes(int likes) 
	{
		this.likes = likes;
	}
	
	
	public int getDislikes() 
	{
		return dislikes;
	}
	
	public void setDislikes(int dislikes)
	{
		this.dislikes = dislikes;
	}
	
	
	public Date getCreationDate() 
	{
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
	
	public String getCreator()
	{
		return creator;
	}
	
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	
	
	public String getCommentedOn() 
	{
		return commentedOn;
	}
	
	public void setCommentedOn(String commentedOn)
	{
		this.commentedOn = commentedOn;
	}
	
}
