import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RecipeComponent} from "./recipe/recipe.component";
import {RecipeAddComponent} from "./recipe/recipe-add/recipe-add.component";
import {RecipeEditComponent} from "./recipe/recipe-edit/recipe-edit.component";
import {RecipeDetailsComponent} from "./recipe/recipe-details/recipe-details.component";
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {MyRecipeComponent} from "./recipe/my-recipe/my-recipe.component";
import {MyRecipeDetailsComponent} from "./recipe/my-recipe/my-recipe-details/my-recipe-details.component";
import {AuthGuard} from "./auth/auth.guard";

const routes: Routes = [
  {path:"", redirectTo:"recipes", pathMatch:"full"},
  {path:"recipes", component:RecipeComponent},
  {path:"recipes/:id", component: RecipeDetailsComponent},
  {path:"recipes/:id/edit", component: RecipeEditComponent, canActivate: [AuthGuard]},
  {path:"addRecipe", component: RecipeAddComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'myRecipes', component: MyRecipeComponent, canActivate: [AuthGuard]},
  {path: 'myRecipes/:id', component: MyRecipeDetailsComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
