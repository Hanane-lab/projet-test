import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventList } from './component/event-list/event-list';
import { Login } from './component/login/login';
import { Register } from './component/register/register';
import { TicketReservation } from './component/ticket-reservation/ticket-reservation';
import { MyTickets } from './component/my-tickets/my-tickets';

export const routes: Routes = [
  { path: '', redirectTo: '/events', pathMatch: 'full' },
  { path: 'events', component: EventList },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'reserve/:id', component: TicketReservation },
  { path: '**', redirectTo: '/events' },{ path: 'my-tickets', component: MyTickets }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
