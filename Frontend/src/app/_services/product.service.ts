import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductDto} from "../_dtos/product.dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  BASE_PATH = "http://localhost:8090/api/products";

  constructor(private httpClient: HttpClient) { }

  public createProduct(newProduct: ProductDto) {
    return this.httpClient.post<ProductDto>(this.BASE_PATH, newProduct);
  }
}
