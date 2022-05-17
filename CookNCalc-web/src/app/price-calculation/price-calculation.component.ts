import { Component, OnInit } from '@angular/core';
import {TotalAmountRow} from "../TotalAmountRow";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Route} from "@angular/router";

@Component({
  selector: 'app-price-calculation',
  templateUrl: './price-calculation.component.html',
  styleUrls: ['./price-calculation.component.css']
})
export class PriceCalculationComponent implements OnInit {

  totalAmountRow?: TotalAmountRow[];
  id?:number;


  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    this.http.get<TotalAmountRow[]>(`/api/test/${this.id}`).subscribe(result => this.totalAmountRow= result);
  }

}
