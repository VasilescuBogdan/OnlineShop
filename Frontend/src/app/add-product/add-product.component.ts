import {Component} from '@angular/core';
import {AddProductDto} from "../_dtos/product.dto";
import {ProductService} from "../_services/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {

  product: AddProductDto = {
    id: 0,
    name: '',
    specifications: '',
    category: '',
    provider: '',
    points: 0,
    price: 0,
  }


  constructor(private productService: ProductService, private router: Router) {
  }

  addProduct() {
    this.productService.createProduct(this.product).subscribe(
      (response) => {
        this.router.navigate(['']);
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
