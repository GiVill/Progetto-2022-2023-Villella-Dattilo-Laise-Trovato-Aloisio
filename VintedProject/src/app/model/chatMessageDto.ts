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
import { Chat } from './chat';

export interface ChatMessageDto {
    id?: number;
    sender?: number;
    reciver?: number;
    nickname?: string;
    message?: string;
    date?: Date;
    seen?: boolean;
    chat?: Chat;
    user1NameLastname?: string;
    user2NameLastname?: string;
    insertionId?: string;
    InsertionTitle?: string;
}