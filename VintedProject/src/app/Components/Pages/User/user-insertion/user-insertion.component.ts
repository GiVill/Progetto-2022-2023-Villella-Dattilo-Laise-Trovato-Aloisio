import {Component, Input, OnInit} from "@angular/core";
import {UserDto} from "../../../../model/userDto";
import {BasicInsertionDto} from "../../../../model/basicInsertionDto";
import {InsertionService} from "../../../../api/insertion.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../api/user.service";


@Component({
  selector: 'app-user-insertion',
  templateUrl: './user-insertion.component.html',
  styleUrls: ['./user-insertion.component.css']
})
export class UserInsertionComponent implements OnInit{

  @Input() user: UserDto | undefined;
  @Input() insertion: BasicInsertionDto | undefined;


  constructor(private insertionService: InsertionService,
              private route: ActivatedRoute,
              private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUserDtoById(this.user?.id!).subscribe(
      (data: UserDto) => {
        this.user = data;

      },
      (error) => {
        console.log('Si è verificato un errore durante il recupero dell\'inserzione:', error);
      }
    );
  }




}
