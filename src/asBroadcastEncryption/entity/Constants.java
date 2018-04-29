package asBroadcastEncryption.entity;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	//common constant
	public static final String key = "key";
	
	/* set constant
	 * add the valid user id to the set
	 */
	List<Integer> set;
	
	/*public Constants(){
		set.add(1);
		set.add(2);
	}*/

	
	public Constants(int n, int[] validUser){
		set = new ArrayList<Integer>();
		int count = validUser.length;
		if(count>n){
			System.out.println("invalid valid user id array, count bigger than total users!");
		}
		for(int i=0;i<count;i++){
			set.add(validUser[i]);
		}
	}
	public List<Integer> getSet(){
		return set;
	}
	
	public boolean containUser(int userID){
		return set.contains(userID);
	}
}
