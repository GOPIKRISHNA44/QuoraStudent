import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TextdisplayComponent } from './textdisplay.component';

describe('TextdisplayComponent', () => {
  let component: TextdisplayComponent;
  let fixture: ComponentFixture<TextdisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TextdisplayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TextdisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
