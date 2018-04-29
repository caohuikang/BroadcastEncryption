package asBroadcastEncryption.entity;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class ASBEParameters {
	private PairingParameters parameters;
	private int n;
	
	private Pairing pairing;
	private Element g;
	
	public ASBEParameters(PairingParameters parameters,int n){
		this.parameters = parameters;
		this.n = n;
		this.pairing = PairingFactory.getPairing(this.parameters);
		this.g = pairing.getG1().newRandomElement();
	}
	
	public PairingParameters getParameters() {
		return parameters;
	}

	public int getN() {
		return n;
	}
	
	public Element getG(){
		return g;
	}
	
	public Pairing getPairing(){
		return pairing;
	}
}
