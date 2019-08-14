package com.converge.reqRespHandling;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.converge.control.Controller;
import com.converge.dataStorage.*;
import com.converge.enums.Action;
import com.converge.enums.DataType;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
  
    public Servlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		control(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		control(req, resp);
	}
	
	protected void control(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HTTPHandler handler = new HTTPHandler();
		
		RequestDispatcher dispatch;
		
		//format the data passed by the request as a map
		Map<DataType,List<BaseStorage>> data = handler.formatForMap(req,resp);
		
		//get the action to make some basic decision making based off it
		Action action = ((Instruction)data.get(DataType.INSTRUCTIONS).get(0)).getAction();
		
		//if no error has occurred when getting the data, or not logging out
		if(action != Action.ERROR && action != Action.LOGOUT)
		{
			//send off to controller to be processed
			Controller c = new Controller();
			Map<DataType,List<BaseStorage>> responseData = c.begin(data);

			//get a request formatted with the response data
			req = handler.formatForHttp(responseData);
		}
		else
		{
			//if the action is error or logout it means no response from the controller is needed
			//so we used the current state of the data to reply, with either successful logout
			//or error page
			req = handler.formatForHttp(data);
		}
	
		//set relevant page and respond
		dispatch = req.getRequestDispatcher(req.getAttribute("page").toString());
		dispatch.forward(req, resp);
	}
}
