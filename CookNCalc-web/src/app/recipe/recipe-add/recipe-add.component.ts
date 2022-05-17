import { Component, OnInit } from '@angular/core';
import {Recipe} from "../../model/recipe";
import {HttpClient} from "@angular/common/http";
import {RecipeIngredient} from "../../model/recipeIngredient";
import {Router} from "@angular/router";
import {User} from "../../model/user";

@Component({
  selector: 'app-recipe-add',
  templateUrl: './recipe-add.component.html',
  styleUrls: ['./recipe-add.component.css']
})
export class RecipeAddComponent implements OnInit {

  ingredients?: RecipeIngredient[];
  recipes?: Recipe[];

  recipe: Recipe = {
    id: 0,
    title: "",
    ingredients: [
      {amount: 0, name:'', unit:''},
    ],
    description: "",
    preparation: "",
    user: {id: 0, username:"", admin:true}
  }
  ingredientUnit?: string;


  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
    this.http.get<RecipeIngredient[]>('api/ingredients').subscribe(list => this.ingredients = list);
    this.http.get<User>('/api/users/current').subscribe(user => this.recipe.user = user);
  }

  saveRecipe(){
    this.http.post<Recipe>('/api/recipes/add', this.recipe)
      .subscribe();

    this.http.get<Recipe[]>("/api").subscribe(result => this.recipes=result);
    this.router.navigate(['/myRecipes']);
  }

  findUnit(ingredientName : string){
    if(this.ingredients){
      for(let ingredient of this.ingredients){
        if(ingredient.name === ingredientName){
          return ingredient.unit;
        }
      }
    }
    return "";
  }
}
