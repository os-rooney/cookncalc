import {Component, OnInit} from '@angular/core';
import {Recipe} from "../../model/recipe";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {RecipeIngredient} from "../../model/recipeIngredient";
import {User} from "../../model/user";

@Component({
  selector: 'app-recipe-change',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {

  recipe: Recipe = {
    id: 0,
    title: "",
    ingredients: [
      {amount: 0, name:'', unit:''},
    ],
    description: "",
    preparation: "",
    user: {id: 3, username:"", admin:true}
  }
  ingredients?: RecipeIngredient[];
  recipes?:Recipe[];

  id?:number;

  user?:User;

  checkIngredientMatch: boolean = false;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.http.get<RecipeIngredient[]>('api/ingredients').subscribe(list => this.ingredients = list);
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    this.http.get<Recipe>(`/api/recipes/${this.id}`).subscribe(result => {
      this.recipe = result;
      this.http.get<User>('/api/users/current').subscribe(user => this.user = user);
      },)
  }

  changeRecipe(){
    if (this.user) {
      this.recipe.user = this.user;
    }
    this.http.post<Recipe>(`/api/recipes/${this.id}/edit`, this.recipe).subscribe();
    this.http.get<Recipe[]>("/api").subscribe(result => this.recipes=result);
    this.router.navigate(['/recipes']);
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

  ingredientMatchesDropdown(ingredient: RecipeIngredient): boolean {
    let check : boolean = false;
    this.checkIngredientMatch = false;
    if (this.ingredients) {
      for (let ingr of this.ingredients) {
        if (ingr.name === ingredient.name) {
          check = true;
          this.checkIngredientMatch = true;
        }
      }
    }
    return check;
  }

  noDuplicateIngredients():boolean {
    let ingrList : String[] = [];
    for (let ingr of this.recipe.ingredients) {
      if (!ingrList.includes(ingr.name)) {
        ingrList.push(ingr.name);
      }
    }
    if(ingrList.length === this.recipe.ingredients.length) {
      return true;
    }
    return false;
  }
}
