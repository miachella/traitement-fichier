package fr.diginamic.entites;

public class Ingredient {
	
	private String libelle;

	public Ingredient(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Ingredient [libelle=" + libelle + "]";
	}
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


}
