import {Component, OnDestroy, OnInit} from "@angular/core";
import {ChatDto} from "../../../model/chatDto";
import {CookiesService} from "../../../api/cookies.service";
import {NewMessageDto} from "../../../model/newMessageDto";
import {ChatMessageService} from "../../../api/chatMessage.service";
import {ChatMessageDto} from "../../../model/chatMessageDto";
import {ChatService} from "../../../api/chat.service";
import {interval, Subject, takeUntil} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {
  noChat: Boolean = false;
  chat?: Array<ChatDto> = [];
  selectedChat!: ChatDto
  messages: Array<ChatMessageDto> = [];
  newMessage: string = '';
  selectedUserId?: number | null = null;
  myId= Number(this.cookiesService.getUserId())

  private destroy$: Subject<void> = new Subject<void>();




  constructor(private chatMessageService: ChatMessageService,
              private chatService: ChatService,
              private snackBar: MatSnackBar,
              private cookiesService: CookiesService) {}

  async ngOnInit():  Promise<void> {
    if (!this.cookiesService.checkUserToken()){
        this.cookiesService.getRefreshToken()
    }
    this.loadChat().then(() => {
      if (this.chat && this.chat.length > 0) {
        this.selectedChat = this.chat[0];
        this.loadMessages(this.selectedChat.id!);
      }
    });

    interval(3000) // Intervallo di 3 secondi
      .pipe(
        takeUntil(this.destroy$) // Unsubscribe quando il componente viene distrutto
      )
      .subscribe(() => {
        this.loadMessages(this.selectedChat.id!);
      });
  }
  async loadChat(): Promise<void> {
    try {
      const [chats] = await Promise.all([this.chatService.allChatUser(this.myId).toPromise()]);
      this.chat = chats;
      if (chats) {
        this.selectedChat = chats[0];
      }
      this.loadMessages(this.selectedChat.id!);
    } catch (error) {
      this.noChat=true;
    }

  }

  async  loadMessages(userId: number):  Promise<void> {
    this.chatMessageService.allChatMessage(this.selectedChat.id!).subscribe(
      (messages: Array<ChatDto>) => {
        this.messages = messages;
      },
      () => {
      }
    );
  }

  sendMessage(): void {
    if (this.newMessage.trim() !== '') {
      let r: number;
      if (this.selectedChat.user2 == this.myId) {
         r = this.selectedChat.user1!

      }else{
         r = this.selectedChat.user2!
      }
      let newMessageDto: NewMessageDto = {
        reciver: r,
        sender: this.myId,
        message: this.newMessage,
        chatId: this.selectedChat.id,
      };

      this.chatMessageService.insertMessage(newMessageDto).subscribe(

        (response: string) => {

          if (response=="ok") {
            this.loadMessages(this.selectedChat.id!);
            this.newMessage = '';
            this.snackBar.open("Messaggio inviato." , "OK")
          }
        },
        (error) => {
          if (error.statusText=="OK") {
            this.loadMessages(this.selectedChat.id!);
            this.newMessage = '';
          }else {
            this.snackBar.open("Errore, messaggio non inviato." , "RIPROVA")
          }
        }
      );
    }
  }

  onSelectChat(chat: ChatDto): void {
    this.selectedChat = chat;
    this.loadMessages(chat.id!);
    this.selectedUserId = chat.id;
  }

  ngOnDestroy(): void {
    // Assicurati di distruggere l'intervallo quando il componente viene distrutto
    this.destroy$.next();
    this.destroy$.complete();
  }



}
