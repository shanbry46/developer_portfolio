import React from "react";
import "./Login.css";
import { successfullyLogInUser } from "../../../Reducers/User/UserActions";
import { connect } from "react-redux";
import { RouteComponentProps, withRouter } from "react-router-dom";
import { md5 } from '../../Common/md5';
import { attemptLogin } from "../../Services/UserService";
import { IUser } from "../../../Models/IUser";

var loginCount = 0;

//state type
interface IProps extends RouteComponentProps {
  successfullyLogInUser: typeof successfullyLogInUser;
}

interface ILoginState {
  email: string;
  password: string;
  message: string;
  isError: boolean;
  isLoading: boolean;
}

const initialState: ILoginState = {
  email: "",
  password: "",
  message: "",
  isError: false,
  isLoading: false,
};

class Login extends React.Component<IProps, ILoginState> {

  public constructor(props: IProps) {
    super(props);
    this.state = initialState;
  }

  public render() {
    return (
      <div className="wrapper">
        <div className="form-wrapper" id="login-fw">
          <h2>Login</h2>
          <form className="form">
            {this.state.isError && (
              <h3 className="error">{this.state.message}</h3>
            )}
            <div className="email">
              <label htmlFor="email">Email</label>
              <input
                type="email"
                name="email"
                placeholder="username@email.com"
                onChange={(e) =>
                  this.setState({ email: e.currentTarget.value })
                }
              />
            </div>
            <div className="password">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                name="password"
                onChange={(e) =>
                  this.setState({ password: e.currentTarget.value })
                }
              />
            </div>
            <div className="submit">
              <button
                className="submit-btn"
                type="submit"
                onClick={this.handleLoginAttempt}
                disabled={this.state.isLoading}
              >
                {this.state.isLoading ? "Logging In..." : "Login"}
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }

  private handleLoginAttempt = async (e: React.FormEvent) => {
    e.preventDefault();

    var loginResult = await attemptLogin(this.state.email, md5(this.state.password));
    console.log(loginResult);

    //replace with call to database
    if (!!loginResult) {
      this.onSuccessfulLogin(loginResult);
    }
    else {
      loginCount++;
      if (loginCount === 3) {
        alert("Reached max login attempts. Please make an account.");
        this.props.history.push("/signup");
        loginCount = 0;
      }
      else {
        alert("Invalid email and/or password.");
      }
    }
  };

  private onSuccessfulLogin = (u: IUser) => {
    this.props.successfullyLogInUser(u);
    this.props.history.push("/");
  };
}

const mapDispatchToProps = (dispatch: any) => {
  return {
    successfullyLogInUser: (u: IUser) => dispatch(successfullyLogInUser(u)),
  };
};

export default connect(null, mapDispatchToProps)(withRouter(Login));
