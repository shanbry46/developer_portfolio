import * as React from 'react';
import './SignUp.css';
import { successfullyLogInUser } from "../../../Reducers/User/UserActions";
import { md5 } from '../../Common/md5';
import { RouteComponentProps, withRouter } from "react-router-dom";
import { IUser } from "../../../Models/IUser";
import { connect } from "react-redux";
import { doesUserExist, createUser } from '../../Services/UserService'

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

class SignUp extends React.Component<IProps, ILoginState>
{
   public constructor(props: IProps) {
      super(props);
   }

   private signUpHandler = async () => {
      let emailToAdd = (document.getElementById("email") as HTMLInputElement).value;
      let passwordToAdd = md5((document.getElementById("password") as HTMLInputElement).value);
      let nameToAdd = (document.getElementById("username") as HTMLInputElement).value;
      let zipToAdd = Number((document.getElementById("zip") as HTMLInputElement).value);

      let userToAdd = {
         user_name: nameToAdd,
         password_hash: passwordToAdd,
         email: emailToAdd,
         zip_code: zipToAdd
      }

      // check for whether the user exists or not already from user service
      var userExists = await doesUserExist(emailToAdd);
      if (userExists) {
         alert("User already exists. Please use a different email.");
      }
      else {
         var user = await createUser(userToAdd);
         if (!!user) {
            this.props.successfullyLogInUser(user);
            this.props.history.push("/");
         }
      }
   }

   public render() {
      return (
         <div className='wrapper'>
            <div className='form-wrapper'>
               <h2>Sign Up</h2>
                  <div className='username'>
                     <label htmlFor="username">Name</label>
                     <input type='username' name='username' id="username"/>
                  </div>
                  <div className='zip'>
                     <label htmlFor="zip_code">Zip Code</label>
                     <input type='text' name='zip' id="zip"/>
                  </div>
                  <div className='email'>
                     <label htmlFor="email">Email</label>
                     <input type='email' name='email' id="email"/>
                  </div>
                  <div className='password'>
                     <label htmlFor="password">Password</label>
                     <input type='password' name='password' id="password"/>
                  </div>
                  <div className='submit'>
                     <button className='submit-btn' onClick={this.signUpHandler}>Register Me</button>
                  </div>
            </div>
         </div>
      );
   }
}

const mapDispatchToProps = (dispatch: any) => {
   return {
      successfullyLogInUser: (u: IUser) => dispatch(successfullyLogInUser(u)),
   };
};

export default connect(null, mapDispatchToProps)(withRouter(SignUp));