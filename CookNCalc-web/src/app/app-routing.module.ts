import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RecipeDetailsComponent} from "./recipe/recipe-details/recipe-details.component";
import {RecipeComponent} from "./recipe/recipe.component";

const routes: Routes = [
  {path:"", redirectTo:"recipes", pathMatch:"full"},
  {path:"recipes", component:RecipeComponent},
  {path:"recipes/:id", component: RecipeDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
