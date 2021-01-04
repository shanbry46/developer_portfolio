import React from "react";
import {
  BrowserRouter, Route
} from "react-router-dom";
import Authorize from "./Authorize"
import "./App.css";
import Home from "./Home"
export default function App() {
    return (
      <BrowserRouter >
        <Route path="/app" component={Home}></Route>
        <Route path="/" component={Authorize} exact></Route>
      </BrowserRouter>
    )

}
