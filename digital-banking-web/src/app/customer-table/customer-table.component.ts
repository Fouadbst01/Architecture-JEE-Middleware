import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { catchError, map, Observable, throwError } from 'rxjs';
import { Customer, CustomerPage } from '../model/customer.model';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-table',
  templateUrl: './customer-table.component.html',
  styleUrls: ['./customer-table.component.css']
})
export class CustomerTableComponent implements OnInit {

  customers$!: Observable<CustomerPage> | undefined;
  errorMessage!: String;
  currentPage : number=0;
  pageSize : number =5;
  customerFromGroup!: FormGroup;

  constructor(private customerService: CustomerService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.customerFromGroup = this.fb.group({
      keyword: this.fb.control(""),
    })

    this.loadData();
  }

  loadData() {
    let key = this.customerFromGroup?.value.keyword
    this.customers$ = this.customerService.getCustomers(key,this.currentPage,this.pageSize).pipe(
      catchError(
        err => {
          this.errorMessage = err.message;
          return throwError(() => err)
        }
      )
    );
  }

  deleteCustomer(customer: Customer) {
    this.customerService.deleteCustomer(customer.id).subscribe({
      next:(response) =>{
        this.customers$ = this.customers$?.pipe(
          map( data => {
            let index = data.data.indexOf(customer)
            data.data.slice(index,1)
            return data;
          })
        );
      },
      error: err => {
        console.log(err);
      }
    });
  }
  goTopage(i:number){
    this.currentPage=i;
    this.loadData()
  }

}
