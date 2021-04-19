import React from 'react';
import { NavLink } from "react-router-dom";

const Header = () => {
  const activeStyle = {color: "orange"}
  return (
    <nav>
      <NavLink activeStyle={activeStyle} exact to="/">Home</NavLink>{"  |  "}
      <NavLink activeStyle={activeStyle} exact to="/decisions">Decisions</NavLink>{"  |  "}
      <NavLink activeStyle={activeStyle} exact to="/about">About</NavLink>{"  |  "}
    </nav>
  );
}

export default Header;
