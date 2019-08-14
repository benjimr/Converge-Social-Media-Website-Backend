package com.converge.dataStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post extends BaseStorage
{
	public String title;
	public String body;
	public String link;
	
	public int likes;
	public int dislikes;
	public int shares;
	
	public Date postedOn;
	
	public User poster;
	public Category category;
	
	public List<Comment> comments = new ArrayList<Comment>();
	

	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	
	public String getBody()
	{
		return body;
	}
	
	public void setBody(String body)
	{
		this.body = body;
	}
	
	
	public String getLink() 
	{
		return link;
	}

	public void setLink(String link) 
	{
		this.link = link;
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

	
	public int getShares()
	{
		return shares;
	}

	public void setShares(int shares)
	{
		this.shares = shares;
	}

	
	public Date getPostedOn() 
	{
		return postedOn;
	}

	public void setPostedOn(Date postedOn)
	{
		this.postedOn = postedOn;
	}

	
	public User getPoster() 
	{
		return poster;
	}

	public void setPoster(User poster)
	{
		this.poster = poster;
	}
	

	public Category getCategory() 
	{
		return category;
	}

	public void setCategory(Category category) 
	{
		this.category = category;
	}

	
	public List<Comment> getComments() 
	{
		return comments;
	}

	public void setComments(List<Comment> comments) 
	{
		this.comments = comments;
	}
}
