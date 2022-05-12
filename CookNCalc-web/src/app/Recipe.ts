import {RecipeIngredient} from "./Ingredient";

export interface Recipe{
  id:number;
  title: string;
  recipeIngredient: RecipeIngredient[];
  description: string;
  preparation: string;
}
