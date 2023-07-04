export interface ProductDto {
    id: number | null;
    name: string;
    specifications: string;
    price: number;
    points: number;
    provider: string;
    category: string;
    discount: {
        id: number;
        value: number;
        points: number;
    } | null;
}
