import { Component } from '@angular/core';
import { AuthService } from '../../auth';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
    standalone: true,
  imports: [CommonModule,FormsModule   ],
  templateUrl: './register.html',
  styleUrl: './register.css',
})


export class Register {
  user = { email: '', password: '', firstName: '', lastName: '' };

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    this.authService.register(this.user).subscribe(
      () => {
        alert('Inscription réussie!');
        this.router.navigate(['/login']);
      },
      (error) => {
        alert('Erreur d\'inscription: ' + error.error.message);
      }
    );
  }
}
