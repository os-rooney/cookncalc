import { Component, OnInit } from '@angular/core';
import {Recipe} from "../../Recipe";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-recipe-add',
  templateUrl: './recipe-add.component.html',
  styleUrls: ['./recipe-add.component.css']
})
export class RecipeAddComponent implements OnInit {

  recipes?: Recipe[];


  recipe: Recipe = {
    id: 0,
    title: "",
    ingredients: [
      {amount: 0, name:'', unit:''},
    ],
    description: "",
    preparation: ""
  }

  constructor(private http: HttpClient,private router: Router) { }

  ngOnInit(): void {
  }

  saveRecipe(){
    this.http.post<Recipe>('/api/addRecipe', this.recipe)
      .subscribe();

    this.http.get<Recipe[]>("/api").subscribe(result => this.recipes=result);
    this.router.navigate(['/recipes']);
  }

}
