package com.example.cookncalc;

import com.example.cookncalc.recipeIngredient.RecipeIngredientDTO;
import com.example.cookncalc.ingredient.Ingredient;
import com.example.cookncalc.ingredient.IngredientDTO;
import com.example.cookncalc.ingredient.IngredientRepository;
import com.example.cookncalc.recipeIngredient.RecipeIngredient;
import com.example.cookncalc.recipeIngredient.RecipeIngredientRepository;
import com.example.cookncalc.recipes.Recipe;
import com.example.cookncalc.recipes.RecipeDTO;
import com.example.cookncalc.recipes.RecipeRepository;
import com.example.cookncalc.recipes.TotalPriceForRecipe;
import com.example.cookncalc.supermarketIngredient.SupermarketIngredient;
import com.example.cookncalc.supermarketIngredient.SupermarketIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class HomeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    private final SupermarketIngredientRepository supermarketIngredientRepository;

    @Autowired
    public HomeService(RecipeRepository recipeRepository,
                       IngredientRepository ingredientRepository,
                       RecipeIngredientRepository recipeIngredientRepository, SupermarketIngredientRepository supermarketIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.supermarketIngredientRepository = supermarketIngredientRepository;
    }

    public List<RecipeDTO> findRecipeByUser(Long id){
        List <RecipeDTO> recipeDTOS = new LinkedList<>();
        List<Recipe> recipes = recipeRepository.findAllByUserId(id);
        for(Recipe recipe: recipes){
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setId(recipe.getId());
            recipeDTO.setTitle(recipe.getTitle());
            recipeDTO.setDescription(recipe.getDescription());
            recipeDTOS.add(recipeDTO);
        }
        return recipeDTOS;
    }


    public List<RecipeDTO> showRecipes(){
        List<RecipeDTO> recipeDTO = new LinkedList<RecipeDTO>();
        List<Recipe> recipes = recipeRepository.findAll();
        for(Recipe recipe : recipes){
            RecipeDTO recipeDTO1 =  new RecipeDTO();
            recipeDTO1.setId(recipe.getId());
            recipeDTO1.setTitle(recipe.getTitle());
            recipeDTO1.setDescription(recipe.getDescription());
            recipeDTO.add(recipeDTO1);
        }
        return recipeDTO;
    }

    public RecipeIngredientDTO showDetailRecipe(Long id) {
        RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        recipeIngredientDTO.setTitle(recipe.getTitle());
        recipeIngredientDTO.setDescription(recipe.getDescription());
        recipeIngredientDTO.setPreparation(recipe.getPreparation());

        List<RecipeIngredient> recipeIngredient = recipeIngredientRepository.findAllByRecipeId(recipe.getId());

        for(RecipeIngredient recipeIngredient1: recipeIngredient){
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setAmount(recipeIngredient1.getAmount());
            ingredientDTO.setName(recipeIngredient1.getIngredient().getName());
            ingredientDTO.setUnit(recipeIngredient1.getIngredient().getUnit());
            recipeIngredientDTO.getIngredients().add(ingredientDTO);
        }
        return recipeIngredientDTO;
    }

    public void addRecipe(RecipeIngredientDTO dto){
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setPreparation(dto.getPreparation());
        recipe.setUser(dto.getUser());
        recipeRepository.save(recipe);

        for (IngredientDTO ingredientDTO : dto.getIngredients()) {
            Double amount = ingredientDTO.getAmount();
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredientRepository.findByName(ingredientDTO.getName()).orElseThrow());
            recipeIngredient.setAmount(amount);
            recipeIngredientRepository.save(recipeIngredient);
        }
    }

    public boolean checkForDuplicateIngredients(RecipeIngredientDTO dto){
        Set<String> ingredientNames = new HashSet<>();
        for(IngredientDTO ingredientDTO : dto.getIngredients()){
            ingredientNames.add(ingredientDTO.getName());
        }
        if (dto.getIngredients().size() == ingredientNames.size()) {
            return true;
        }
        return false;
    }

    public List<IngredientDTO> ingredientsForDropdown() {
        List<IngredientDTO> ingredientDTOList = new LinkedList<>();
        List<Ingredient> ingredients = ingredientRepository.findAll();
        for (Ingredient ingredient : ingredients) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setId(ingredient.getId());
            ingredientDTO.setName(ingredient.getName());
            ingredientDTO.setUnit(ingredient.getUnit());
            ingredientDTOList.add(ingredientDTO);
        }
        return ingredientDTOList;
    }

    public List<RecipeDTO> deleteRecipe(Long id){
        Optional<Recipe> recipeToDeleteOptional = recipeRepository.findById(id);
        if(!recipeToDeleteOptional.isPresent()){
            return null;
        }
        Recipe recipeToDelete = recipeToDeleteOptional.get();
        List<RecipeIngredient> recipeIngredient = recipeIngredientRepository.findByRecipe(recipeToDelete);
        for(RecipeIngredient recipeIngredient1: recipeIngredient){
            recipeIngredientRepository.delete(recipeIngredient1);
        }
        recipeRepository.delete(recipeToDelete);

        return showRecipes();
    }

    public List<TotalPriceForRecipe> priceCalculation(Long id){
        List<TotalPriceForRecipe> calcTable = new ArrayList<>();
        for(Object[] object: this.recipeRepository.getTotalAmountPerMarketForRecipeId(id)){
            calcTable.add(new TotalPriceForRecipe(object));

        }
        return calcTable;
    }

    public List<SupermarketIngredient> getIngredientPrices(Long id) {
        return supermarketIngredientRepository.findSupermarketIngredientByIngredientId(id);
    }
}