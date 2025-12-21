import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private baseUrl = 'http://localhost:8081/api/payments';

  constructor(private http: HttpClient) {}

  createPaymentIntent(paymentData: any) {
    return this.http.post(`${this.baseUrl}/create-payment-intent`, paymentData);
  }
}
