package com.converge.reqRespHandling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.converge.dataStorage.*;
import com.converge.enums.Action;
import com.converge.enums.DataType;

import org.mindrot.jbcrypt.BCrypt;


public class HTTPHandler 
{
	HttpServletRequest req;
	
	//this is used when a request comes from the client
	public Map<DataType, List<BaseStorage>> formatForMap(HttpServletRequest req, HttpServletResponse resp)
	{
		//storing the request for sending data to page later
		this.req = req;
		
		Map<DataType,List<BaseStorage>> data = new HashMap<DataType,List<BaseStorage>>();
		List<BaseStorage> instructions = new ArrayList<BaseStorage>();
		
		//action by default is an error
		Action action = Action.ERROR;
		Instruction i = new Instruction(action);
		i.instData.add("Request Error: Invalid Action");
		
		//if a valid action is passed in override the default with it
		if(req.getParameter("action") != null)
		{
			action = Action.valueOf(req.getParameter("action"));
			i = new Instruction(action);
		}
				
		//if not a login request, check session, as user should not be able to make any other request
		//without logging in or registering
		if(action != Action.LOGIN && action != Action.REGISTER)
		{
			HttpSession session = req.getSession();
			
			if(session.getId() !=  session.getAttribute("loggedSessionID"))
			{
				//if session error, set message accordingly
				i.setAction(Action.ERROR);
				i.instData.add("Session Error: Please login or register");
			}
		}
		
		//after basic checks have been done extract the required data based on the action
		switch(action)
		{	
			case LOGIN:
			{
				//get user name and password
				String userName = req.getParameter("username");
				String passwordPlain = req.getParameter("password");
				
				i.instData.add(userName);
				i.instData.add(passwordPlain);
				
				break;
			}
			
			case LOGOUT:
			{
				//invalidate the session
				HttpSession session = req.getSession();
				session.invalidate();
				break;
			}
			
			case REGISTER:
			{
				//get full name, user name and hashed password
				String fullName = req.getParameter("fullname");
				String userName = req.getParameter("username");
				String passwordPlain = req.getParameter("password");
				String passwordHashed = BCrypt.hashpw(passwordPlain, BCrypt.gensalt());
				passwordPlain = null;
				
				i.instData.add(userName);
				i.instData.add(passwordHashed);
				i.instData.add(fullName);
				
				break;
			}
			
			case VIEWCATEGORY:
			{
				//get category id
				String catID = req.getParameter("category");
				
				i.instData.add(catID);
				break;
			}

			case VIEWPOST:
			{
				//get post id
				String postID = req.getParameter("post");
				
				i.instData.add(postID);
				break;
			}
			
			case CREATEPOST:
			{
				//get post title, body and link, and user id
				List<BaseStorage> posts = new ArrayList<BaseStorage>();
				String catID = req.getParameter("category");
				
				HttpSession session = req.getSession();
				
				String userID = (String) session.getAttribute("userID");

				Post p = new Post();
				p.title = req.getParameter("title");
				
				if(req.getParameter("link") != null)
				{
					p.link = req.getParameter("link");
				}
				
				if(req.getParameter("body") != null)
				{
					p.body = req.getParameter("body");
				}
				
				posts.add(p);
				data.put(DataType.POSTS,posts);
				
				i.instData.add(catID);
				i.instData.add(userID);
				break;
			}
			
			case CREATECOMMENT:
			{
				//get post id, user id and comment body
				String postID = req.getParameter("post");
				HttpSession session = req.getSession();
				String userID = (String)session.getAttribute("userID");
				
				i.instData.add(postID);
				i.instData.add(userID);				
				i.instData.add(req.getParameter("body"));
				
				break;
			}
			
			case UPDATECOMMENT:
			{
				//get comment id and body of new comment
				i.instData.add(req.getParameter("body"));
				i.instData.add(req.getParameter("comment"));
				break;
			}
			
			default:{}//if not one of the action accounted for above, take no data from the request
		}
		
		//add the final instructions to the data and return it
		instructions.add(i);
		data.put(DataType.INSTRUCTIONS,instructions);
		
		return data;
	}
	
	//this is used before a response is sent to the client
	public HttpServletRequest formatForHttp(Map<DataType, List<BaseStorage>> data)
	{
		//get the action
		Instruction i = ((Instruction)data.get(DataType.INSTRUCTIONS).get(0));
		Action action = i.getAction();
		
		switch(action)
		{
			case ERROR:
			{
				//index size-1 will contain the final String added to the data, which will be the error
				int size = i.instData.size();
				req.setAttribute("ERROR", i.instData.get(size-1));
				
				//which will be displayed on the error page
				req.setAttribute("page", "/error.jsp");
				break;
			}
			
			case LOGIN:
			{
				//get user data
				User u = ((User)data.get(DataType.USERS).get(0));
				req.setAttribute("username", u.userName);
				req.setAttribute("page", "/home.jsp");
				
				//set session variables for display, and session checks later on
				HttpSession session = req.getSession();
				String clientSessionID = session.getId();
				session.setAttribute("loggedSessionID", clientSessionID);
				session.setAttribute("username", u.userName);
				session.setAttribute("userID", u.UID);
				break;
			}
			
			case LOGOUT:
			{
				//no data required other than what page should be loaded
				req.setAttribute("page","/loggedOut.html");
				break;
			}
			
			case VIEWCATEGORIES:
			{
				//send the list of categories
				req.setAttribute("page", "/categories.jsp");
				req.setAttribute("categories", data.get(DataType.CATEGORIES));
				break;
			}
			
			case VIEWCATEGORY:
			{
				//send the list of posts
				req.setAttribute("page", "/category.jsp");
				req.setAttribute("posts", data.get(DataType.POSTS));
				break;
			}
			
			case VIEWPOST:
			{
				//send the list of comments
				req.setAttribute("page", "/post.jsp");
				req.setAttribute("comments", data.get(DataType.COMMENTS));
				break;
			}
			
			case CREATEPOST:
			{
				//send just newly made post
				req.setAttribute("page", "/category.jsp");
				req.setAttribute("posts", data.get(DataType.POSTS));
				break;
			}
			
			case UPDATECOMMENT:
			{
				//return home
				req.setAttribute("page", "/home.jsp");
			}
			
			default:{}
		}
		return req;
	}
}
