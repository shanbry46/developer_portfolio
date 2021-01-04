import { combineReducers, createStore, Store } from "redux";
import { userReducer } from "./Reducers/User/UserReducer";
import { IUserState } from "./Reducers/User/UserTypes";

export interface IApplicationState {
  user: IUserState;
}

const rootReducer = combineReducers<IApplicationState>({
  user: userReducer,
});

export default function configureStore(): Store<IApplicationState> {
  const store = createStore(rootReducer, undefined, undefined);
  return store;
}
