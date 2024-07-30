package model;

public enum CategoriaEnum {
	SUV(0),
	HACTCHBACK(1),
	SEDAN(2);
	
	private final int id;
	
	private CategoriaEnum(int id) {
		this.id = id;
	}
}
