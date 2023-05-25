import {Component, OnInit} from '@angular/core';
import {ProductService} from "../_services/product.service";
import {lastValueFrom, Observable} from "rxjs";
import {ProductDto} from "../_dtos/product.dto";
import {HttpErrorResponse} from "@angular/common/http";
import {UserAuthService} from "../_services/user-auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: ProductDto[] = [];
  constructor(private productService: ProductService, private userAuthService: UserAuthService) {
  }

  ngOnInit(): void {
    this.getAllProducts();
  }

  public async getAllProducts() {
    try {
      const response = await lastValueFrom(this.productService.getAllProducts());
      console.log(response);
      this.products = response;
    } catch (err) {
      console.log(err);
    }
  }

  public isAdmin() {
    return this.userAuthService.isAdmin();
  }

  public isClient() {
    return this.userAuthService.isClient();
  }

  public async deleteProduct(productID: number) {
    try {
      await this.productService.deleteProduct(productID).toPromise();
      location.reload();
    } catch (err) {
      console.log(err);
    }
  }
}
