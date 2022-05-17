import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyRecipeAddComponent } from './my-recipe-add.component';

describe('MyRecipeAddComponent', () => {
  let component: MyRecipeAddComponent;
  let fixture: ComponentFixture<MyRecipeAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyRecipeAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyRecipeAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
