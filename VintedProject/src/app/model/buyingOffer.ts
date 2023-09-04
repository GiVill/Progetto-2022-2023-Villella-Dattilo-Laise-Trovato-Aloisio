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
import { BasicInsertion } from './basicInsertion';
import { User } from './user';

export interface BuyingOffer {
    id: number;
    price: number;
    status: BuyingOffer.StatusEnum;
    insertion: BasicInsertion;
    user?: User;
    paid: boolean
}
export namespace BuyingOffer {
    export type StatusEnum = 'PENDING' | 'APPROVED' | 'REFUSED';
    export const StatusEnum = {
        PENDING: 'PENDING' as StatusEnum,
      APPROVED: 'APPROVED' as StatusEnum,
        REFUSED: 'REFUSED' as StatusEnum
    };
}