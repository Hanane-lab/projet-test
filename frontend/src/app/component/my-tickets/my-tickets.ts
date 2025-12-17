import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-my-tickets',
   standalone: true,
  imports: [
    CommonModule,
    FormsModule   
  ],
  templateUrl: './my-tickets.html',
  styleUrl: './my-tickets.css',
})

export class MyTickets implements OnInit {
  tickets: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadUserTickets();
  }

  loadUserTickets() {
    const token = localStorage.getItem('authToken');
    const headers = { 'Authorization': `Bearer ${token}` };
    this.http.get<any[]>('http://localhost:8081/api/tickets/user', { headers })
      .subscribe(data => this.tickets = data);
  }
}
