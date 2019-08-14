package com.converge.dataAccessHandling;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.converge.dataStorage.*;
import com.converge.enums.Action;
import com.converge.enums.DataType;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAOHandler extends DAO
{
	public Map<DataType, List<BaseStorage>> prepare(Map<DataType, List<BaseStorage>> data)
	{
		Map<DataType, List<BaseStorage>> responseData = null;
		SQLHandler handler = new SQLHandler();
		DAO dao = DAO.getInstance();
		Connection conn = dao.getConnection();
		Instruction i = ((Instruction)data.get(DataType.INSTRUCTIONS).get(0));
		Action action = i.getAction();

		//ask handler for prepared statement for the current action,
		//also give it the connection so it can prepare the statement
		PreparedStatement stmt = handler.getSQL(action, conn);
		
		//set variables in the prepared statement based on what the current action is
		switch(action)
		{
			case LOGIN:
			{
				//login just finds the user, password is check later
				String userName = i.instData.get(0);
				
				try
				{
					stmt.setString(1, userName);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				responseData = handler.getData(action, dao.getData(stmt));
				break;
			}
			
			case STAGE1REG:
			{
				//stage 1 of registering checks for the username requested
				String userName = i.instData.get(0);
				
				try
				{
					stmt.setString(1, userName);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				responseData = handler.getData(action, dao.getData(stmt));
				break;
			}
			
			case STAGE2REG:
			{
				//stage 2 of registering requires username, password, fullname and current date
				//for inserting in to the database
				String userName = i.instData.get(0);
				String password = i.instData.get(1);
				String fullname = i.instData.get(2);
				
				try
				{
					stmt.setString(1, userName);
					stmt.setString(2, password);
					stmt.setString(3, fullname);
					stmt.setDate(4, Date.valueOf(java.time.LocalDate.now()));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				//send the statement to the DAO to be executed
				int dbResp = dao.updateData(stmt);
				
				//if insert was not successful
				if(dbResp != 1)
				{
					responseData = null;
				}
				else
				{
					//set response data to the data that was originally passed in
					//this is to save us from having to select it from the database
					//again, as we already have all the inserted data stored here
					responseData = data;
				}
				
				break;
			}
			
			case VIEWCATEGORIES:
			{
				//no extra data needed to be added to statement
				//just run it and format the response
				responseData = handler.getData(action, dao.getData(stmt));
				break;
			}
			
			case VIEWCATEGORY:
			{
				//just needs a category id
				String catID = i.instData.get(0);
				
				try
				{
					stmt.setString(1, catID);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				responseData = handler.getData(action, dao.getData(stmt));
				break;
			}
			
			case VIEWPOST:
			{
				//just needs a post id
				String postID = i.instData.get(0);
				
				try
				{
					stmt.setString(1, postID);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				responseData = handler.getData(action, dao.getData(stmt));
				break;
			}
			
			case CREATEPOST:
			{
				//gets all the relevant parts of the post
				Post p = (Post)data.get(DataType.POSTS).get(0);
				
				try
				{
					stmt.setString(1,p.title);
					stmt.setString(2,p.body);
					stmt.setString(3,p.link);
					stmt.setInt(4,Integer.parseInt(i.instData.get(0)));
					stmt.setInt(5,Integer.parseInt(i.instData.get(1)));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				int dbResp = dao.updateData(stmt);
				
				if(dbResp != 1)
				{
					responseData = null;
				}
				else
				{
					responseData = data;
				}
				
				break;
			}
			
			case CREATECOMMENT:
			{
				try
				{
					stmt.setString(1, i.instData.get(2));//body
					stmt.setString(2, i.instData.get(0));//post
					stmt.setString(3, i.instData.get(1));//user
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				int dbResp = dao.updateData(stmt);
				
				if(dbResp != 1)
				{
					responseData = null;
				}
				else
				{
					responseData = data;
				}
				
				break;
			}
			
			case UPDATECOMMENT:
			{
				try
				{
					stmt.setString(1,i.instData.get(0));//body
					stmt.setString(2, i.instData.get(1));//comment id
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				int dbResp = dao.updateData(stmt);
				
				if(dbResp != 1)
				{
					responseData = null;
				}
				else
				{
					responseData = data;
				}
				break;
			}
			
			default:{}
		}
		
		
		//close the statement
		try
		{
			if(stmt != null)
			{
				stmt.close();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//return the data
		return responseData;
	}
}
