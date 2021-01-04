import { UserActionTypes, IUserSetCurrentState, IUserLogOut } from "./UserTypes";
import { IUser } from "../../Models/IUser";

export const successfullyLogInUser = (userInfo: IUser): IUserSetCurrentState => ({
  type: UserActionTypes.LOGIN,
  isUserLoggedIn: true,
  userInfo: userInfo
});

export const userLogout = (): IUserLogOut => ({
  type: UserActionTypes.LOGOUT,
});
