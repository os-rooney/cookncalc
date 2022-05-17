import {RecipeIngredient} from "./RecipeIngredient";
import {User} from "./model/user";

export interface Recipe{
  id:number;
  title: string;
  ingredients: RecipeIngredient[];
  description: string;
  preparation: string;
  user: User;
}
