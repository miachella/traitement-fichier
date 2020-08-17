package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entites.Marque;

public interface MarqueDao {
	List<Marque> extraire();
    void insert(Marque marque);
    int update(String ancienNom, String nouveauNom);
    boolean delete(Marque marque);
}
