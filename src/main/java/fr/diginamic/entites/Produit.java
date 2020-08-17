package fr.diginamic.entites;

import java.util.ArrayList;
import java.util.List;

public class Produit {
	
	private Categorie categorie;
	private Marque marque;
	private String nom;
	private String scoreNutritionnel; 
	private List<Ingredient> ingredients = new ArrayList<>();
	private List<Additif> additifs = new ArrayList<>();
	private List<Allergene> allergenes = new ArrayList<>();
	private Double energie100g;
	private Double graisse100g;
	private Double sucres100g;
	private Double fibres100g;
	private Double proteines100g;
	private Double sel100g;
	private Double vitA100g;
	private Double vitD100g;
	private Double vitE100g;
	private Double vitK100g;
	private Double vitC100g;
	private Double vitB1100g;
	private Double vitB2100g;
	private Double vitPP100g;
	private Double vitB6100g;
	private Double vitB9100g;
	private Double vitB12100g;
	private Double calcium100g;
	private Double magnesium100g;
	private Double iron100g;
	private Double fer100g;
	private Double betaCarotene100g;
	private Double presenceHuilePalme;
	
	public Produit(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Produit " + nom;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getScoreNutritionnel() {
		return scoreNutritionnel;
	}

	public void setScoreNutritionnel(String scoreNutritionnel) {
		this.scoreNutritionnel = scoreNutritionnel;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Additif> getAdditifs() {
		return additifs;
	}

	public void setAdditifs(List<Additif> additifs) {
		this.additifs = additifs;
	}

	public List<Allergene> getAllergenes() {
		return allergenes;
	}

	public void setAllergenes(List<Allergene> allergenes) {
		this.allergenes = allergenes;
	}

	public Double getEnergie100g() {
		return energie100g;
	}

	public void setEnergie100g(Double energie100g) {
		this.energie100g = energie100g;
	}

	public Double getGraisse100g() {
		return graisse100g;
	}

	public void setGraisse100g(Double graisse100g) {
		this.graisse100g = graisse100g;
	}

	public Double getSucres100g() {
		return sucres100g;
	}

	public void setSucres100g(Double sucres100g) {
		this.sucres100g = sucres100g;
	}

	public Double getFibres100g() {
		return fibres100g;
	}

	public void setFibres100g(Double fibres100g) {
		this.fibres100g = fibres100g;
	}

	public Double getProteines100g() {
		return proteines100g;
	}

	public void setProteines100g(Double proteines100g) {
		this.proteines100g = proteines100g;
	}

	public Double getSel100g() {
		return sel100g;
	}

	public void setSel100g(Double sel100g) {
		this.sel100g = sel100g;
	}

	public Double getVitA100g() {
		return vitA100g;
	}

	public void setVitA100g(Double vitA100g) {
		this.vitA100g = vitA100g;
	}

	public Double getVitD100g() {
		return vitD100g;
	}

	public void setVitD100g(Double vitD100g) {
		this.vitD100g = vitD100g;
	}

	public Double getVitE100g() {
		return vitE100g;
	}

	public void setVitE100g(Double vitE100g) {
		this.vitE100g = vitE100g;
	}

	public Double getVitK100g() {
		return vitK100g;
	}

	public void setVitK100g(Double vitK100g) {
		this.vitK100g = vitK100g;
	}

	public Double getVitC100g() {
		return vitC100g;
	}

	public void setVitC100g(Double vitC100g) {
		this.vitC100g = vitC100g;
	}

	public Double getVitB1100g() {
		return vitB1100g;
	}

	public void setVitB1100g(Double vitB1100g) {
		this.vitB1100g = vitB1100g;
	}

	public Double getVitB2100g() {
		return vitB2100g;
	}

	public void setVitB2100g(Double vitB2100g) {
		this.vitB2100g = vitB2100g;
	}

	public Double getVitPP100g() {
		return vitPP100g;
	}

	public void setVitPP100g(Double vitPP100g) {
		this.vitPP100g = vitPP100g;
	}

	public Double getVitB6100g() {
		return vitB6100g;
	}

	public void setVitB6100g(Double vitB6100g) {
		this.vitB6100g = vitB6100g;
	}

	public Double getVitB9100g() {
		return vitB9100g;
	}

	public void setVitB9100g(Double vitB9100g) {
		this.vitB9100g = vitB9100g;
	}

	public Double getVitB12100g() {
		return vitB12100g;
	}

	public void setVitB12100g(Double vitB12100g) {
		this.vitB12100g = vitB12100g;
	}

	public Double getCalcium100g() {
		return calcium100g;
	}

	public void setCalcium100g(Double calcium100g) {
		this.calcium100g = calcium100g;
	}

	public Double getMagnesium100g() {
		return magnesium100g;
	}

	public void setMagnesium100g(Double magnesium100g) {
		this.magnesium100g = magnesium100g;
	}

	public Double getIron100g() {
		return iron100g;
	}

	public void setIron100g(Double iron100g) {
		this.iron100g = iron100g;
	}

	public Double getFer100g() {
		return fer100g;
	}

	public void setFer100g(Double fer100g) {
		this.fer100g = fer100g;
	}

	public Double getBetaCarotene100g() {
		return betaCarotene100g;
	}

	public void setBetaCarotene100g(Double betaCarotene100g) {
		this.betaCarotene100g = betaCarotene100g;
	}

	public Double getPresenceHuilePalme() {
		return presenceHuilePalme;
	}

	public void setPresenceHuilePalme(Double presenceHuilePalme) {
		this.presenceHuilePalme = presenceHuilePalme;
	}

}
