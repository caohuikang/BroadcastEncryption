package asBroadcastEncryption.entity;

public class ASBEKeyPair {
	private ASBEPublicKey publicKey;
	private ASBEPrivateKey privateKey;
	
	public ASBEKeyPair(ASBEPublicKey publicKey,ASBEPrivateKey privateKey){
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	public ASBEPublicKey getPublicKey(){
		return publicKey;
	}
	
	public ASBEPrivateKey getPrivateKey(){
		return privateKey;
	}
}
