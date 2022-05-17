import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RecipeComponent} from "./recipe/recipe.component";
import {RecipeAddComponent} from "./recipe/recipe-add/recipe-add.component";
import {RecipeEditComponent} from "./recipe/recipe-details/recipe-edit/recipe-edit.component";
import {RecipeDetailsComponent} from "./recipe/recipe-details/recipe-details.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {MyRecipeComponent} from "./recipe/my-recipe/my-recipe.component";

const routes: Routes = [
  {path:"", redirectTo:"recipes", pathMatch:"full"},
  {path:"recipes", component:RecipeComponent},
  {path:"recipes/:id", component: RecipeDetailsComponent},
  {path:"recipes/:id/edit", component: RecipeEditComponent},
  {path:"addRecipe", component: RecipeAddComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'myRecipes', component: MyRecipeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
