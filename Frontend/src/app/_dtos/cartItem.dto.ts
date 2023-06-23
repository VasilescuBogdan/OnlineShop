import {GetProductDto} from "./product.dto";

export interface CartItemDto {
  id: number;
  product: GetProductDto;
  quantity: number;
  isReduced: boolean;
}
