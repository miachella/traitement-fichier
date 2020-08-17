package fr.diginamic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.entites.Marque;

public class MarqueDaoJdbc implements MarqueDao {

	@Override
	public List<Marque> extraire() {
		Connection connection = null;
		List<Marque> listeMarque = new ArrayList<>();

		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM marque");
			while (result.next()) {
				listeMarque.add(new Marque(result.getString("nom")));
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
		return listeMarque;
	}

	@Override
	public void insert(Marque marque) {
		Connection connection = null;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO marque (nom) VALUES ('"
			 + marque.getNom() + "') ;");
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
	public int update(String ancienNom, String nouveauNom) {
		Connection connection = null;
		int result = 0;
		try{
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE marque SET nom='" + nouveauNom
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

	@Override
	public boolean delete(Marque marque) {
		Connection connection = null;
		boolean result = false;
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM marque where libelle=" + marque.getNom() + ";");
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
