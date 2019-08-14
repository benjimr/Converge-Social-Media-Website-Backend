package com.converge.commandHandling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import com.converge.dataStorage.BaseStorage;
import com.converge.dataStorage.Instruction;
import com.converge.dataStorage.User;
import com.converge.enums.Action;
import com.converge.enums.DataType;

public class LOGINCommand extends SpecializedCommand
{
	@Override
	public Map<DataType, List<BaseStorage>> execute(Map<DataType, List<BaseStorage>> data)
	{
		Map<DataType, List<BaseStorage>> responseData = new HashMap<DataType, List<BaseStorage>>();
		String passwordPlain = ((Instruction)(data.get(DataType.INSTRUCTIONS).get(0))).instData.get(1);
		Boolean mode2;
		
		//mode2 is used when we are logging in after registering, this is because the data format will be
		//slightly different and we need to know that.
		if(((Instruction)data.get(DataType.INSTRUCTIONS).get(0)).getAction().equals(Action.LOGIN2))
		{
			mode2 = true;
		}
		else
		{
			mode2 = false;
		}
		
		//get the action
		((Instruction)data.get(DataType.INSTRUCTIONS).get(0)).setAction(Action.LOGIN);

		//get data required from database
		responseData = super.execute(data);
		
		//if user requested has been found
		if(responseData != null)
		{
			//get the password hash that was stored in the database
			String storedHash = ((User)(responseData.get(DataType.USERS).get(0))).passwordHash;
						
			//if passwords match or we are in login mode 2, meaning the user has just registered return data, indicating login was successful
			if(BCrypt.checkpw(passwordPlain, storedHash) || mode2 == true )
			{
				return responseData;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}

	}
}
