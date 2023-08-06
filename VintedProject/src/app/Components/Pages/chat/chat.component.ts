import { Component, OnInit } from '@angular/core';
import { ChatDto} from "../../../Model/chatDto";
import { ChatService} from "../../../service/chat.service";
import {NewMessageDto} from "../../../Model/newMessageDto";
import {CookiesService} from "../../../service/cookies.service"; // Import your service

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  users?: Array<ChatDto> = [];
  selectedUser!: ChatDto
  messages: Array<ChatDto> = [];
  newMessage: string = '';
  myId= Number(this.cookiesService.getUserId())

  constructor(private chatService: ChatService,
              private cookiesService: CookiesService) {}

  ngOnInit(): void {
    this.loadUsers();
  }
  async loadUsers(): Promise<void> {
    try {
      const [users] = await Promise.all([this.chatService.allChatUser(this.myId).toPromise()]);
      this.users = users;
      if (users) {
        this.selectedUser = users[0];
      }
      console.log(users);
      this.loadMessages(this.selectedUser.id!);
    } catch (error) {
      console.error('Error fetching users:', error);
    }
  }
  loadMessages(userId: number): void {
    this.chatService.allChatMessage(this.myId, userId).subscribe(
      (messages: Array<ChatDto>) => {
        this.messages = messages;
      },
      (error) => {
        console.error('Error fetching messages:', error);
      }
    );
    console.log(this.selectedUser)
  }

  sendMessage(): void {
    if (this.newMessage.trim() !== '') {
      const newMessageDto: NewMessageDto = {
        idUser1: this.myId,
        idUser2: this.selectedUser.reciver,
        message: this.newMessage
      };
      console.log(newMessageDto)
      this.chatService.insertMessage(newMessageDto).subscribe(
        (response: string) => {
          this.loadMessages(this.selectedUser.reciver!);
          this.newMessage = '';
        },
        (error) => {
          console.error('Error sending message:', error);
        }
      );
    }
  }

  onSelectUser(user: ChatDto): void {
    console.log("Before selecting user:", user);
    this.selectedUser = user;
    console.log("After selecting user:", this.selectedUser);
    this.loadMessages(user.reciver!);
  }

}
