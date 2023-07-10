import { UserDto } from "./userDto";

export interface TokenResponse {
  access_token: string;
  refresh_token: string;
  token_type: string;
  userId: string;
  userDto: UserDto;
}
