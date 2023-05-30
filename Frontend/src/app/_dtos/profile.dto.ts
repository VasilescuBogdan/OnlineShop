import {CartItemDto} from "./cartItem.dto";

export interface ProfileDto{

  username: String;

  firstName: String;

  lastName: String;

  email: String;

  number: String;

  points: Number;

  cart: CartItemDto[];
}
