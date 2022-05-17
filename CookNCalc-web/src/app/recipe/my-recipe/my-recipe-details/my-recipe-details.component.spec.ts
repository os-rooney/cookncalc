import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyRecipeDetailsComponent } from './my-recipe-details.component';

describe('MyRecipeDetailsComponent', () => {
  let component: MyRecipeDetailsComponent;
  let fixture: ComponentFixture<MyRecipeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyRecipeDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyRecipeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
