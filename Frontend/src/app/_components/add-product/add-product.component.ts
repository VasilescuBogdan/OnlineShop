import {Component, OnInit} from '@angular/core';
import {ProductDto} from "../../_dtos/product.dto";
import {ProductService} from "../../_services/product.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'app-add-product',
    templateUrl: './add-product.component.html',
    styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

    product: ProductDto = {
        id: null,
        name: '',
        specifications: '',
        category: '',
        provider: '',
        points: 0,
        price: 0,
        discount: null,
    }


    constructor(private productService: ProductService, private router: Router, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.product = this.activatedRoute.snapshot.data['product'];
    }

    addProduct() {
        this.productService.createProduct(this.product).subscribe({
            next: (response) => {
                this.router.navigate(['']).then(
                    r => console.log(r),
                    err => console.log(err)
                );
                console.log(response);
            },
            error: (error) => {
                console.log(error);
            }
        })

    }

}
