package asBroadcastEncryption.entity;

import java.util.concurrent.ConcurrentHashMap;

public class ASBEParametersHolder {
	
	private static ConcurrentHashMap<String,ASBEParameters> map = new ConcurrentHashMap<>();
	
	public static void set(String key,ASBEParameters parameters){
		map.put(key,parameters);
	}
	
	public static ASBEParameters getParameters(String key){
		return map.get(key);
	}
}
