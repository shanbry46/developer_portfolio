import { IUser } from "../../Models/IUser";

export enum UserActionTypes {
  LOGIN = "USER/LOGIN",
  USERINFO = "USER/INFO",
  LOGOUT = "USER/LOGOUT",
}

export interface IUserState {
  readonly isLoggedIn: boolean;
  readonly userInfo: IUser | undefined;
}

export interface IUserSetCurrentState {
  type: UserActionTypes.LOGIN;
  isUserLoggedIn: boolean;
  userInfo: IUser;
}

export interface IUserLogOut {
  type: UserActionTypes.LOGOUT
}

export type UserActions = IUserSetCurrentState | IUserLogOut;
