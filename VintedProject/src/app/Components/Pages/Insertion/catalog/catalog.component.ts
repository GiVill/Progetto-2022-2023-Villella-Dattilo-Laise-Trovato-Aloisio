import {Component, OnInit} from "@angular/core";
import {UserDto} from "../../../../model/userDto";
import {PageBasicInsertionDto} from "../../../../model/pageBasicInsertionDto";
import {InsertionService} from "../../../../api/insertion.service";
import {UserService} from "../../../../api/user.service";


@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})

export class CatalogComponent implements OnInit {
users: UserDto[] = [];
catalog: PageBasicInsertionDto | undefined;
page = 0;

constructor(private insertionService: InsertionService, private userService: UserService) {}

ngOnInit(): void {
  this.loadInsertions();
}

loadInsertions(): void {
  this.insertionService.getAll1(this.page).subscribe((insertions: PageBasicInsertionDto) => {
    this.catalog = insertions;

    // Extract all unique user IDs from the insertions
    const userIds = this.catalog.content?.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);

    if (userIds) {
      // Fetch all users by their IDs and store them in the users array
      userIds.forEach((userId) => {
        this.userService.getUserDtoById(userId).subscribe((user: UserDto) => {
          this.users.push(user);
        });
      });
    }

    console.log(this.catalog);
  });
}

loadmore() {
  this.page += 1;
  this.insertionService.getAll1(this.page).subscribe((insertions: PageBasicInsertionDto) => {
    // Aggiungi i nuovi prodotti alla lista esistente invece di sostituirla
    if (this.catalog?.content && insertions.content) {
      this.catalog.content.push(...insertions.content);

      // Fetch users for the new insertions and add them to the users array
      const userIds = insertions.content.map((insertion) => insertion.userId).filter((id, index, array) => array.indexOf(id) === index);
      userIds.forEach((userId) => {
        this.userService.getUserDtoById(userId).subscribe((user: UserDto) => {
          this.users.push(user);
        });
      });

      console.log(this.catalog);
    }
  });
}

// Metodo per ottenere l'utente corrispondente in base all'userId
getUserByUserId(userId: number): UserDto  {
  return <UserDto>this.users.find(user => user.id === userId);
}
}


