package fr.diginamic.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.dao.CategorieDaoJdbc;
import fr.diginamic.dao.MarqueDaoJdbc;
import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class ProduitFactory {
	
	public static Produit creer(String ligne) {
		String[] morceaux = ligne.split("\\t", -1);

		String nom = morceaux[2];
		
		return new Produit(nom);
		
	}
	
	public Connection getConnection() {
		// recupere le fichier properties
		ResourceBundle db = ResourceBundle.getBundle("database");

		try {
			// enregistre le pilote
			Class.forName(db.getString("db.driver"));

			return DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
					db.getString("db.pass"));
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
