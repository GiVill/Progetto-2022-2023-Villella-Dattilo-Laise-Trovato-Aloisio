import { User } from "./user.model";
import { Order } from "./order.model";
import { PaymentMethod } from "./Enumerated/payment-method.model";
import { Status } from "./Enumerated/status.model";

export interface Payment {
  id : BigInt;
  paymentMethod : PaymentMethod;
  status : Status;
  order : Order;
  user : User;
}
