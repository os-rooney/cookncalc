import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "./model/recipe";
import {AuthService} from "./auth/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  @Input()
  recipes?: Recipe[];

  // Navbar toggler
  isShown:boolean = false;

  constructor(public authService: AuthService) {
  }

  ngOnInit(): void {
  }
}
