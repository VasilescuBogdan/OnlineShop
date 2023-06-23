import {CartItemDto} from "./cartItem.dto";
import {ProfileDto} from "./profile.dto";

export interface TransactionDto {
  total: number;
  cartItems: CartItemDto[];
  profile: ProfileDto;
  date: Date;
}
