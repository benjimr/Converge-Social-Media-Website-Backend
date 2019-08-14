package com.converge.commandHandling;

import java.util.List;
import java.util.Map;

import com.converge.dataAccessHandling.DAOHandler;
import com.converge.dataStorage.BaseStorage;
import com.converge.enums.DataType;

public class SpecializedCommand implements Command
{

	@Override
	public Map<DataType, List<BaseStorage>> execute(Map<DataType, List<BaseStorage>> data) 
	{
		DAOHandler dao = new DAOHandler();
		
		return dao.prepare(data);
	}
	
}
