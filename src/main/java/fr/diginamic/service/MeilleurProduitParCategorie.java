package fr.diginamic.service;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.diginamic.dao.CategorieDaoJdbc;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.Lecteur;
import fr.diginamic.utils.ProduitFactory;

public class MeilleurProduitParCategorie extends Service {

	@Override
	public void traiter(Scanner scanner) throws Exception {

		String filePath = ClassLoader.getSystemClassLoader().getResource("open-food-facts.csv").getFile();
		List<String> lignes = Lecteur.lire(filePath);
		Set<Categorie> setCategorie = Lecteur.ajouterCategorie(lignes);
		CategorieDaoJdbc ocat = new CategorieDaoJdbc();
		List<Categorie> listCat = ocat.extraire();
		
		System.out.println("Veuillez saisir une catégorie:");
		String nomCat = scanner.nextLine();
		
		if (!setCategorie.contains(nomCat)) {
			throw new Exception();
		}
		
		List<Produit> produits = null;
		
		for (String ligne : lignes ) {
			produits.add(ProduitFactory.creer(ligne));
		}

		//Récupérer l'ID Cat
		String idCat = null;
		for (Categorie cat: listCat) {
			if (nomCat.equals(cat.getLibelle())) {
				idCat = cat.getLibelle();
			}
		}
		
		for (Produit p: produits) {
			if (p.getCategorie().equals(idCat) && p.getScoreNutritionnel().equals("a")) {
				System.out.println(p);
			}
		}
	}

}
