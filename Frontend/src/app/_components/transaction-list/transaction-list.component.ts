import {Component, OnInit} from '@angular/core';
import {TransactionDto} from "../../_dtos/transaction.dto";
import {TransactionService} from "../../_services/transaction.service";

@Component({
    selector: 'app-transaction-list',
    templateUrl: './transaction-list.component.html',
    styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {

    transactions: TransactionDto[] = [];
    displayedColumns = ['date', 'items', 'total'];
    displayedColumns1 = ['name', 'price', 'quantity', 'actual_price'];

    constructor(private transactionService: TransactionService) {
    }

    ngOnInit(): void {
        this.getTransactions();
    }

    public getTransactions() {

        this.transactionService.getTransactions().subscribe({
            next: (response) => {
                this.transactions = response;
                console.log(response);
            },
            error: (error) => {
                console.log(error);
            }
        });
    }
}
