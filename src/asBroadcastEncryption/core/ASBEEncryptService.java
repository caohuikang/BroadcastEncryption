package asBroadcastEncryption.core;

import java.util.List;

import asBroadcastEncryption.entity.*;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class ASBEEncryptService {

	Constants setConstants;//valid user id array
	
	/*
	 * generate public and private key pair
	 * public key for generating user secret key and encrypting message
	 * private key for generating user secret key
	 */
	public ASBEKeyPair setUp(int n,int[] validUser){
		
		setConstants = new Constants(n,validUser);
		
		ASBEParameters parameters = createParameters(n);
		Pairing pairing = parameters.getPairing();
	//	int n = parameters.getN();
		Element g = parameters.getG().getImmutable();
		
		Element alpha = pairing.getZr().newRandomElement().getImmutable();
		Element galpha = g.powZn(alpha);
		
		Element alphaPairing = pairing.pairing(g, g).powZn(alpha);				
		Element[] H = new Element[n];
		for(int i = 0;i < n;i++){
			Element cigma = pairing.getZr().newRandomElement();
			H[i] = g.powZn(cigma);
		}
		
		return new ASBEKeyPair(new ASBEPublicKey(g,alphaPairing,H),new ASBEPrivateKey(galpha));
	}
	
	/*
	 * generate user secret(private) key
	 * @param id: user unique id
	 */
	public UserSecretKey keyGen(ASBEPublicKey publicKey,ASBEPrivateKey privateKey,int id){
		ASBEParameters asbeParameters = ASBEParametersHolder.getParameters(Constants.key);
		Pairing pairing = asbeParameters.getPairing();
		int n = asbeParameters.getN();
		Element g = asbeParameters.getG().getImmutable();
		Element ri = pairing.getZr().newRandomElement().getImmutable();
		Element[] secretKey = new Element[n+1];//di
		
		secretKey[0] = g.powZn(ri.negate());//d(i,0)
		secretKey[id+1] = privateKey.getGalpha().mul(
				publicKey.getHByIndex(id).duplicate().powZn(ri));//d(i,i)=galpha * (H[i] pow ri)
		/*
		 * secretKey index from 1 to n response to userID from 0 to n-1
		 */
		for(int i = 1;i < n+1;i++){
			if((i-1) != id){
				secretKey[i] = publicKey.getHByIndex(i-1).duplicate().powZn(ri);
			}
		}
		
		UserSecretKey usecretKey = new UserSecretKey(secretKey);
		return usecretKey;
	}
	
	/*
	 * broadcast encryption
	 * return cipher text composed by header and a random secret key
	 * not the common sense of cipher-text
	 * but can use the K in cipher-text to encrypt real message 
	 * by using the K as symmetric encryption private key or other encryption algorithm
	 */
	public Ciphertext encrypt(ASBEPublicKey publicKey){
		ASBEParameters asbeParameters = ASBEParametersHolder.getParameters(Constants.key);
		Pairing pairing = asbeParameters.getPairing();
		int n = asbeParameters.getN();
		Element g = asbeParameters.getG().getImmutable();
		
		//Constants setConstants = new Constants();
		List<Integer> set = setConstants.getSet();
		Element t = pairing.getZr().newRandomElement().getImmutable();
		
		Element K = publicKey.getPairingG().powZn(t);
		
		Element c1 = g.powZn(t);
		Element[] H = publicKey.getH();
		Element c2 = null;
		int cursor = 0;
		for(int i = 0;i<n;i++){
			if(set.contains(i)){
				c2 = H[i];
				cursor = i; 
				break;
			}
			if(i == n){
				System.out.println("invalid set:contains no valid user id!");
				return null;
			}
		}
	//	System.out.println("cursor is: "+cursor);
		for(int i = cursor+1;i< n;i++){
			if(set.contains(i)){
				c2 = c2.mul(H[i]);
			}
		}
		
		c2 = c2.powZn(t);
		
		Hdr hdr = new Hdr(c1,c2);
		Ciphertext cipherText = new Ciphertext(hdr,K);
		return cipherText;		
	}
	
	/*
	 * broadcast decryption 
	 * return the random private key K
	 */
	public Element decrypt(Ciphertext ciphertext,UserSecretKey uSecretKey,int userID){
		ASBEParameters asbeParameters = ASBEParametersHolder.getParameters(Constants.key);
		Pairing pairing = asbeParameters.getPairing();
		
		//Constants setConstants = new Constants();
		List<Integer> set = setConstants.getSet();
		
		Element temp = uSecretKey.getSecretKeyByIndex(userID+1);
		for(int i = 0;i<set.size();i++){
			if(!(set.get(i).equals(userID))){
				temp = temp.mul(uSecretKey.getSecretKeyByIndex(set.get(i)+1));
			}
		}
		Element c1 = ciphertext.getHdr().getC1();
		Element c2 = ciphertext.getHdr().getC2();
		Element isK = (pairing.pairing(temp, c1)).mul(//cannot be mulZn!!!
				pairing.pairing(uSecretKey.getSecretKeyByIndex(0), c2));
		return isK;
	}
	
	private ASBEParameters createParameters(int n){
		ASBEParameters parameters = new ASBEParameters(
				PairingFactory.getPairingParameters("./params/curves/a.properties"),n);
		ASBEParametersHolder.set(Constants.key,parameters);
		return parameters;
	}
}
