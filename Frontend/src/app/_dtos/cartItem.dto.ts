import {ProductDto} from "./product.dto";

export interface CartItemDto {
  id: number;
  product: ProductDto;
  quantity: number;
  isReduced: boolean;
}
