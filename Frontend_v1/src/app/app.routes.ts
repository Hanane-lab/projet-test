import { Routes } from '@angular/router';
import { EventListComponent } from './component/event-list/event-list.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { TicketReservationComponent } from './component/ticket-reservation/ticket-reservation.component';

export const routes: Routes = [ { path: '', redirectTo: 'events', pathMatch: 'full' },
  { path: 'events', component: EventListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'reserve/:id', component: TicketReservationComponent },
  { path: 'my-tickets', component: TicketReservationComponent },
  { path: '**', redirectTo: 'events' }];
