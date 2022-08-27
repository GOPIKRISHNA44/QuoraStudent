import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidatepasswordComponent } from './validatepassword.component';

describe('ValidatepasswordComponent', () => {
  let component: ValidatepasswordComponent;
  let fixture: ComponentFixture<ValidatepasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ValidatepasswordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ValidatepasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
