package asBroadcastEncryption.entity;

import it.unisa.dia.gas.jpbc.Element;
/*
 * broadcast encryption header
 */
public class Hdr {
	
	private Element c1;
	private Element c2;
	
	public Hdr(Element c1,Element c2){
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public Element getC1(){
		return c1;
	}
	
	public Element getC2(){
		return c2;
	}
}
