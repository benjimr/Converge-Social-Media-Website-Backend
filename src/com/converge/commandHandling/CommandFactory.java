package com.converge.commandHandling;

import com.converge.enums.Action;

public class CommandFactory 
{
	private static CommandFactory commandFactory = null;
	
	//used to implement as a singleton object
	public static CommandFactory getInstance()
	{
		//only instantiate new command factory if null
		if(commandFactory == null)
		{
			commandFactory = new CommandFactory();
		}
		
		return commandFactory;
	}
	
	public Command getCommand(Action action)
	{
		//make the relevant command for the current action being carried out
		String commandStr = "com.converge.commandHandling." + action.toString() + "Command";
		Command command = null;
		try
		{
			Class<?> c = Class.forName(commandStr);
			Object o = c.newInstance();
			command = (Command)o;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return command;
	}
}
