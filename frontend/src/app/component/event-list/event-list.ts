import { Component, OnInit, inject } from '@angular/core';
import { EventService } from '../../event';
import { from } from 'rxjs';
import { AuthService } from '../../auth';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.html',
  styleUrls: ['./event-list.css']
})
export class EventListComponent implements OnInit {
  events: any[] = [];

  constructor(private eventService: EventService) {}

  ngOnInit() {
    this.loadEvents();
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
