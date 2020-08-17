package fr.diginamic.dao;

import java.util.List;

import fr.diginamic.entites.Ingredient;

public interface IngredientDao {
	List<Ingredient> extraire();
    void insert(Ingredient ingredient);
    int update(String ancienLibelle, String nouveauLibelle);
    boolean delete(Ingredient ingredient);
}
