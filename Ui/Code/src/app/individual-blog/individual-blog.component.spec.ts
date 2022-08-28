import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndividualBlogComponent } from './individual-blog.component';

describe('IndividualBlogComponent', () => {
  let component: IndividualBlogComponent;
  let fixture: ComponentFixture<IndividualBlogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndividualBlogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndividualBlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
