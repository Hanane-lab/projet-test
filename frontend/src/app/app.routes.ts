import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TicketReservationComponent } from './components/ticket-reservation/ticket-reservation.component';
import { EventListComponent } from './component/event-list/event-list';

const routes: Routes = [
  { path: '', redirectTo: '/events', pathMatch: 'full' },
  { path: 'events', component: EventListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'reserve/:id', component: TicketReservationComponent },
  { path: '**', redirectTo: '/events' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
