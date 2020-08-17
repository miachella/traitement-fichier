package fr.diginamic.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class Lecteur {
	
	public static List<String> lire(String cheminFichier){
		
		List<String> lignes = null;
		try {
			File file = new File(cheminFichier);
			lignes = FileUtils.readLines(file, "UTF-8");
			
			// On supprime la ligne d'entête avec les noms des colonnes
			lignes.remove(0);
			
			for (String ligne: lignes) {
				ligne = ligne.replaceAll("'", " ");
				ligne = ligne.replace("\\(", " -");
				ligne = ligne.replace("\\)", "- ");
			}
			
			return lignes;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	/**
	 *  Méthode pour créer la liste de catégories 
	 * @param lignes
	 * @return setCategorie
	 */
	public static Set<Categorie> ajouterCategorie (List<String> lignes){
		Set<Categorie> setCategorie = new HashSet<>();

		for (String ligne: lignes){
			String[] morceaux = ligne.split("\\t", -1);
			
			/*
			 * Méthode de création de la liste de catégories 
			 */
			String nomCat = morceaux[0];
			setCategorie.add(new Categorie(nomCat));
		}
		return setCategorie;	
	}
	
	/**
	 * Méthode pour créer la liste de Marques 
	 * @param lignes
	 * @return setMarque
	 */
	public static Set<Marque> ajouterMarque (List<String> lignes){
		Set<Marque> setMarque = new HashSet<>();

		for (String ligne: lignes){
			String[] morceaux = ligne.split("\\t", -1);
			
			/*
			 * Méthode de création de la liste de marques 
			 */
			String nomMarque = morceaux[1];
			setMarque.add(new Marque(nomMarque));
		}
		return setMarque;	
	}
	
	/**
	 * Mathode pour créer la liste d'ingrédients
	 * @param lignes
	 * @return setIngredient
	 */
	public static Set<Ingredient> ajouterIngredient (List<String> lignes){
		Set<Ingredient> setIngredient = new HashSet<>();

		for (String ligne: lignes){
			String[] morceaux = ligne.split("\\t", -1);
			
			/*
			 * Méthode de création de la liste d'ingrédients
			 */
			String[] ingredients = morceaux[4].split(",");
			for (String igr : ingredients) {
				setIngredient.add(new Ingredient(igr));
			}
		}
		return setIngredient;	
	}
	
	/**
	 * Méthode pour créer la liste d'ingrédients d'un produit
	 * @param ingredients
	 * @return listIngredient
	 */
	public static List<Ingredient> ajouterIngredientPrd (String ingredients){
		List<Ingredient> listIngredient = new ArrayList<>();
			
			/*
			 * Méthode de création de la liste d'ingrédients
			 */
			String[] ingredientTable = ingredients.split(",");
			for (String igr : ingredientTable) {
				listIngredient.add(new Ingredient(igr));
			}
	
		return listIngredient;
	}
	
	/**
	 * Mathode pour créer la liste d'allergenes
	 * @param lignes
	 * @return setAllergene
	 */
	public static Set<Allergene> ajouterAllergene (List<String> lignes){
		Set<Allergene> setAllergene = new HashSet<>();

		for (String ligne: lignes){
			String[] morceaux = ligne.split("\\t", -1);
			
			/*
			 * Méthode de création de la liste d'allergenes
			 */
			String[] allergenes = morceaux[28].split(",");
			for (String all : allergenes) {
				setAllergene.add(new Allergene(all));
			}
		}
		return setAllergene;	
	}
	
	/**
	 * Méthode pour créer la liste d'allergènes d'un produit
	 * @param allergenes
	 * @return listAllergene
	 */
	public static List<Allergene> ajouterAllergenePrd (String allergenes){
		List<Allergene> listAllergene = new ArrayList<>();
			
			/*
			 * Méthode de création de la liste d'ingrédients
			 */
			String[] allergeneTable = allergenes.split(",");
			for (String all : allergeneTable) {
				listAllergene.add(new Allergene(all));
			}
	
		return listAllergene;
	}
	
	/**
	 * Mathode pour créer la liste d'additifs
	 * @param lignes
	 * @return setAdditif
	 */
	public static Set<Additif> ajouterAdditif (List<String> lignes){
		Set<Additif> setAdditif = new HashSet<>();

		for (String ligne: lignes){
			String[] morceaux = ligne.split("\\t", -1);
			
			/*
			 * Méthode de création de la liste d'additifs
			 */
			String[] additifs = morceaux[29].split(",");
			for (String add : additifs) {
				setAdditif.add(new Additif(add));
			}
		}
		return setAdditif;	
	}
	
	/**
	 * Méthode pour créer la liste d'additifs d'un produit
	 * @param additifs
	 * @return listAdditif
	 */
	public static List<Additif> ajouterAdditifPrd (String additifs){
		List<Additif> listAdditif = new ArrayList<>();
			
			String[] additifTable = additifs.split(",");
			for (String addt : additifTable) {
				listAdditif.add(new Additif(addt));
			}
	
		return listAdditif;
	}
	
	public static Set<Produit> ajouterProduit (List<String> lignes){
		Set<Produit> setProduit = new HashSet<>();

		for (String ligne: lignes){
			String[] morceaux = ligne.split("\\t", -1);
			
			/*
			 * Méthode de création de la liste de produits
			 */
			String nomCategorie = morceaux[0];
			Categorie categorie = new Categorie(nomCategorie);

			String nomMarque = morceaux[1];
			Marque marque = new Marque(nomMarque);

			String nomProduit = morceaux[2];			
			Produit produit = new Produit(nomProduit);

			String scoreNutritionnel = morceaux[3];
			String ingredientsList = morceaux[4];
			Double energie100g = Double.parseDouble(morceaux[5]);
			Double graisse100g = Double.parseDouble(morceaux[6]);
			Double sucres100g = Double.parseDouble(morceaux[7]);
			Double fibres100g = Double.parseDouble(morceaux[8]);
			Double proteines100g = Double.parseDouble(morceaux[9]);
			Double sel100g = Double.parseDouble(morceaux[10]);
			Double vitA100g = Double.parseDouble(morceaux[11]);
			Double vitD100g = Double.parseDouble(morceaux[12]);
			Double vitE100g = Double.parseDouble(morceaux[13]);
			Double vitK100g = Double.parseDouble(morceaux[14]);
			Double vitC100g = Double.parseDouble(morceaux[15]);
			Double vitB1100g = Double.parseDouble(morceaux[16]);
			Double vitB2100g = Double.parseDouble(morceaux[17]);
			Double vitPP100g = Double.parseDouble(morceaux[18]);
			Double vitB6100g = Double.parseDouble(morceaux[19]);
			Double vitB9100g = Double.parseDouble(morceaux[20]);
			Double vitB12100g = Double.parseDouble(morceaux[21]);
			Double calcium100g = Double.parseDouble(morceaux[22]);
			Double magnesium100g = Double.parseDouble(morceaux[23]);
			Double iron100g = Double.parseDouble(morceaux[24]);
			Double fer100g = Double.parseDouble(morceaux[25]);
			Double betaCarotene100g = Double.parseDouble(morceaux[26]);
			Double presenceHuilePalme = Double.parseDouble(morceaux[27]);
			String allergenesList = morceaux[28];
			String additifsList = morceaux[29];
			

			/*
			 * Setting des attributs du prd 
			 */
			produit.setCategorie(categorie);
			produit.setMarque(marque);
			produit.setScoreNutritionnel(scoreNutritionnel);
			produit.setIngredients(ajouterIngredientPrd(ingredientsList));
			produit.setAdditifs(ajouterAdditifPrd(additifsList));
			produit.setAllergenes(ajouterAllergenePrd(allergenesList));
			produit.setBetaCarotene100g(betaCarotene100g);
			produit.setCalcium100g(calcium100g);
			produit.setEnergie100g(energie100g);
			produit.setFer100g(fer100g);
			produit.setFibres100g(fibres100g);
			produit.setGraisse100g(graisse100g);
			produit.setIron100g(iron100g);
			produit.setMagnesium100g(magnesium100g);
			produit.setPresenceHuilePalme(presenceHuilePalme);
			produit.setProteines100g(proteines100g);
			produit.setSel100g(sel100g);
			produit.setSucres100g(sucres100g);
			produit.setVitA100g(vitA100g);
			produit.setVitB1100g(vitB1100g);
			produit.setVitB12100g(vitB12100g);
			produit.setVitB2100g(vitB2100g);
			produit.setVitB6100g(vitB6100g);
			produit.setVitB9100g(vitB9100g);
			produit.setVitC100g(vitC100g);
			produit.setVitD100g(vitD100g);
			produit.setVitE100g(vitE100g);
			produit.setVitK100g(vitK100g);
			produit.setVitPP100g(vitPP100g);

			setProduit.add(produit);
		}
		return setProduit;	
	}
}
