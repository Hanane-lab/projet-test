import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private baseUrl = 'http://localhost:8081/api/events';

  constructor(private http: HttpClient) {}

  getEvents() {
    return this.http.get<any[]>(this.baseUrl);
  }

  getEventById(id: number) {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  createEvent(event: any) {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('authToken')}`
    });
    return this.http.post(this.baseUrl, event, { headers });
  }
}
