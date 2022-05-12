import {Component, OnInit} from '@angular/core';
import {Recipe} from "../../Recipe";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {

  recipe?: Recipe;

  id?: number;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
   this.id = Number(this.route.snapshot.paramMap.get("id"));
   this.httpClient.get<Recipe>(`/api/recipe/${this.id}`).subscribe(result => this.recipe = result);
  }

}
