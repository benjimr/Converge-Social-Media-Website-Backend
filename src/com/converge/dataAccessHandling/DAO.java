package com.converge.dataAccessHandling;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class DAO 
{
	private static DAO dao = null;
	private Connection conn = null;
	
	//singleton 
	public static DAO getInstance()
	{
		if(dao == null)
		{
			dao = new DAO();
		}
		
		return dao;
	}
	
	//return the already opened connection
	protected Connection getConnection()
	{
		return conn;
	}
	
	public DAO()
	{
		//when the object is created open the connection
		String url = "jdbc:mysql://localhost:3306/converge";
		String driver ="com.mysql.jdbc.Driver";
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"root","");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//used to execute a statement when data is expected back
	public ResultSet getData(PreparedStatement stmt)
	{
		try
		{
			return stmt.executeQuery();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	//used to execute a statement when no data is expected back
	public int updateData(PreparedStatement stmt)
	{
		try
		{
			return stmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return -1;
	}
}
