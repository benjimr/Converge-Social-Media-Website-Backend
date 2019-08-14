package com.converge.commandHandling;

import java.util.List;
import java.util.Map;

import com.converge.dataStorage.*;
import com.converge.enums.DataType;

public interface Command 
{
	Map<DataType, List<BaseStorage>> execute(Map<DataType, List<BaseStorage>> data);
}
