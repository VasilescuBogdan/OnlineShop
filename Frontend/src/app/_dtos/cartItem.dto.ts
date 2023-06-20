import {GetProductDto} from "./product.dto";

export interface CartItemDto {
  product: GetProductDto;
  quantity: number;
}
