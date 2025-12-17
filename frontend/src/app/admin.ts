import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  role: 'ADMIN' | 'ORGANIZER' | 'PARTICIPANT';
  enabled: boolean;
  createdAt: string;
}

export interface Stats {
  totalUsers: number;
  totalEvents: number;
  totalTickets: number;
  totalRevenue: number;
  upcomingEvents: number;
  activeOrganizers: number;
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private http = inject(HttpClient);
  private readonly API_URL = 'http://localhost:8080/api/admin';

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.API_URL}/users`);
  }

  updateUserRole(userId: number, role: string): Observable<User> {
    return this.http.put<User>(`${this.API_URL}/users/${userId}/role?role=${role}`, {});
  }

  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/users/${userId}`);
  }

  getStats(): Observable<Stats> {
    return this.http.get<Stats>(`${this.API_URL}/stats`);
  }

  toggleUserStatus(userId: number, enabled: boolean): Observable<User> {
    return this.http.put<User>(`${this.API_URL}/users/${userId}/status`, { enabled });
  }
}
