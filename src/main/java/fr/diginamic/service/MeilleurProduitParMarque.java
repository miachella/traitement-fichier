package fr.diginamic.service;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.diginamic.dao.MarqueDaoJdbc;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import fr.diginamic.exceptions.ExceptionMarque;
import fr.diginamic.utils.Lecteur;
import fr.diginamic.utils.ProduitFactory;

public class MeilleurProduitParMarque extends Service {

	@Override
	public void traiter(Scanner scanner) throws ExceptionMarque {
		String filePath = ClassLoader.getSystemClassLoader().getResource("open-food-facts.csv").getFile();
		List<String> lignes = Lecteur.lire(filePath);
		Set<Marque> setMarque = Lecteur.ajouterMarque(lignes);
		MarqueDaoJdbc omrq = new MarqueDaoJdbc();
		List<Marque> listMrq = omrq.extraire();
		
		System.out.println("Veuillez saisir une marque:");
		String nomMrq = scanner.nextLine();
		
		if (!listMrq.contains(nomMrq)) {
			throw new ExceptionMarque();
		}
		
		List<Produit> produits = null;
		
		for (String ligne : lignes ) {
			produits.add(ProduitFactory.creer(ligne));
		}

		//Récupérer l'ID Mrq
		String idMrq = null;
		for (Marque mrq: listMrq) {
			if (nomMrq.equals(mrq.getNom())) {
				idMrq = mrq.getNom();
			}
		}
		
		for (Produit p: produits) {
			if (p.getMarque().equals(idMrq) && p.getScoreNutritionnel().equals("a")) {
				System.out.println(p);
			}
		}
		
	}

}
