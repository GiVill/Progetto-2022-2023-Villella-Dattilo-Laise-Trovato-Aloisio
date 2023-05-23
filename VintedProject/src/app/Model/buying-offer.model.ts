import { Status } from "./Enumerated/status.model";
import { BasicInsertion } from "./basic-insertion.model";
import { User } from "./user.model";


export interface BuyingOffer {
  id : BigInt;
  price : number;
  status : Status;
  insertion : BasicInsertion;
  user : User
}
