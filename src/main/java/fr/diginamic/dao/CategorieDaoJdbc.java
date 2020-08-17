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

public class CategorieDaoJdbc implements CategorieDao {

	@Override
	public List<Categorie> extraire() {
		Connection connection = null;
		List<Categorie> listeCategorie = new ArrayList<>();

		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM categorie");
			while (result.next()) {
				listeCategorie.add(new Categorie(result.getString("libelle")));
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
		return listeCategorie;
	}

	@Override
	public void insert(Categorie categorie) {
		Connection connection = null;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO categorie (libelle) VALUES ('"
			 + categorie.getLibelle() + "') ;");
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

	@Override
	public int update(String ancienLibelle, String nouveauLibelle) {
		Connection connection = null;
		int result = 0;
		try{
			connection = getConnection();
			Statement statement = connection.createStatement();
			result = statement.executeUpdate("UPDATE categorie SET nom='" + nouveauLibelle
					+ "' WHERE nom='"+ ancienLibelle + "';");
			if (result == 1) {
				System.out.println("Categorie updatée!");
			}
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

	@Override
	public boolean delete(Categorie categorie) {
		Connection connection = null;
		boolean result = false;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			result = statement.execute("DELETE FROM categorie where libelle=" + categorie.getLibelle() + ";");
			if (result == true) {
				System.out.println("La categorie a été supprimée");
			}
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
