import React from "react";
import "./NavBar.css";
import { IApplicationState } from "../../../Store";
import { connect } from "react-redux";
import { NavLink, withRouter, RouteComponentProps } from "react-router-dom";
import UserInfo from "./UserInfo";
import { IUser } from "../../../Models/IUser";
import { userLogout } from "../../../Reducers/User/UserActions";

interface IProps extends RouteComponentProps {
  isLoggedIn: boolean;
  userInfo: IUser | undefined;
  userLogout: typeof userLogout;
}

class NavBar extends React.Component<IProps> {
  public constructor(props: IProps) {
    super(props);
  }

  private handleLogoutClick = () => {
    this.props.userLogout();
  };

  public render() {
    return (
      <div className="topnav">
        <NavLink to="/" activeClassName="active" exact>Home</NavLink>
        {this.props.isLoggedIn ? null : <NavLink to="/signup" activeClassName="active">Sign Up</NavLink>}
        {this.props.isLoggedIn ? null : <NavLink to="/login" activeClassName="active">Login</NavLink>}
        {this.props.isLoggedIn ? <NavLink to="/account" activeClassName="active">Account</NavLink> : null}
        <NavLink to="/search" activeClassName="active">Search</NavLink>
        <NavLink to="/add-medication" activeClassName="active">Add Medication</NavLink>
        {this.props.isLoggedIn ? <NavLink to="/login" activeClassName="active" onClick={() => this.handleLogoutClick()}>Logout</NavLink> : null }
        {this.props.isLoggedIn && this.props.userInfo ? <UserInfo name={this.props.userInfo.userName} /> : null}
      </div>
    );
  }
}

const mapStateToProps = (store: IApplicationState) => {
  return {
    isLoggedIn: store.user.isLoggedIn,
    userInfo: store.user.userInfo,
  };
};

const mapDispatchToProps = (dispatch: any) => {
  return {
    userLogout: () => dispatch(userLogout()),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(withRouter(NavBar));
