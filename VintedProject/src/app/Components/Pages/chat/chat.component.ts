import {Component, OnInit} from "@angular/core";
import {ChatDto} from "../../../model/chatDto";
import {ChatService} from "../../../api/chat.service";
import {CookiesService} from "../../../api/cookies.service";
import {NewMessageDto} from "../../../model/newMessageDto";


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  noChat: Boolean = false;
  users?: Array<ChatDto> = [];
  selectedUser!: ChatDto
  messages: Array<ChatDto> = [];
  newMessage: string = '';
  selectedUserId?: number | null = null;
  myId= Number(this.cookiesService.getUserId())


  constructor(private chatService: ChatService,
              private cookiesService: CookiesService) {}

  ngOnInit(): void {
    this.loadUsers().then(() => {
      if (this.users && this.users.length > 0) {
        this.selectedUser = this.users[0];
        this.loadMessages(this.selectedUser.reciver!);
      }
    });
  }
  async loadUsers(): Promise<void> {
    try {
      const [users] = await Promise.all([this.chatService.allChatUser(this.myId).toPromise()]);
      this.users = users;
      if (users) {
        this.selectedUser = users[0];
      }
      console.log(users);
      this.loadMessages(this.selectedUser.reciver!);

    } catch (error) {
      this.noChat=true;
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
  }

  sendMessage(): void {
    if (this.newMessage.trim() !== '') {
      const newMessageDto: NewMessageDto = {
        sender: this.myId,
        reciver: this.selectedUser.reciver,
        nickname: this.selectedUser.nickname,
        message: this.newMessage
      };
      console.log(newMessageDto)
      this.chatService.insertMessage(newMessageDto).subscribe(

        (response: string) => {
          console.log(response)
          if (response=="ok") {
            this.loadMessages(this.selectedUser.reciver!);
            this.newMessage = '';
          }
        },
        (error) => {
          if (error.statusText=="OK") {
            this.loadMessages(this.selectedUser.reciver!);
            this.newMessage = '';
          }else {
            console.error('Error sending message:', error);
          }
        }
      );
    }
  }

  onSelectUser(user: ChatDto): void {
    console.log("Before selecting user:", user);
    this.selectedUser = user;
    console.log("After selecting user:", this.selectedUser);
    this.loadMessages(user.reciver!);
    this.selectedUserId = user.id;
  }

}
