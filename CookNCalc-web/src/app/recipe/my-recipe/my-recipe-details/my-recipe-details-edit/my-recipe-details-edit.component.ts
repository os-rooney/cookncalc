import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "../../../../model/recipe";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {RecipeIngredient} from "../../../../model/recipeIngredient";
import {User} from "../../../../model/user";

@Component({
  selector: 'app-my-recipe-details-edit',
  templateUrl: './my-recipe-details-edit.component.html',
  styleUrls: ['./my-recipe-details-edit.component.css']
})
export class MyRecipeDetailsEditComponent implements OnInit {

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

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

  user?: User;

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

}
