export interface AddProductDto{
  id: number;
  name: string;
  specifications: string;
  price: number;
  stock: number;
  provider: string;
  category: string;
}

export interface GetProductDto{
  id: number;
  name: string;
  specifications: string;
  price: number;
  stock: number;
  provider: string;
  category: string;
  discount: {
    id: number;
    value: number;
    points: number;
  }
}
