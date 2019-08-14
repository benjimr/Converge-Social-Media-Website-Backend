package com.converge.dataStorage;

//have all other kinds of data storage classes extend from this allows them
//all to be stored in the same lists, list<BaseStorage>, so i can just cast to
//the relevant type when I need to
abstract public class BaseStorage 
{
	public String UID;
	
	public String getUID()
	{
		return UID;
	}
	
	public void setUID(String UID)
	{
		this.UID = UID;
	}
}
