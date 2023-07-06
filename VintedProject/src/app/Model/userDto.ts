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

export interface UserDto {
    id?: number;
    nickName: string;
    firstName: string;
    lastName: string;
    email?: string;
    birthDate?: string;
    gender?: UserDto.GenderEnum;
    addressStreet?: string;
    addressNumber?: number;
    addressCity?: string;
    addressCap?: number;
    addressState?: string;
    addressRegion?: string;
    photo?: Blob;
    imagepath: string;
}
export namespace UserDto {
    export type GenderEnum = 'MALE' | 'FEMALE' | 'OTHER';
    export const GenderEnum = {
        MALE: 'MALE' as GenderEnum,
        FEMALE: 'FEMALE' as GenderEnum,
        OTHER: 'OTHER' as GenderEnum
    };
}
