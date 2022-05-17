import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../auth.service";

@Component({
  selector: 'app-my-recipe',
  templateUrl: './my-recipe.component.html',
  styleUrls: ['./my-recipe.component.css']
})
export class MyRecipeComponent implements OnInit {

  user?: User;

  constructor(private http: HttpClient, public authService: AuthService) { }

  ngOnInit(): void {
    this.http.get<User>('/api/users/current').subscribe(user => this.user = user);
  }

}
