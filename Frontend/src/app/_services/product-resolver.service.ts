import { Injectable } from '@angular/core';
import {ProductService} from "./product.service";
import {ProductDto} from "../_dtos/product.dto";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductResolverService implements Resolve<ProductDto> {

  constructor(private productService: ProductService) {
  }

    resolve(route: ActivatedRouteSnapshot): Observable<ProductDto> {
        const id = Number(route.paramMap.get("productId"));

        if (id) {
            return this.productService.getProductById(id);
        } else {
            return of(this.getProduct());
        }
    }

    private getProduct() {
        return {
            id: 0,
            name: '',
            specifications: '',
            category: '',
            provider: '',
            points: 0,
            price: 0,
            discount: null,
        }
    }
}
