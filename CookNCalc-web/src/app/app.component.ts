import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Recipe} from "./Recipe";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  recipes?: Recipe[];

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.httpClient.get<Recipe[]>("/api").subscribe(result => this.recipes=result);
  }

}
