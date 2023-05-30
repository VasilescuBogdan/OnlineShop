import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CartItemDto} from "../_dtos/cartItem.dto";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  BASE_PATH = "http://localhost:8090/api/cart";

  constructor(private httpClient: HttpClient) {
  }

  public addCartItem(cartItemDto: CartItemDto) {
    return this.httpClient.post(this.BASE_PATH, cartItemDto);
  }
}
