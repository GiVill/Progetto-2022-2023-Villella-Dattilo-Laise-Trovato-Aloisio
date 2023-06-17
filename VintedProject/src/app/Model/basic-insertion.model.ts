import { Order } from "./order.model";
import { User } from "./user.model";
import { BuyingOffer } from "./buying-offer.model";

export interface BasicInsertion {
  id : number;
  title : String;
  price : number;
  brand: string;
  creationDate : Date;
  endDate : Date;
  description : String;
  image : Blob;
  condition : String;
  usersFavorites : User[];
  buyingOffers : BuyingOffer[];
  order : Order;
  userId : number;
  size: string;
  imageSrc?: string;
  additionalImages
}
