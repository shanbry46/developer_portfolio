import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import * as serviceWorker from "./serviceWorker";
import { Store } from "redux";
import { IApplicationState } from "./Store";
import configureStore from "./Store";
import { Provider } from "react-redux";
import Routes from "./Routes";

interface IProps {
  store: Store<IApplicationState>;
}

const Root: React.FunctionComponent<IProps> = (props) => {
  return (
    <Provider store={props.store}>
      <Routes />
    </Provider>
  )
}

const store = configureStore();
ReactDOM.render(
  <React.StrictMode>
    <Root store={store} />
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
