import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ProductDto} from "../_dtos/product.dto";
import {Observable} from "rxjs";
import {UserAuthService} from "./user-auth.service";
import {DiscountDto} from "../_dtos/discount.dto";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  BASE_PATH = "http://localhost:8090/api/products";

  requestHeader = new HttpHeaders({"No-Auth": "True"});

  constructor(private httpClient: HttpClient) {
  }

  public createProduct(newProduct: ProductDto) {
    return this.httpClient.post<ProductDto>(this.BASE_PATH, newProduct);
  }

  public getAllProducts() {
    return this.httpClient.get<ProductDto[]>(this.BASE_PATH, {headers: this.requestHeader});
  }

  public deleteProduct(Id: number) {
    return this.httpClient.delete(this.BASE_PATH + "/" + Id);
  }

  public addDiscount(newDiscount: DiscountDto) {
    return this.httpClient.post<DiscountDto>(this.BASE_PATH + "/discount", newDiscount);
  }

  public deleteDiscount(productId: number) {
    return this.httpClient.delete(this.BASE_PATH + "/discount/" + productId);
  }

  public getDiscount(productId: number) {
    return this.httpClient.get<DiscountDto>(this.BASE_PATH + "/discount/" + productId);
  }
}
