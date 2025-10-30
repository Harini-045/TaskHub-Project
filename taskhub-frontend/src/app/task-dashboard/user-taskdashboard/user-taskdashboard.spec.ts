import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTaskdashboard } from './user-taskdashboard';

describe('UserTaskdashboard', () => {
  let component: UserTaskdashboard;
  let fixture: ComponentFixture<UserTaskdashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserTaskdashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserTaskdashboard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
