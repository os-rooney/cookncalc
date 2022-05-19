import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyRecipeDetailsEditComponent } from './my-recipe-details-edit.component';

describe('MyRecipeDetailsEditComponent', () => {
  let component: MyRecipeDetailsEditComponent;
  let fixture: ComponentFixture<MyRecipeDetailsEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyRecipeDetailsEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyRecipeDetailsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
