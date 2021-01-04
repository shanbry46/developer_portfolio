import { Reducer } from "redux";
import { UserActions, UserActionTypes, IUserState } from "./UserTypes";

const initialUserState: IUserState = {
  isLoggedIn: false,
  userInfo: undefined,
};

export const userReducer: Reducer<IUserState, UserActions> = (
  state = initialUserState,
  action
) => {
  switch (action.type) {
    case UserActionTypes.LOGIN: {
      return {
        ...state,
        isLoggedIn: true,
        userInfo: action.userInfo
      };
    }
    case UserActionTypes.LOGOUT: {
      return {
        ...state,
        username: undefined,
        isLoggedIn: false,
      };
    }
    
    default: {
      return state;
    }
  }
};
