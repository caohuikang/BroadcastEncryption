package asBroadcastEncryption.entity;

import it.unisa.dia.gas.jpbc.Element;

public class ASBEPrivateKey {
private Element galpha;
	
	public ASBEPrivateKey(Element galpha){
		this.galpha = galpha;
	}
	
	public Element getGalpha(){
		return galpha;
	}
}
