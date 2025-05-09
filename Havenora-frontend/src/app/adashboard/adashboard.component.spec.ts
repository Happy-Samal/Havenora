import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdashboardComponent } from './adashboard.component';

describe('AdashboardComponent', () => {
  let component: AdashboardComponent;
  let fixture: ComponentFixture<AdashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdashboardComponent]
    });
    fixture = TestBed.createComponent(AdashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
