import {CartItemDto} from "./cartItem.dto";

export interface ProfileDto{

  username: string;

  firstName: string;

  lastName: string;

  email: string;

  number: string;

  points: number;

  cart: CartItemDto[];
}
