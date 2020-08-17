package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entites.Allergene;

public interface AllergeneDao {
	List<Allergene> extraire();
    void insert(Allergene allergene);
    int update(String ancienLibelle, String nouveauLibelle);
    boolean delete(Allergene allergene);
}
