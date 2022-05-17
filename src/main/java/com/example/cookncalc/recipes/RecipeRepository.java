package com.example.cookncalc.recipes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findAll();

    @Query(value = "select t.Rezept_ID, t.Rezept_Title, t.Markt, t.Zutat, t.VPE_Need, t.Preis * t.VPE_Need as Ges_Produkt_Preis\n" +
            "from (select r.id                                                                             as Rezept_ID,\n" +
            "             r.title                                                                          as Rezept_Title,\n" +
            "             i.name                                                                           as Zutat,\n" +
            "             si.price                                                                         as Preis,\n" +
            "             s.name                                                                           as Markt,\n" +
            "             i.package_size                                                                   as VPE,\n" +
            "             ri.amount                                                                        as Rezept_Amount,\n" +
            "             floor(ri.amount / i.package_size) + if(mod(ri.amount, i.package_size) > 0, 1, 0) as VPE_Need\n" +
            "      from ingredient i,\n" +
            "           recipe r,\n" +
            "           recipe_ingredient ri,\n" +
            "           supermarket_ingredient si,\n" +
            "           supermarket s\n" +
            "      where ri.ingredient_id = i.id\n" +
            "        and ri.recipe_id = r.id\n" +
            "        and si.ingredient_id = i.id\n" +
            "        and si.supermarket_id = s.id\n" +
            "      order by s.name) t\n" +
            "order by t.Rezept_ID, t.Markt, t.Zutat", nativeQuery = true)
    List<Object[]> getAmountPerIngredientPerMarket();

    @Query(value = "select t.Rezept_ID, t.Rezept_Title, t.Markt, ROUND(sum(t.Preis*t.VPE_Need),2) as Gesamt_Preis from (" +
            "               select r.id                                                                             as Rezept_ID,\n" +
            "                      r.title                                                                          as Rezept_Title,\n" +
            "                      i.name                                                                           as Zutat,\n" +
            "                      si.price                                                                         as Preis,\n" +
            "                      s.name                                                                           as Markt,\n" +
            "                      i.package_size                                                                   as VPE,\n" +
            "                      ri.amount                                                                        as Rezept_Amount,\n" +
            "                      floor(ri.amount / i.package_size) + if(mod(ri.amount, i.package_size) > 0, 1, 0) as VPE_Need\n" +
            "               from ingredient i,\n" +
            "                    recipe r,\n" +
            "                    recipe_ingredient ri,\n" +
            "                    supermarket_ingredient si,\n" +
            "                    supermarket s\n" +
            "               where ri.ingredient_id = i.id\n" +
            "                 and ri.recipe_id = r.id\n" +
            "                 and si.ingredient_id = i.id\n" +
            "                 and si.supermarket_id = s.id\n" +
            "               order by s.name) t where t.Rezept_ID = ?1 GROUP BY t.Rezept_ID, t.Rezept_Title, t.Markt order by t.Rezept_ID, Gesamt_Preis", nativeQuery = true)
    List<Object[]> getTotalAmountPerMarketForRecipeId(Long id);


}
