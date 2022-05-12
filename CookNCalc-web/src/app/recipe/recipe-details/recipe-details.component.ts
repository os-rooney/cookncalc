import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "../../Recipe";
import {ActivatedRoute} from "@angular/router";
import {RecipeService} from "../../RecipeService";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {

  recipe?: Recipe={
    "id":0,
    "title":"",
    "description":"",
    "preparation":"",
  }

  constructor(private route: ActivatedRoute, private recipeService: RecipeService) { }

  ngOnInit(): void {
   const id= Number(this.route.snapshot.paramMap.get("id"));
   this.recipe = this.recipeService.getRecipe(id);
  }

}
