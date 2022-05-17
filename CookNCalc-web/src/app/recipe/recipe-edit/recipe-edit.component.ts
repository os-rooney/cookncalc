import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "../../model/recipe";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {RecipeIngredient} from "../../model/recipeIngredient";

@Component({
  selector: 'app-recipe-change',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) { }

  @Input()
  recipe?: Recipe;
  ingredients?: RecipeIngredient[];
  recipes?:Recipe[];

  id?:number;

  ngOnInit(): void {
    this.http.get<RecipeIngredient[]>('api/ingredients').subscribe(list => this.ingredients = list);
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    this.http.get<Recipe>(`/api/recipes/${this.id}`).subscribe(result => this.recipe = result);
  }

  changeRecipe(){
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
