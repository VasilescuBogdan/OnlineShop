import {Component} from '@angular/core';
import {ProductDto} from "../_dtos/product.dto";
import {ProductService} from "../_services/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {

  product: ProductDto = {
    id: 0,
    name: '',
    specifications: '',
    category: '',
    provider: '',
    stock: 0,
    price: 0,
    image: ''
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

  onImageChosen(event: Event) {
    const fileInput = event.target as HTMLInputElement;
    const image: File | null = fileInput.files?.[0] || null;
    if (image) {
      const filePath: string = URL.createObjectURL(image);
      console.log('File Location:', filePath);
      this.product.image = filePath;
    }
  }
}
