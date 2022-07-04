import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterestsDialogComponent } from './interests-dialog.component';

describe('InterestsDialogComponent', () => {
  let component: InterestsDialogComponent;
  let fixture: ComponentFixture<InterestsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InterestsDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InterestsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
