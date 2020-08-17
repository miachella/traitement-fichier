package fr.diginamic.service;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.diginamic.dao.AdditifDaoJdbc;
import fr.diginamic.dao.AllergeneDaoJdbc;
import fr.diginamic.dao.CategorieDaoJdbc;
import fr.diginamic.dao.IngredientDaoJdbc;
import fr.diginamic.dao.MarqueDaoJdbc;
import fr.diginamic.dao.ProduitDaoJdbc;
import fr.diginamic.entites.Additif;
import fr.diginamic.entites.Allergene;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Ingredient;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;
import fr.diginamic.exceptions.ExceptionMarque;
import fr.diginamic.utils.Lecteur;



public class Application {

	public static void main(String[] args) throws Exception, ExceptionMarque {
		Scanner scanner = new Scanner(System.in);

		String filePath = ClassLoader.getSystemClassLoader().getResource("open-food-facts.csv").getFile();
		List<String> lignes = Lecteur.lire(filePath);

		if (lignes == null) {
			System.out.println("L'application doit s'arrêter en raison d'une erreur d'exécution.");
			System.exit(-1);
		} 
		
		Set<Categorie> setCategorie = Lecteur.ajouterCategorie(lignes);
		CategorieDaoJdbc ocat = new CategorieDaoJdbc();
		Iterator<Categorie> iterCat = setCategorie.iterator();
		while (iterCat.hasNext()) {
			Categorie cat = iterCat.next();
			ocat.insert(cat);
		}
		
		Set<Marque> setMarque = Lecteur.ajouterMarque(lignes);
		MarqueDaoJdbc omrq = new MarqueDaoJdbc();
		Iterator<Marque> iterMrq = setMarque.iterator();
		while (iterMrq.hasNext()) {
			Marque mrq = iterMrq.next();
			omrq.insert(mrq);
		}
		
		Set<Ingredient> setIngredient = Lecteur.ajouterIngredient(lignes);
		IngredientDaoJdbc oigr = new IngredientDaoJdbc();
		Iterator<Ingredient> iterIgr = setIngredient.iterator();
		while (iterIgr.hasNext()) {
			Ingredient igr = iterIgr.next();
			oigr.insert(igr);
		}
		
		Set<Allergene> setAllergene = Lecteur.ajouterAllergene(lignes);
		AllergeneDaoJdbc oall = new AllergeneDaoJdbc();
		Iterator<Allergene> iterAll = setAllergene.iterator();
		while (iterAll.hasNext()) {
			Allergene all = iterAll.next();
			oall.insert(all);
		}
		
		Set<Additif> setAdditif = Lecteur.ajouterAdditif(lignes);
		AdditifDaoJdbc oadd = new AdditifDaoJdbc();
		Iterator<Additif> iterAdd = setAdditif.iterator();
		while (iterAdd.hasNext()) {
			Additif add = iterAdd.next();
			oadd.insert(add);
		}
		
		Set<Produit> setProduit = Lecteur.ajouterProduit(lignes);
		ProduitDaoJdbc oprd= new ProduitDaoJdbc();
		Iterator<Produit> iterPrd = setProduit.iterator();
		while (iterPrd.hasNext()) {
			Produit prd = iterPrd.next();
			oprd.insert(prd);
		}

		// Menu
		int choix = 0;
		do {

			// Affichage du menu
			afficherMenu();

			// Poser une question à l'utilisateur
			String choixMenu = scanner.nextLine();

			// Conversion du choix utilisateur en int
			choix = Integer.parseInt(choixMenu);

			// On exécute l'option correspondant au choix de l'utilisateur
			switch (choix) {
			case 1:
				MeilleurProduitParMarque meillPrdMrq = new MeilleurProduitParMarque();
				meillPrdMrq.traiter(scanner);
				break;
			case 2:
				MeilleurProduitParCategorie meillPrdCat = new MeilleurProduitParCategorie();
				meillPrdCat.traiter(scanner);
				break;
			case 3:
				MeilleurProduitParCategorieParMarque meillPrdCatMrq = new MeilleurProduitParCategorieParMarque();
				meillPrdCatMrq.traiter(scanner);
				break;
			case 4:
				AllergeneCourant allCourant = new AllergeneCourant();
				allCourant.traiter(scanner);
				break;
			case 5:
				AdditifCourant addCourant = new AdditifCourant();
				addCourant.traiter(scanner);
				break;
			}

		} while (choix != 99);

		scanner.close();

	}

	/**
	 * Affichage du menu
	 */
	private static void afficherMenu() {
		System.out.println("***** Application Open Food Facts *****");
		System.out.println("1. Rechercher les meilleurs produits pour une marque donnée");
		System.out.println("2. Rechercher les meilleurs produits pour une catégorie donnée");
		System.out.println("3. Rechercher les meilleurs produits par marque et par catégorie");
		System.out.println("4. Rechercher les allergènes les plus courants");
		System.out.println("5. Rechercher les additifs les plus courants");
		System.out.println("99. Sortir");
	}

}
