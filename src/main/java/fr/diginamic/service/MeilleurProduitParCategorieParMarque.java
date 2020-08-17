package fr.diginamic.service;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.diginamic.dao.CategorieDaoJdbc;
import fr.diginamic.dao.MarqueDaoJdbc;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import fr.diginamic.utils.Lecteur;
import fr.diginamic.utils.ProduitFactory;

public class MeilleurProduitParCategorieParMarque extends Service {

	@Override
	public void traiter(Scanner scanner) throws Exception {
		String filePath = ClassLoader.getSystemClassLoader().getResource("open-food-facts.csv").getFile();
		List<String> lignes = Lecteur.lire(filePath);
		Set<Categorie> setCategorie = Lecteur.ajouterCategorie(lignes);
		CategorieDaoJdbc ocat = new CategorieDaoJdbc();
		List<Categorie> listCat = ocat.extraire();
		Set<Marque> setMarque = Lecteur.ajouterMarque(lignes);
		MarqueDaoJdbc omrq = new MarqueDaoJdbc();
		List<Marque> listMrq = omrq.extraire();
		
		System.out.println("Veuillez saisir une catégorie:");
		String nomCat = scanner.nextLine();
		System.out.println("Veuillez saisir une marque:");
		String nomMrq = scanner.nextLine();
		
		if (!setCategorie.contains(nomCat) || !setMarque.contains(nomMrq)) {
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
		
		//Récupérer l'ID Mrq
				String idMrq = null;
				for (Marque mrq: listMrq) {
					if (nomMrq.equals(mrq.getNom())) {
						idMrq = mrq.getNom();
					}
				}
		
		for (Produit p: produits) {
			if (p.getCategorie().equals(idCat) && p.getMarque().equals(idMrq) && p.getScoreNutritionnel().equals("a")) {
				System.out.println(p);
			}
		}
	}

}
