import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "../../../Recipe";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-recipe-change',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit {

  constructor(private route: ActivatedRoute, private httpClient: HttpClient, private router: Router) { }

  @Input()
  recipe?: Recipe;

  recipes?:Recipe[];

  id?:number;

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    this.httpClient.get<Recipe>(`/api/recipe/${this.id}`).subscribe(result => this.recipe = result);
  }

  changeRecipe(){
    this.httpClient.post<Recipe>(`/api/recipe/${this.id}/edit`, this.recipe).subscribe();

    this.httpClient.get<Recipe[]>("/api").subscribe(result => this.recipes=result);
    this.router.navigate(['/recipes']);

  }



}
