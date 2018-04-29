package asBroadcastEncryption.core;

import asBroadcastEncryption.entity.ASBEKeyPair;
import asBroadcastEncryption.entity.Ciphertext;
import asBroadcastEncryption.entity.UserSecretKey;
import it.unisa.dia.gas.jpbc.Element;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n=5;
		int[] validUser = {1,2};
		int userID = 4;
		
		ASBEEncryptService service = new ASBEEncryptService();
		ASBEKeyPair keyPair = service.setUp(n,validUser);
		UserSecretKey userSecretKey = service.keyGen(
				keyPair.getPublicKey(),keyPair.getPrivateKey(),userID);
		Ciphertext ciphertext = service.encrypt(keyPair.getPublicKey());
		Element kdec = service.decrypt(ciphertext,userSecretKey,userID);
		System.out.println("k in ciphertext is: "+ciphertext.getK().toString());
		System.out.println("k in decryption is: "+kdec.toString());
		if(ciphertext.getK().equals(kdec)){//
			System.out.println("successful!");
		}
		else{
			System.out.println("failed!");
		}
	}

}
