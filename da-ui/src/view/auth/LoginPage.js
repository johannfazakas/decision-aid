import * as PropTypes from "prop-types";
import React, { useState, useEffect } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

import { loginUser } from "../../action/userActions";
import { defaultLoginUser } from "../../store/default";

import LoginForm from "./LoginForm";

const LoginPage = props => {
  const [user, setUser] = useState(defaultLoginUser)
  const [errors, setErrors] = useState({});

  useEffect(() => {
    setUser(defaultLoginUser)
  }, []);

  const handleChange = event => {
    const {name, value} = event.target
    setUser(it => ({...it, [name]: value}))
  }

  const validateForm = () => {
    const _errors = {};
    if (!user.email) _errors.name = "Email is required";
    if (!user.password) _errors.password = "Password is required";
    if (user.password.length < 8) _errors.password = "Password should have at least 8 characters";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  }

  const handleSubmit = event => {
    event.preventDefault()
    if (!validateForm()) return;
    props.loginUser(user)
      .then(() => props.history.push("/decisions"))
      .catch(error => alert("Invalid credentials. " + error))
  }

  return (
    <div className="jumbotron">
      <h2>Login</h2>
      <LoginForm
        user={user}
        errors={errors}
        onChange={handleChange}
        onSubmit={handleSubmit}
      />
    </div>
  );
}

LoginPage.propTypes = {
  loginUser: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired
}

const mapStateToProps = () => ({})

const mapDispatchToProps = dispatch => ({
  loginUser: bindActionCreators(loginUser, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(LoginPage);
