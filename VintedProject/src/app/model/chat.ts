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
import { ChatMessageDto } from './chatMessageDto';

export interface Chat {
    id?: number;
    sender?: number;
    reciver?: number;
    messages?: Array<ChatMessageDto>;
    basicInsertion?: BasicInsertion;
}
