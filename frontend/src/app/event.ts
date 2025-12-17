import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api';
import { format } from 'date-fns';

export interface Event {
  id: number;
  title: string;
  description: string;
  location: string;
  startDate: string;
  endDate: string;
  category: string;
  maxParticipants?: number;
  currentParticipants: number;
  price: number;
  imageUrl?: string;
  organizerName: string;
  organizerId: number;
  status: 'UPCOMING' | 'ONGOING' | 'COMPLETED' | 'CANCELLED';
  createdAt: string;
  isFull: boolean;
  hasAvailableSpots: boolean;
  isFavorite?: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private apiService = inject(ApiService);

  getAllEvents(page: number = 0, size: number = 10): Observable<any> {
    return this.apiService.get('/events', {
      page,
      size,
      sortBy: 'startDate',
      direction: 'asc'
    });
  }

  getEvent(id: number): Observable<Event> {
    return this.apiService.get<Event>(`/events/${id}`);
  }

  createEvent(eventData: any): Observable<Event> {
    const data = {
      ...eventData,
      startDate: format(eventData.startDate, 'yyyy-MM-dd\'T\'HH:mm:ss'),
      endDate: format(eventData.endDate, 'yyyy-MM-dd\'T\'HH:mm:ss')
    };

    return this.apiService.post<Event>('/events', data);
  }

  updateEvent(id: number, eventData: any): Observable<Event> {
    const data: any = { ...eventData };

    if (eventData.startDate) {
      data.startDate = format(eventData.startDate, 'yyyy-MM-dd\'T\'HH:mm:ss');
    }
    if (eventData.endDate) {
      data.endDate = format(eventData.endDate, 'yyyy-MM-dd\'T\'HH:mm:ss');
    }

    return this.apiService.put<Event>(`/events/${id}`, data);
  }

  deleteEvent(id: number): Observable<void> {
    return this.apiService.delete<void>(`/events/${id}`);
  }

  searchEvents(params: any): Observable<any> {
    const searchParams = { ...params };

    if (searchParams.startDate) {
      searchParams.startDate = format(searchParams.startDate, 'yyyy-MM-dd\'T\'HH:mm:ss');
    }
    if (searchParams.endDate) {
      searchParams.endDate = format(searchParams.endDate, 'yyyy-MM-dd\'T\'HH:mm:ss');
    }

    return this.apiService.get('/events/search', searchParams);
  }

  toggleFavorite(eventId: number): Observable<void> {
    return this.apiService.post<void>(`/events/${eventId}/favorite`, {});
  }

  getFavoriteEvents(): Observable<Event[]> {
    return this.apiService.get<Event[]>('/events/favorites');
  }

  getUpcomingEvents(limit: number = 5): Observable<Event[]> {
    return this.apiService.get<Event[]>(`/events/upcoming?limit=${limit}`);
  }
}
