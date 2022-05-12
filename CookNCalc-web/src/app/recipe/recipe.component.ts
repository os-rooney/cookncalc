import { Component, OnInit } from '@angular/core';
import {Recipe} from "../Recipe";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  recipes?: Recipe[];

  //ToDo : Service einbinden!!!
  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.httpClient.get<Recipe[]>("/api").subscribe(result => this.recipes=result);
  }
}