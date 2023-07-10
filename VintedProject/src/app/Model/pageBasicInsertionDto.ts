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
import { PageableObject } from './pageableObject';
import { SortObject } from './sortObject';

export interface PageBasicInsertionDto {
    totalElements?: number;
    totalPages?: number;
    size?: number;
    content?: Array<BasicInsertionDto>;
    number?: number;
    sort?: SortObject;
    first?: boolean;
    last?: boolean;
    numberOfElements?: number;
    pageable?: PageableObject;
    empty?: boolean;
    imageName: String;
}