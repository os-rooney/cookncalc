import { Component, OnInit } from '@angular/core';
import {TotalPriceForRecipe} from "../model/totalPriceForRecipe";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Route} from "@angular/router";
import {ingredientPriceForRecipe} from "../model/ingredientPriceForRecipe";

@Component({
  selector: 'app-price-calculation',
  templateUrl: './price-calculation.component.html',
  styleUrls: ['./price-calculation.component.css']
})
export class PriceCalculationComponent implements OnInit {
  ingredientPricePerMarket?: ingredientPriceForRecipe[];
  totalPriceForRecipe?: TotalPriceForRecipe[];
  pricePerUnit?: TotalPriceForRecipe[];
  id?:number;
  supermarket?:string;
  test = false;

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    this.http.get<TotalPriceForRecipe[]>(`/api/recipes/${this.id}/calculation`).subscribe(result => this.totalPriceForRecipe= result);
    this.http.get<TotalPriceForRecipe[]>(`/api/recipes/${this.id}/calculationPerUnit`).subscribe(result => this.pricePerUnit= result);

  }

  ingredientPriceDetail(supermarket:string){
    this.supermarket = supermarket;
    this.http.get<ingredientPriceForRecipe[]>(`/api/recipes/${this.id}/${supermarket}`).subscribe(result => this.ingredientPricePerMarket= result);
    this.test = true;
  }

}
