import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  BASE_PATH = "http://localhost:8090/api/transactions";

  constructor(private httpClient: HttpClient) {
  }

  public addTransaction(totalPrice: number, totalPoints: number) {
    return this.httpClient.post(this.BASE_PATH, {"totalPoints": totalPoints, "totalPrice": totalPrice});
  }

  public getTransactions() {
    return this.httpClient.get(this.BASE_PATH);
  }
}
