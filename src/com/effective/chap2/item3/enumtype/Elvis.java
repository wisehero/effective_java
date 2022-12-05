package com.effective.chap2.item3.enumtype;

public enum Elvis {
	INSTANCE;

	public static void main(String[] args) {
		Elvis elvis = Elvis.INSTANCE;
		elvis.leaveTheBuilding();
	}

	public void leaveTheBuilding() {
		System.out.println("Whoa baby, I'm outta here!");
	}
}
