import { User } from "./user.model";
import { Payment } from "./payment.model";
import { BasicInsertion } from "./basic-insertion.model";

export interface Order {
  id : BigInt;
  date : Date;
  payment : Payment;
  number : Number;
  insertion : BasicInsertion;
  user : User
}
