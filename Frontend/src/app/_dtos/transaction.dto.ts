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
