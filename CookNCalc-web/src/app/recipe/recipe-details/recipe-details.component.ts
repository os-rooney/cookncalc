import {Component, OnInit} from '@angular/core';
import {Recipe} from "../../model/recipe";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {

  recipe?: Recipe;

  id?: number;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private router: Router,
              public authService: AuthService) { }

  ngOnInit(): void {
   this.id = Number(this.route.snapshot.paramMap.get("id"));
   this.httpClient.get<Recipe>(`/api/recipes/${this.id}`).subscribe(result => this.recipe = result);
  }
}
