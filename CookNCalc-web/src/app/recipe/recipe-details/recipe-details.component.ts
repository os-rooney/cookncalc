import {Component, OnInit} from '@angular/core';
import {Recipe} from "../../model/recipe";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../auth/auth.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.css']
})
export class RecipeDetailsComponent implements OnInit {

  recipe: Recipe = {
    id: 0,
    title: "",
    ingredients: [
      {amount: 0, name:'', unit:''},
    ],
    description: "",
    preparation: "",
    user: {id: 0, username:"", admin:true}
  }
  id?: number;

  user?:User;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private router: Router,
              public authService: AuthService) { }

  ngOnInit(): void {
   this.id = Number(this.route.snapshot.paramMap.get("id"));
   this.httpClient.get<Recipe>(`/api/recipes/${this.id}`).subscribe(result => this.recipe = result);
    this.httpClient.get<User>('/api/users/current').subscribe(user => this.user = user);
  }
}
