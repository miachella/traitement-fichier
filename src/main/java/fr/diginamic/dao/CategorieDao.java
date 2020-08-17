package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entites.Categorie;

public interface CategorieDao {
	List<Categorie> extraire();
    void insert(Categorie categorie);
    int update(String ancienLibelle, String nouveauLibelle);
    boolean delete(Categorie categorie);
}
