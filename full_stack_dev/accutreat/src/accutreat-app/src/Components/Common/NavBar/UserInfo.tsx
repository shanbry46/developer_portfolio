import React from "react";
import Avatar from "react-avatar";
import "./NavBar.css";

interface IProps {
  name: string | undefined;
}

const UserInfo: React.FunctionComponent<IProps> = (props) => {
  return (
    <div className="userinfo-container">
      {props.name ? (
        <span className="username-element">{props.name}</span>
      ) : null}
      <Avatar
        src="https://img.zeit.de/digital/internet/2016-08/this-is-fine/wide__1300x731"
        round="20px"
        alt="AT"
        name="AccuTreat User"
      />
    </div>
  );
};

export default UserInfo;
