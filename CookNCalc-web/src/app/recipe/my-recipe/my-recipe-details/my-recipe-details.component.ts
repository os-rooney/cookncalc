import { Component, OnInit } from '@angular/core';
import {Recipe} from "../../../Recipe";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../auth.service";

@Component({
  selector: 'app-my-recipe-details',
  templateUrl: './my-recipe-details.component.html',
  styleUrls: ['./my-recipe-details.component.css']
})
export class MyRecipeDetailsComponent implements OnInit {

  recipe?: Recipe;

  id?: number;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private router: Router,
              public authService: AuthService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    this.httpClient.get<Recipe>(`/api/recipe/${this.id}`).subscribe(result => this.recipe = result);
  }
}
