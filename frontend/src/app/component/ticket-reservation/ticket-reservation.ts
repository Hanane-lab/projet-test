import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../event';


@Component({
  selector: 'app-ticket-reservation',
  templateUrl: './ticket-reservation.component.html',
  styleUrls: ['./ticket-reservation.component.css']
})
export class TicketReservationComponent implements OnInit {
  eventId: number = 0;
  event: any = {};
  ticketCount: number = 1;

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private paymentService: PaymentService,
    private router: Router
  ) {}

  ngOnInit() {
    this.eventId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadEventDetails();
  }

  loadEventDetails() {
    this.eventService.getEventById(this.eventId).subscribe(
      (data) => {
        this.event = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des détails de l\'événement', error);
      }
    );
  }

  bookTicket() {
    this.paymentService.createPaymentIntent({
      eventId: this.eventId,
      ticketCount: this.ticketCount,
      totalAmount: this.event.price * this.ticketCount
    }).then((result) => {
      // Gérer le paiement via Stripe
      const stripe = (window as any).Stripe('pk_test_your_stripe_key');
      stripe.redirectToCheckout({
        sessionId: result.sessionId
      });
    });
  }
}
