package asBroadcastEncryption.entity;

import it.unisa.dia.gas.jpbc.Element;

public class ASBEPublicKey {
	private Element g;
	private Element pairingG;
	private Element[] H;
	

	public ASBEPublicKey(Element g,Element pairingG,Element[] H){
		this.g = g;
		this.pairingG = pairingG;
		this.H = H;
	}
	
	public Element getG() {
		return g;
	}

	public Element getPairingG() {
		return pairingG;
	}

	public Element[] getH() {
		return H;
	}	
	
	public Element getHByIndex(int index){
		return H[index];
	}
}
