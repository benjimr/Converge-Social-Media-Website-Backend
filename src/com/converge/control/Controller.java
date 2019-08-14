package com.converge.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.converge.commandHandling.*;
import com.converge.dataStorage.*;
import com.converge.enums.Action;
import com.converge.enums.DataType;

public class Controller 
{
	public Map<DataType,List<BaseStorage>> begin(Map<DataType,List<BaseStorage>> data)
	{
		Map<DataType,List<BaseStorage>> responseData = new HashMap<DataType,List<BaseStorage>>();
		
		//get command factory
		CommandFactory cf = CommandFactory.getInstance();
		
		//get action to be carried out
		Action action = ((Instruction)data.get(DataType.INSTRUCTIONS).get(0)).getAction();
		Command command;
		
		//by default error message is unknown, if an error occurs without a message being set this will be the message
		String errorMsg = "Unknown Error";
		
		//get and execute the command relevant to the current action
		command = cf.getCommand(action);
		responseData = command.execute(data);
		
		//depending on the action different thing need to happen after the responseData has been returned 
		//from the execution
		switch(action)
		{
			case LOGIN:
			{	
				//if null then there has been an error
				if(responseData == null)
				{
					errorMsg = "Login Error: Username or password do not match our records";
				}

				break;
			}
			
			case REGISTER:
			{			
				//if registering was successful, log the user in
				if(responseData  != null)
				{
					command = cf.getCommand(Action.LOGIN);
					((Instruction)responseData.get(DataType.INSTRUCTIONS).get(0)).setAction(Action.LOGIN2);
					responseData = command.execute(responseData);
					
				}
				else
				{
					errorMsg = "Register Error: Username taken";
				}
				
				break;
			}
			
			case VIEWCATEGORIES:
			{
				//if null there has been an error
				if(responseData == null)
				{
					errorMsg = "Retrieval Error: Categories not retrieved";
				}
				break;
			}
			
			case VIEWCATEGORY:
			{
				//if null there has been an error
				if(responseData == null)
				{
					errorMsg = "Retrieval Error: Posts not retrieved";
				}
				break;
			}
			
			case CREATEPOST:
			{
				//if null there has been an error
				if(responseData == null)
				{
					errorMsg = "Creation Error: Post not created";
				}

				break;
			}
			
			case CREATECOMMENT:
			{
				//if null there has been an error
				if(responseData == null)
				{
					errorMsg = "Creation Error: Post not created";
				}
				else//otherwise view the post that the comment is on
				{
					((Instruction)responseData.get(DataType.INSTRUCTIONS).get(0)).setAction(Action.VIEWPOST);
					command = cf.getCommand(Action.VIEWPOST);
					responseData = command.execute(responseData);
				}
			}
			
			case UPDATECOMMENT:
			{		
				if(responseData == null)
				{
					errorMsg = "Update Error: Comment not updated";
				}
			}
			
			default:
			{
				errorMsg = "Invalid Action";
			}
		}
		
		//if the response data is null at this point an error has occurred at some point
		//set the appropriate action/message to be sent back up
		if(responseData == null)
		{
			responseData = new HashMap<DataType,List<BaseStorage>>();
			List<BaseStorage> instructions = new ArrayList<BaseStorage>();
			Instruction i = new Instruction(Action.ERROR);
			i.instData.add(errorMsg);
			instructions.add(i);
			
			responseData.put(DataType.INSTRUCTIONS,instructions);
		}
		
		//return the final response data to the servlet to be formatted and sent to client
		return responseData;
	}
}
