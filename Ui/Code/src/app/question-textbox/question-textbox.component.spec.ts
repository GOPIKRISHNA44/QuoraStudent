import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionTextboxComponent } from './question-textbox.component';

describe('QuestionTextboxComponent', () => {
  let component: QuestionTextboxComponent;
  let fixture: ComponentFixture<QuestionTextboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuestionTextboxComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionTextboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
