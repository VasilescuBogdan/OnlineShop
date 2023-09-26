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

  public deleteCartItem(cartItemId: number) {
    return this.httpClient.delete(this.BASE_PATH + "/" + cartItemId);
  }

  public setQuantity(cartItemId: number, quantity: number) {
    return this.httpClient.patch(this.BASE_PATH + "/quantity/" + cartItemId + "?quantity=" + quantity, null);
  }

  public setIsReduced(cartItemId: number, isReduced: boolean) {
    return this.httpClient.patch(this.BASE_PATH + "/isReduced/" + cartItemId + "?is_reduced=" + isReduced, null);
  }

  emptyCart(cart: CartItemDto[]) {
    return this.httpClient.delete(this.BASE_PATH);
  }
}
