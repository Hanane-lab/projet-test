import { Component, OnInit, inject } from '@angular/core';
import { EventService } from '../../event';
import { from } from 'rxjs';
import { AuthService } from '../../auth';
import { CommonModule, DatePipe, CurrencyPipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-event-list',standalone: true,
  imports: [
    CommonModule,
    DatePipe,
    CurrencyPipe
  ],
  templateUrl: './event-list.html',
  styleUrls: ['./event-list.css']
})
export class EventList implements OnInit {
  events: any[] = [];

  constructor(private eventService: EventService, private router: Router) {}

  ngOnInit() {
    this.loadEvents();
  }
  bookTicket(eventId: number) {
    this.router.navigate(['/reserve', eventId]);
  }
  loadEvents() {
    this.eventService.getEvents().subscribe(
      (data) => {
        this.events = data;
      },
      (error) => {
        console.error('Erreur lors du chargement des événements', error);
      }
    );
  }
}
