import React, { useEffect, useState } from "react";
import * as PropTypes from "prop-types";
import { connect } from "react-redux";

import { bindActionCreators } from "redux";
import { defaultRegisterUser } from "../../store/default";
import { registerUser } from "../../action/userActions";

import RegisterForm from "./RegisterForm";

const RegisterPage = props => {
  const [user, setUser] = useState(defaultRegisterUser)
  const [errors, setErrors] = useState({});

  useEffect(() => {
    setUser(defaultRegisterUser)
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
    if (!user.confirmPassword) _errors.confirmPassword = "Password is required";
    if (user.confirmPassword.length < 8) _errors.confirmPassword = "Password should have at least 8 characters";
    setErrors(_errors);
    return Object.keys(_errors).length === 0;
  }

  const handleSubmit = event => {
    event.preventDefault()
    if (!validateForm()) return;
    props.registerUser(user)
      .then(() => props.history.push("/"))
      .catch(error => alert("Invalid input. " + error))
  }

  return (
    <div className="jumbotron">
      <h2>Register</h2>
      <RegisterForm
        user={user}
        errors={errors}
        onChange={handleChange}
        onSubmit={handleSubmit}
      />
    </div>
  );
}

RegisterPage.propTypes = {
  registerUser: PropTypes.func.isRequired,
  history: PropTypes.object.isRequired
}

const mapStateToProps = () => ({})

const mapDispatchToProps = dispatch => ({
  registerUser: bindActionCreators(registerUser, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(RegisterPage);
