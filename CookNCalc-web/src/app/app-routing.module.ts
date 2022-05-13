import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RecipeDetailsComponent} from "./recipe/recipe-details/recipe-details.component";
import {RecipeComponent} from "./recipe/recipe.component";
import {RecipeAddComponent} from "./recipe/recipe-add/recipe-add.component";

const routes: Routes = [
  {path:"", redirectTo:"recipes", pathMatch:"full"},
  {path:"recipes", component:RecipeComponent},
  {path:"recipes/:id", component: RecipeDetailsComponent},
  {path:"addRecipe", component: RecipeAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
