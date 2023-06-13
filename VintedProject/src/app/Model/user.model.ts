import { Address } from "./address.model";
import { Payment } from "./payment.model";
import { BuyingOffer } from "./buying-offer.model";
import { Order } from "./order.model";
import { BasicInsertion } from "./basic-insertion.model";
import { Role } from "./Enumerated/role.model";
import { Gender } from "./Enumerated/gender.model";


export interface User {
  id: number;
  nickName: string;
  firstName: string;
  lastName: string;
  address: Address;
  email: string;
  password: string;
  role: Role;
  phoneNumber: number;
  birthDate: Date;
  registrationDate: Date;
  gender: Gender;
  photo: Blob;
  photoSrc: string;
  payments: Payment[];
  buyingOffers: BuyingOffer[];
  orders: Order[];
  insertions: BasicInsertion[];
  favorites: BasicInsertion[];
}

