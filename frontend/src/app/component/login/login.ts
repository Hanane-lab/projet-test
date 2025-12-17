import { Component } from '@angular/core';

import { AuthService } from '../../auth';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-login', standalone: true,
  imports: [CommonModule,FormsModule   ],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {
  credentials = { email: '', password: '' };

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.credentials).subscribe(
      (response: any) => {
        localStorage.setItem('authToken', response.token);
        this.router.navigate(['/events']);
      },
      (error) => {
        alert('Erreur de connexion: ' + error.error.message);
      }
    );
  }
}
