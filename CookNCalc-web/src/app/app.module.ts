import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { RecipeDetailsComponent } from './recipe/recipe-details/recipe-details.component';
import { RecipeComponent } from './recipe/recipe.component';
import { RecipeAddComponent } from './recipe/recipe-add/recipe-add.component';
import { PriceCalculationComponent } from './price-calculation/price-calculation.component';
import { RecipeDeleteComponent } from './recipe/recipe-delete/recipe-delete.component';
import {RecipeEditComponent} from "./recipe/recipe-edit/recipe-edit.component";
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { MyRecipeComponent } from './recipe/my-recipe/my-recipe.component';
import { MyRecipeDetailsComponent } from './recipe/my-recipe/my-recipe-details/my-recipe-details.component';
import { MyRecipeDetailsEditComponent } from './recipe/my-recipe/my-recipe-details/my-recipe-details-edit/my-recipe-details-edit.component';


@NgModule({
  declarations: [
    AppComponent,
    RecipeDetailsComponent,
    RecipeComponent,
    RecipeAddComponent,
    PriceCalculationComponent,
    RecipeDeleteComponent,
    RecipeEditComponent,
    LoginComponent,
    RegisterComponent,
    MyRecipeComponent,
    MyRecipeDetailsComponent,
    MyRecipeDetailsEditComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
