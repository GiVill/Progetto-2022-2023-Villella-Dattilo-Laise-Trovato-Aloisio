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

export interface BasicInsertionDto {
    id?: number;
    title: string;
    price: number;
    description: string;
    creationDate?: string;
    isPrivate?: boolean;
    imageName?: string;
    brand?: BasicInsertionDto.BrandEnum;
    category?: BasicInsertionDto.CategoryEnum;
    available?: boolean;
    userId: number;
}
export namespace BasicInsertionDto {
    export type BrandEnum = 'Adidas' | 'Puma' | 'Nike' | 'Microsoft' | 'Xiaomi' | 'Nothing' | 'Guess' | 'Pumpling' | 'Fila' | 'Onze' | 'Gojiang' | 'Rebook' | 'New_Balance' | 'Asix' | 'Sony' | 'ALTRO';
    export const BrandEnum = {
        Adidas: 'Adidas' as BrandEnum,
        Puma: 'Puma' as BrandEnum,
        Nike: 'Nike' as BrandEnum,
        Microsoft: 'Microsoft' as BrandEnum,
        Xiaomi: 'Xiaomi' as BrandEnum,
        Nothing: 'Nothing' as BrandEnum,
        Guess: 'Guess' as BrandEnum,
        Pumpling: 'Pumpling' as BrandEnum,
        Fila: 'Fila' as BrandEnum,
        Onze: 'Onze' as BrandEnum,
        Gojiang: 'Gojiang' as BrandEnum,
        Rebook: 'Rebook' as BrandEnum,
        NewBalance: 'New_Balance' as BrandEnum,
        Asix: 'Asix' as BrandEnum,
        Sony: 'Sony' as BrandEnum,
        ALTRO: 'ALTRO' as BrandEnum
    };
    export type CategoryEnum = 'ELETTRONICA' | 'COLLEZIONI' | 'ABBIGLIAMENTO' | 'BARCHE' | 'SPORT' | 'DONNA' | 'UOMO' | 'BAMBINI' | 'ALTRO';
    export const CategoryEnum = {
        ELETTRONICA: 'ELETTRONICA' as CategoryEnum,
        COLLEZIONI: 'COLLEZIONI' as CategoryEnum,
        ABBIGLIAMENTO: 'ABBIGLIAMENTO' as CategoryEnum,
        BARCHE: 'BARCHE' as CategoryEnum,
        SPORT: 'SPORT' as CategoryEnum,
        DONNA: 'DONNA' as CategoryEnum,
        UOMO: 'UOMO' as CategoryEnum,
        BAMBINI: 'BAMBINI' as CategoryEnum,
        ALTRO: 'ALTRO' as CategoryEnum
    };
}
