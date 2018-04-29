package asBroadcastEncryption.entity;

import it.unisa.dia.gas.jpbc.Element;

public class UserSecretKey {
	
	private Element[] secretKey;
	
	public UserSecretKey(Element[] secretKey){
		this.secretKey = secretKey;
	}
	
	public Element getSecretKeyByIndex(int index){
		return secretKey[index];
	}
}
