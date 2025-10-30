import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTaskdashboard } from './admin-taskdashboard';

describe('AdminTaskdashboard', () => {
  let component: AdminTaskdashboard;
  let fixture: ComponentFixture<AdminTaskdashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminTaskdashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminTaskdashboard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
