import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageNoteFoudComponent } from './page-note-foud.component';

describe('PageNoteFoudComponent', () => {
  let component: PageNoteFoudComponent;
  let fixture: ComponentFixture<PageNoteFoudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageNoteFoudComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageNoteFoudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
