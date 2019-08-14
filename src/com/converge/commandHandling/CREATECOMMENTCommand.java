package com.converge.commandHandling;

import java.util.List;
import java.util.Map;

import com.converge.dataStorage.BaseStorage;
import com.converge.enums.DataType;

public class CREATECOMMENTCommand extends SpecializedCommand
{
	@Override
	public Map<DataType, List<BaseStorage>> execute(Map<DataType, List<BaseStorage>> data) 
	{
		return super.execute(data);
	}
}
