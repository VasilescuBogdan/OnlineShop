import { Component } from '@angular/core';
import {NgForm} from "@angular/forms";
import {ProductDto} from "../_dtos/product.dto";
import {ProductService} from "../_services/product.service";
import {lastValueFrom} from "rxjs";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {

  product: ProductDto = {
    name: "",
    specifications: "",
    category: "",
    provider: "",
    stock: 0,
    price: 0
  }


  constructor(private productService: ProductService) {
  }

  async addProduct(productForm: NgForm) {
    try {
      const response = await lastValueFrom(this.productService.createProduct(this.product));
      console.log(response);
    } catch (err) {
      console.log(err);
    }
  }
}
