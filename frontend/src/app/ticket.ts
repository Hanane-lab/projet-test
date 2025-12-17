import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api';

export interface Ticket {
  id: number;
  ticketCode: string;
  eventId: number;
  eventTitle: string;
  eventDate: string;
  eventLocation: string;
  pricePaid: number;
  purchaseDate: string;
  status: 'VALID' | 'USED' | 'CANCELLED';
  qrCodeUrl: string;
  paymentStatus: string;
}

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiService = inject(ApiService);

  purchaseTicket(request: any): Observable<Ticket> {
    return this.apiService.post<Ticket>('/tickets/purchase', request);
  }

  getMyTickets(): Observable<Ticket[]> {
    return this.apiService.get<Ticket[]>('/tickets/my-tickets');
  }

  validateTicket(ticketCode: string): Observable<Ticket> {
    return this.apiService.post<Ticket>('/tickets/validate', { ticketCode });
  }

  useTicket(ticketCode: string): Observable<Ticket> {
    return this.apiService.post<Ticket>(`/tickets/${ticketCode}/use`, {});
  }

  cancelTicket(ticketId: number): Observable<void> {
    return this.apiService.post<void>(`/tickets/${ticketId}/cancel`, {});
  }

  getTicketByCode(ticketCode: string): Observable<Ticket> {
    return this.apiService.get<Ticket>(`/tickets/${ticketCode}`);
  }
}
