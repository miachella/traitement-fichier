package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entites.Additif;

public interface AdditifDao {
	List<Additif> extraire();
    void insert(Additif additif);
    int update(String ancienLibelle, String nouveauLibelle);
    boolean delete(Additif additif);
}
