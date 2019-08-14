package com.converge.commandHandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.converge.dataStorage.BaseStorage;
import com.converge.dataStorage.Instruction;
import com.converge.enums.Action;
import com.converge.enums.DataType;

public class REGISTERCommand extends SpecializedCommand
{
	@Override
	public Map<DataType,List<BaseStorage>> execute(Map<DataType,List<BaseStorage>> data)
	{
		Map<DataType,List<BaseStorage>> responseData = new HashMap<DataType,List<BaseStorage>>();

		//stage 1 checking if the user already exists
		((Instruction)data.get(DataType.INSTRUCTIONS).get(0)).setAction(Action.STAGE1REG);
		responseData = super.execute(data);

		//if null, means no user was found, so we can keep going in the process
		if(responseData == null)
		{
			//move on to stage 2, creating the new user
			((Instruction)data.get(DataType.INSTRUCTIONS).get(0)).setAction(Action.STAGE2REG);
			responseData = super.execute(data);
		}
		else
		{
			//if a user was found at stage 1 we return null, indicating the user name is taken
			responseData = null;
		}

		return responseData;
	}
}
