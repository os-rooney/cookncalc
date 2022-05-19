import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Recipe} from "./model/recipe";
import {AuthService} from "./auth/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{


  constructor(public authService: AuthService) {
  }

  ngOnInit(): void {
  }

  @Input()
  recipes?: Recipe[];

}
