package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public interface ProduitDao {
	List<Produit> extraire();
    void insert(Produit produit);
    int updateCategorie(Categorie ancienneCategorie, Categorie nouvelleCategorie);
    int updateMarque(Marque ancienneMarque, Marque nouvelleMarque);
    int updateNom(String ancienNom, String nouveauNom);
    int updateScoreNutritionnel(String ancienScoreNutritionnel, String nouveauScoreNutritionnel);
    boolean delete(Produit produit);
}
