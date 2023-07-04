import {CartItemDto} from "./cartItem.dto";
import {ProfileDto} from "./profile.dto";

export interface TransactionItemDto {
  name: string;
  price: number;
  quantity: number;
}

export interface TransactionDto {
  totalCost: number;
  items: TransactionItemDto[];
  date: Date;
}
