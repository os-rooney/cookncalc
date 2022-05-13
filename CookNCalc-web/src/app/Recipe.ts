import {RecipeIngredient} from "./RecipeIngredient";

export interface Recipe{
  id:number;
  title: string;
  ingredients: RecipeIngredient[];
  description: string;
  preparation: string;
}
