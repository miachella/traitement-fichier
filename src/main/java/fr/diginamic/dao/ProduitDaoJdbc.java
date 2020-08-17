package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.entites.Categorie;
import fr.diginamic.entites.Marque;
import fr.diginamic.entites.Produit;

public class ProduitDaoJdbc implements ProduitDao {

	@Override
	public List<Produit> extraire() {
		Connection connection = null;
		List<Produit> listeProduit = new ArrayList<>();

		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM produit");
			while (result.next()) {
				listeProduit.add(new Produit(result.getString("nom")));
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			System.err.println("Erreur de communication avec la base de données" + e.getMessage());
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("pb de connection close : " + e.getMessage());
			}
		}
		return listeProduit;
	}

	/**
	 * 
	 * Methode d'insert de Produit
	 * 
	 * @param produit
	 * 
	 */
	@Override
	public void insert(Produit produit) {
		Connection connection = null;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO produit (categorie, marque, nom, scoreNutritionnel) VALUES ("
					+ produit.getCategorie() + "," + produit.getMarque() + ", '" + produit.getNom() + "', '" 
					+ produit.getScoreNutritionnel() + "') ;");
			statement.close();
		} catch (Exception e) {
			System.err.println("Erreur insert : " + e.getMessage());
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("pb de connection close : " + e.getMessage());
			}
		}

	}

	/**
	 * 
	 * Methode d'update de Categorie
	 * 
	 * @param ancienneCategorie, nouvelleCategorie
	 * 
	 */
	@Override
	public int updateCategorie(Categorie ancienneCategorie, Categorie nouvelleCategorie) {
		Connection connection = null;
		int result = 0;
		try{
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE produit SET categorie=" + nouvelleCategorie.getLibelle()
					+ " WHERE categorie="+ ancienneCategorie.getLibelle() + ";");
			statement.close();
		} catch (SQLException e) {
			System.err.println("pb de connection close : " + e.getMessage());
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("pb de connection close : " + e.getMessage());
			}
		}
		return result;
	}

	
	/**
	 * 
	 * Methode d'update de Marque
	 * 
	 * @param ancienneMarque, nouvelleMarque
	 * 
	 */
	@Override
	public int updateMarque(Marque ancienneMarque, Marque nouvelleMarque) {
		Connection connection = null;
		int result = 0;
		try{
			connection = getConnection();
			Statement statement = connection.createStatement();
			result = statement.executeUpdate("UPDATE produit SET marque=" + nouvelleMarque.getNom()
					+ " WHERE marque="+ ancienneMarque.getNom() + ";");
			statement.close();
		} catch (SQLException e) {
			System.err.println("pb de connection close : " + e.getMessage());
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("pb de connection close : " + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 
	 * Methode d'update de nom
	 * 
	 * @param ancienNom, nouveauNom
	 * 
	 */
	@Override
	public int updateNom(String ancienNom, String nouveauNom) {
		Connection connection = null;
		int result = 0;
		try{
			connection = getConnection();
			Statement statement = connection.createStatement();
			result = statement.executeUpdate("UPDATE produit SET nom='" + nouveauNom
					+ "' WHERE nom='"+ ancienNom + "';");
			statement.close();
		} catch (SQLException e) {
			System.err.println("pb de connection close : " + e.getMessage());
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("pb de connection close : " + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 
	 * Methode d'update de ScoreNutritionnel
	 * 
	 * @param ancienScoreNutritionnel, nouveauScoreNutritionnel
	 * 
	 */
	@Override
	public int updateScoreNutritionnel(String ancienScoreNutritionnel, String nouveauScoreNutritionnel) {
		Connection connection = null;
		int result = 0;
		try{
			connection = getConnection();
			Statement statement = connection.createStatement();
			result = statement.executeUpdate("UPDATE produit SET scoreNutritionnel='" + nouveauScoreNutritionnel
					+ "' WHERE scoreNutritionnel='"+ ancienScoreNutritionnel + "';");
			statement.close();
		} catch (SQLException e) {
			System.err.println("pb de connection close : " + e.getMessage());
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("pb de connection close : " + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 
	 * Methode de delete de Produit
	 * 
	 * @param produit à delete
	 * 
	 */
	@Override
	public boolean delete(Produit produit) {
		Connection connection = null;
		boolean result = false;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM produit where nom='" + produit.getNom() + "';");
			statement.close();
		} catch (SQLException e) {
			System.err.println("pb de connection close : " + e.getMessage());
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println("pb de connection close : " + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * 
	 * Methode d'ouverture de connexion
	 * 
	 */
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
