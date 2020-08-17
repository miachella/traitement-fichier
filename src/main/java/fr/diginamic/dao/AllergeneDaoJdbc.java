package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.entites.Allergene;

public class AllergeneDaoJdbc implements AllergeneDao {

	@Override
	public List<Allergene> extraire() {
		Connection connection = null;
		List<Allergene> listeAllergene = new ArrayList<>();

		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM allergene");
			while (result.next()) {
				listeAllergene.add(new Allergene(result.getString("libelle")));
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
		return listeAllergene;
	}

	@Override
	public void insert(Allergene allergene) {
		Connection connection = null;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO allergene (libelle) VALUES ('"
			 + allergene.getLibelle() + "') ;");
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
			statement.executeUpdate("UPDATE allergene SET libelle='" + nouveauLibelle
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
	public boolean delete(Allergene allergene) {
		Connection connection = null;
		boolean result = false;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM allergene where libelle=" + allergene.getLibelle() + ";");
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
