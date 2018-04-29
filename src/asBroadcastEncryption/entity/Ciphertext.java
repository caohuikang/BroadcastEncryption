package asBroadcastEncryption.entity;

import it.unisa.dia.gas.jpbc.Element;
/*
 * broadcast encryption cipher-text
 * composed by header and a random private key
 * header for decrypting K
 * K is the real cipher-text
 * K can be used for encrypting message
 */
public class Ciphertext {
	private Hdr hdr;
	private Element K;
	
	public Ciphertext(Hdr hdr,Element K){
		this.hdr = hdr;
		this.K = K;
	}
	
	public Hdr getHdr(){
		return hdr;
	}
	
	public Element getK(){
		return K;
	}
}
