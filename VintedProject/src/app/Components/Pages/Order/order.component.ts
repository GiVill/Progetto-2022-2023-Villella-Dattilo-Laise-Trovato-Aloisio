import {Component, Input, OnInit} from "@angular/core";
import {OrderService} from "../../../api/order.service";
import {Order} from "../../../model/order";
import {OrderDto} from "../../../model/orderDto";
import {InsertionService} from "../../../api/insertion.service";
import {PageBasicInsertionDto} from "../../../model/pageBasicInsertionDto";
import {Observable} from "rxjs";
import {BasicInsertionDto} from "../../../model/basicInsertionDto";



@Component({
  selector: 'app-private',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit{

  @Input() order!: OrderDto;
  insertions!: PageBasicInsertionDto

  constructor(private  insertionService: InsertionService) {
  }

  ngOnInit(): void {
    this.order.insertionIdList?.map((cartItem) =>
      this.insertionService.getAllInsertionByOrderId(cartItem).subscribe((data: PageBasicInsertionDto) => {
        this.insertions = data;
      })
    );
  }



}
