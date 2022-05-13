import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "../../Recipe";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-recipe-delete',
  templateUrl: './recipe-delete.component.html',
  styleUrls: ['./recipe-delete.component.css']
})
export class RecipeDeleteComponent implements OnInit {

  id?:number;

  recipes?: Recipe[];

  constructor(private http:HttpClient,private route: ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));
  }

  deleteRecipe(){
    this.http.delete<Recipe>(`/api/deleteRecipe/${this.id}`).subscribe();
    console.log(this.id);

    this.http.get<Recipe[]>("/api").subscribe(result => this.recipes=result);
    this.router.navigate(['/recipes']);
  }
}
