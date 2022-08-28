import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyquestionsoreventsComponent } from './myquestionsorevents.component';

describe('MyquestionsoreventsComponent', () => {
  let component: MyquestionsoreventsComponent;
  let fixture: ComponentFixture<MyquestionsoreventsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyquestionsoreventsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyquestionsoreventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
