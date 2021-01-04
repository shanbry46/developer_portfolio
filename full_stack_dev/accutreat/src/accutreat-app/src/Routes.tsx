import React from "react";
import NavBar from "./Components/Common/NavBar/NavBar";
import UserAccount from "./Components/Pages/UserAccount/UserAccount";
import AddMedication from "./Components/Pages/AddMedication/AddMedication";
import Login from "./Components/Pages/Login/Login";
import SignUp from "./Components/Pages/SignUp/SignUp";
import Home from "./Components/Pages/Home/Home";
import "./index.css";
import Search from "./Components/Pages/Search/Search";
import {
  BrowserRouter as Router,
  Route,
  RouteComponentProps,
  Switch
} from "react-router-dom";
//Test
const RoutesWrap: React.FC = () => {
  return (
    <Router>
      <Route component={Routes} />
    </Router>
  );
};

class Routes extends React.Component<RouteComponentProps> {
  public constructor(props: RouteComponentProps) {
    super(props);
  }

  public render() {
    return (
      <div className="App">
        <NavBar />
        <div>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/account" component={UserAccount} />
            <Route path="/add-medication" component={AddMedication} />
            <Route path="/login" component={Login} />
            <Route path="/search" component={Search} />
            <Route path="/signup" component={SignUp} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default RoutesWrap;
