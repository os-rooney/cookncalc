import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-my-recipe',
  templateUrl: './my-recipe.component.html',
  styleUrls: ['./my-recipe.component.css']
})
export class MyRecipeComponent implements OnInit {

  user?: User;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<User>('/api/users/current').subscribe(user => this.user = user);
  }

}
