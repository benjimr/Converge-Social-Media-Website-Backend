package com.converge.dataAccessHandling;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.converge.dataStorage.*;
import com.converge.enums.Action;
import com.converge.enums.DataType;

public class SQLHandler
{
	public PreparedStatement getSQL(Action action, Connection conn)
	{
		PreparedStatement stmt = null;
		String query;
		
		//set the query relevant to the current action
		switch(action)
		{	
			case LOGIN:
			{
				query = "select * from Users where userName = ?";
				break;
			}
			
			case STAGE1REG:
			{
				query = "select UserID from Users where userName = ? ";
				break;
			}
			
			case STAGE2REG:
			{
				query = "insert into Users(userName, password, fullName, joined)"
						+ "values(?,?,?,?)";
				break;
			}
			
			case VIEWCATEGORIES:
			{
				query = "select * from Categories";
				break;
			}
			
			case VIEWCATEGORY:
			{
				query = "select * from Posts where catID = ?";
				break;
			}
			
			case VIEWPOST:
			{
				query = "select * from Comments where postID = ?";
				break;
			}
			
			case CREATEPOST:
			{
				query = "insert into Posts(title, body, link, postedOn, catID, userID)"
						+ "values(?, ?, ?, CURDATE(), ?, ?)";
				break;
			}
			
			case CREATECOMMENT:
			{
				query = "insert into Comments(body, creationDate, postID, userID)"
						+ "values(?, CURDATE(), ?, ?)";
				break;
			}
			
			case UPDATECOMMENT:
			{
				query = "update Comments "
						+ "set body = ? "
						+ "where comID = ?";
				break;
			}
			
			default:
			{
				query = null;
			}
		}
		
		//if set
		if(query != null)
		{
			//prepare the statement using the query
			try
			{
				stmt = conn.prepareStatement(query);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return stmt;
	}
	
	public Map<DataType,List<BaseStorage>> getData(Action action,ResultSet res)
	{
		Map<DataType,List<BaseStorage>> responseData = new HashMap<DataType,List<BaseStorage>>();
		
		//transfer action from incoming data into the response data for later processing
		List<BaseStorage> instructions = new ArrayList<BaseStorage>();
		Instruction i = new Instruction(action);
		instructions.add(i);
		responseData.put(DataType.INSTRUCTIONS, instructions);
		
		try
		{
			//extract the data from the result set based on the current action
			switch(action)
			{
				case LOGIN:
				{
					List<BaseStorage> users = new ArrayList<BaseStorage>();
					
					while(res.next())
					{
						User u = new User();
						u.UID = res.getString("userID");
						u.fullName = res.getString("fullName");
						u.userName = res.getString("userName");
						u.passwordHash = res.getString("password");
						u.joined = res.getDate("joined");
						
						users.add(u);
					}
					
					//if user not found, return null indicating log in was not successful
					if(users.isEmpty())
					{
						responseData = null;
					}
					else
					{
						//otherwise return the logged in user data
						responseData.put(DataType.USERS, users);
					}
					break;
				}
				
				case STAGE1REG:
				{
					List<BaseStorage> users = new ArrayList<BaseStorage>();
					
					while(res.next()) 
					{
						User u = new User();
						u.UID = res.getString("userID");
						
						System.out.println("USER: " + u.toString());

						
						users.add(u);
					}                                                                           
					                                                                         
					if(users.isEmpty())                                                      
					{                                                                        
						responseData = null;                                                 
					}                                                                        
					else                                                                     
					{                                                                        
						responseData.put(DataType.USERS, users);                             
					}                                                                        
					break;                                                                   
				}                                                                            
				                                                                             
				case VIEWCATEGORIES:                                                         
				{                                                                            
					List<BaseStorage> categories = new ArrayList<BaseStorage>();             
					                                                                         
					while(res.next())                                                        
					{                                                                        
						Category c = new Category();  
						c.UID = res.getString("catID");
						c.title = res.getString("title");
						c.description = res.getString("description");
						
						categories.add(c);
					}                     
					
					if(categories.isEmpty())
					{
						responseData = null;
					}
					else
					{
						responseData.put(DataType.CATEGORIES,categories);
					}
					
					break;
				}
				
				case VIEWCATEGORY:
				{
					List<BaseStorage> posts = new ArrayList<BaseStorage>();
					
					while(res.next())
					{
						Post p = new Post();
						p.UID = res.getString("postID");
						p.title = res.getString("title");
						p.body = res.getString("body");
						p.link = res.getString("link");
						p.postedOn = res.getDate("postedOn");
						
						posts.add(p);
					}
					
					if(posts.isEmpty())
					{
						responseData = null;
					}
					else
					{
						responseData.put(DataType.POSTS, posts);
					}
					break;
				}
				
				case VIEWPOST:
				{
					List<BaseStorage> comments = new ArrayList<BaseStorage>();
					
					while(res.next())
					{
						Comment c = new Comment();
						c.setUID(res.getString("comID"));
						c.setBody(res.getString("body"));
						c.setCommentedOn(res.getString("postID"));
						c.setCreator(res.getString("userID"));
						c.setCreationDate(res.getDate("creationDate"));
						
						comments.add(c);
					}
					
					if(comments.isEmpty())
					{
						responseData = null;
					}
					else
					{
						responseData.put(DataType.COMMENTS, comments);
					}
					break;
				}
				
				default:{}
			}                                                                                
			                                                                                 
			if(res != null)
			{
				res.close();
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return responseData;
	}
}
