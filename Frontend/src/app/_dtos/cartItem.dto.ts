import {ProductDto} from "./product.dto";

export interface CartItemDto {
  product: ProductDto;
  quantity: number;
}
