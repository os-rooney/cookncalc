import {Injectable, Input} from "@angular/core";
import {Recipe} from "./Recipe";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RecipeService{


  recipes?: Recipe[];

  constructor(private httpClient: HttpClient) {
    this.httpClient.get<Recipe[]>("/api").subscribe(result => this.recipes=result);
  }

  getRecipes(): Recipe[] | undefined{
    return this.recipes;
  }

  getRecipe(id:number): Recipe{
    return <Recipe>this.recipes?.find(a => a.id === id);
  }
}
