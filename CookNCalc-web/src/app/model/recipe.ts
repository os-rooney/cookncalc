import {RecipeIngredient} from "./recipeIngredient";
import {User} from "./user";

export interface Recipe{
  id:number;
  title: string;
  ingredients: RecipeIngredient[];
  description: string;
  preparation: string;
  user: User;
}
