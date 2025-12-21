import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs';
import { EventService } from '../../services/event.service';
import { PaymentService } from '../../services/payment.service';

@Component({
  selector: 'app-ticket-reservation',
  standalone: true,
  imports: [CommonModule,
    FormsModule ],
  templateUrl: './ticket-reservation.component.html',
  styleUrl: './ticket-reservation.component.css'
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
    }).pipe(
      finalize(() => {
      })
    ).subscribe({
      next: (result: any) => {
        const stripe = (window as any).Stripe('pk_test_your_stripe_key');
        stripe.redirectToCheckout({
          sessionId: result.sessionId
        });
      },
      error: (error) => {
        console.error('Erreur lors de la création du paiement', error);
      }
    });
  }

}
