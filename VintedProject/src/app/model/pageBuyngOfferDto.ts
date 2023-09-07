/**
 * OpenAPi Vinted
 * OpenApi documentation for Spring Security
 *
 * OpenAPI spec version: 1.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { BasicInsertionDto } from './basicInsertionDto';
import {PageableObject} from "./pageableObject";
import {OrderDto} from "./orderDto";
import {SortObject} from "./sortObject";
import {BuyingOfferDto} from "./buyingOfferDto";

export interface PageBuyngOfferDto {

  totalPages?: number;
  totalElements?: number;
  first?: boolean;
  last?: boolean;
  numberOfElements?: number;
  pageable?: PageableObject;
  size?: number;
  content?: Array<BuyingOfferDto>;
  number?: number;
  sort?: SortObject;
  empty?: boolean;

}