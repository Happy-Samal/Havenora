import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdashaboardComponent } from './edashaboard.component';

describe('EdashaboardComponent', () => {
  let component: EdashaboardComponent;
  let fixture: ComponentFixture<EdashaboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EdashaboardComponent]
    });
    fixture = TestBed.createComponent(EdashaboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
