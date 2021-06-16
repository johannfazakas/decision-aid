import React from 'react';
import { NavLink } from "react-router-dom";

const Header = () => {
  const activeStyle = {color: "orange"}
  return (
    <nav>
      <NavLink activeStyle={activeStyle} exact to="/">Home</NavLink>{"   |   "}
      <NavLink activeStyle={activeStyle} exact to="/decisions">Decisions</NavLink>{"   |   "}
      <NavLink activeStyle={activeStyle} exact to="/about">About</NavLink>

      <NavLink activeStyle={activeStyle} className="float-right" exact to="/logout">Logout</NavLink>
      <pre className="float-right"> | </pre>
      <NavLink activeStyle={activeStyle} className="float-right" exact to="/login">Login</NavLink>
      <pre className="float-right"> | </pre>
      <NavLink activeStyle={activeStyle} className="float-right" exact to="/register">Register</NavLink>
    </nav>
  );
}

export default Header;
