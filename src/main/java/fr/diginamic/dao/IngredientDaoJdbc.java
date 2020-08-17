package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.entites.Ingredient;

public class IngredientDaoJdbc implements IngredientDao {

	@Override
	public List<Ingredient> extraire() {
		Connection connection = null;
		List<Ingredient> listeIngredient = new ArrayList<>();

		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM ingredient");
			while (result.next()) {
				listeIngredient.add(new Ingredient(result.getString("libelle")));
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
		return listeIngredient;
	}

	@Override
	public void insert(Ingredient ingredient) {
		Connection connection = null;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO ingredient (libelle) VALUES ('"
			 + ingredient.getLibelle() + "') ;");
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
			statement.executeUpdate("UPDATE ingredient SET libelle='" + nouveauLibelle
					+ "' WHERE libelle='"+ ancienLibelle + "';");
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
	public boolean delete(Ingredient ingredient) {
		Connection connection = null;
		boolean result = false;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM ingredient where libelle=" + ingredient.getLibelle() + ";");
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
