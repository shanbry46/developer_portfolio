import React from 'react';
import './UserAccount.css';
import { connect } from "react-redux";
import profilePic from './ProfilePic.png';
import { IApplicationState } from "../../../Store";
import { IUser } from "../../../Models/IUser";
import {  withRouter, RouteComponentProps } from 'react-router-dom';

interface IProps extends RouteComponentProps {
    userInfo: IUser | undefined;
  }

class UserAccount extends React.Component<IProps> {
  public constructor(props: IProps) {
    super(props);
  }

  public render() {
    return (
        <div className='wrapper'>
            <div className='form-wrapper' id='account-fw'>
                <img src={profilePic} className='profile-pic' alt='basic pic'></img>
                <br></br>
                <p>{this.props.userInfo?.userName}</p>
                <p>{this.props.userInfo?.email}</p>
                <p>{this.props.userInfo?.zip}</p>
                <br></br>
            </div>
      </div>
    );
  }
}

const mapStateToProps = (store: IApplicationState) => {
    return {
      userInfo: store.user.userInfo,
    };
  };

export default connect(mapStateToProps)(withRouter(UserAccount));