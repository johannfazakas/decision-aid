import React from 'react';
import PropTypes from "prop-types";
import { NavLink } from "react-router-dom";
import { connect } from "react-redux";

const Header = ({user}) => {
  const activeStyle = {color: "orange"}
  return (
    <nav>
      <NavLink activeStyle={activeStyle} exact to="/">Home</NavLink>
      {user.token !== undefined && "   |   "}
      {user.token !== undefined && <NavLink activeStyle={activeStyle} exact to="/decisions">Decisions</NavLink>}

      {user.token !== undefined && <NavLink activeStyle={activeStyle} className="float-right" exact to="/logout">Logout</NavLink>}
      {user.token !== undefined || <NavLink activeStyle={activeStyle} className="float-right" exact to="/login">Login</NavLink>}
      {user.token !== undefined || <pre className="float-right"> | </pre>}
      {user.token !== undefined || <NavLink activeStyle={activeStyle} className="float-right" exact to="/register">Register</NavLink>}
    </nav>
  );
}

Header.propTypes = {
  user: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  user: state.user
})

const mapDispatchToProps = () => ({})

export default connect(mapStateToProps, mapDispatchToProps)(Header);
