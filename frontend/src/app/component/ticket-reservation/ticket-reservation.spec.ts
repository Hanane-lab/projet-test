import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketReservation } from './ticket-reservation';

describe('TicketReservation', () => {
  let component: TicketReservation;
  let fixture: ComponentFixture<TicketReservation>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketReservation]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TicketReservation);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
