import {Component, Input, OnInit} from "@angular/core";
import {OrderService} from "../../../api/order.service";
import {Order} from "../../../model/order";
import {OrderDto} from "../../../model/orderDto";
import {InsertionService} from "../../../api/insertion.service";
import {PageBasicInsertionDto} from "../../../model/pageBasicInsertionDto";
import {async, Observable} from "rxjs";
import {BasicInsertionDto} from "../../../model/basicInsertionDto";
import {ActivatedRoute, Route} from "@angular/router";



@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit{
  @Input() myOrder?: OrderDto
  insertions!: BasicInsertionDto[]

  constructor(private  insertionService: InsertionService,
              private  orderService: OrderService,
              private  route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.insertionService.getAllInsertionByOrderId(this.myOrder?.id!).subscribe((insertionData: BasicInsertionDto[]) => {
      this.insertions = insertionData ;})
    }
}
