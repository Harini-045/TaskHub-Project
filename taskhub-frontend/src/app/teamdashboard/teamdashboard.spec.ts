import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Teamdashboard } from './teamdashboard';

describe('Teamdashboard', () => {
  let component: Teamdashboard;
  let fixture: ComponentFixture<Teamdashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Teamdashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Teamdashboard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
