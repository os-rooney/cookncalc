import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../auth/auth.service";
import {Recipe} from "../../model/recipe";
import {mergeMap} from "rxjs";

@Component({
  selector: 'app-my-recipe',
  templateUrl: './my-recipe.component.html',
  styleUrls: ['./my-recipe.component.css']
})

export class MyRecipeComponent implements OnInit {

  recipes?: Recipe[];

  user={
    id:0,
    admin:false,
    username:""
  }

  constructor(private http: HttpClient, public authService: AuthService) {
  }

  ngOnInit(): void {
    this.http.get<User>('/api/users/current')
      .pipe(mergeMap((user: User) => {
        this.user = user;
        return this.http.get<Recipe[]>('/api/myrecipes', {params: {...user}})
      }))
      .subscribe(recipes => this.recipes = recipes);
  }
}

