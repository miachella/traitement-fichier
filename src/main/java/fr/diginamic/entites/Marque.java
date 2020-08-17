package fr.diginamic.entites;

public class Marque {
	
	private String nom;

	public Marque(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Marque [nom=" + nom + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
