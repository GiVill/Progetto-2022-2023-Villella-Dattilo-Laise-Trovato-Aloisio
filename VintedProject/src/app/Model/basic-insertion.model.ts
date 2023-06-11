import { Order } from "./order.model";
import { User } from "./user.model";
import { BuyingOffer } from "./buying-offer.model";

export interface BasicInsertion {
  id : BigInt;
  title : String;
  price : number;
  creationDate : Date;
  endDate : Date;
  description : String;
  image : Blob;
  condition : String;
  usersFavorites : User[];
  buyingOffers : BuyingOffer[];
  order : Order;
  user : User
  imageSrc?: string;
}
