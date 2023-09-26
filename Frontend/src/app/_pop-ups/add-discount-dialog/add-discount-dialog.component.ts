import {Component, Inject, OnInit} from '@angular/core';
import {DiscountDto} from "../../_dtos/discount.dto";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {ProductService} from "../../_services/product.service";

@Component({
    selector: 'app-add-discount-dialog',
    template: `
        <div mat-dialog-content>
            <mat-form-field>
                <mat-label>Value</mat-label>
                <input matInput type="number" [(ngModel)]="discount.value">
            </mat-form-field>
            <mat-form-field>
                <mat-label>Points</mat-label>
                <input matInput type="number" [(ngModel)]="discount.points">
            </mat-form-field>
        </div>
        <div mat-dialog-actions>
            <button mat-button (click)="addDiscount(discount)">Submit</button>
            <span class="toolbar-spacer"></span>
            <button mat-button color="warn" mat-dialog-close>Close</button>
        </div>
    `,
    styles: [`
        toolbar-spacer {
            flex: 1 1 auto;
        }
    `]
})
export class AddDiscountDialogComponent implements OnInit {

    discount: DiscountDto = {
        productId: 0,
        value: 0,
        points: 0,
    }


    constructor(@Inject(MAT_DIALOG_DATA) public data: number, private productService: ProductService) {
    }

    addDiscount(discount: DiscountDto) {
        console.log(discount);
        this.productService.addDiscount(discount).subscribe({
                next: (response) => {
                    console.log(response);
                    location.reload();
                },
                error: (error) => {
                    console.log(error);
                }
            }
        )
    }

    ngOnInit(): void {
        console.log(this.data);
        this.discount.productId = this.data;
    }
}
