import { Component, OnInit } from '@angular/core';
import {TotalPriceForRecipe} from "../model/totalPriceForRecipe";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Route} from "@angular/router";

@Component({
  selector: 'app-price-calculation',
  templateUrl: './price-calculation.component.html',
  styleUrls: ['./price-calculation.component.css']
})
export class PriceCalculationComponent implements OnInit {

  totalAmountRow?: TotalPriceForRecipe[];
  pricePerUnit?: TotalPriceForRecipe[];
  id?:number;


  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    this.http.get<TotalPriceForRecipe[]>(`/api/recipes/${this.id}/calculation`).subscribe(result => this.totalAmountRow= result);
    this.http.get<TotalPriceForRecipe[]>(`/api/recipes/${this.id}/calculationPerUnit`).subscribe(result => this.pricePerUnit= result);
  }

}
