import {AfterContentInit, Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../auth.service";
import {Recipe} from "../../Recipe";
import {HttpParams} from "@angular/common/http";

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
    this.http.get<User>('/api/users/current').subscribe(user =>{
      this.user = user;
      this.getRecipes()
      }
    );
  }

  //mergeMap

  getRecipes(){
    this.http.get<Recipe[]>("/api/myrecipe", {params: this.user}).subscribe(result => this.recipes=result);
  }
}

