import React from "react";
import * as PropTypes from "prop-types";
import TextInput from "../common/TextInput"

const LoginForm = props => (
  <form onSubmit={props.onSubmit}>
    <TextInput
      id="email"
      name="email"
      label="Email"
      value={props.user.email}
      onChange={props.onChange}
      error={props.errors.email}
    />
    <TextInput
      id="password"
      name="password"
      label="Password"
      value={props.user.password}
      type="password"
      onChange={props.onChange}
      error={props.errors.password}
    />
    <input type="submit" value="Login" className="btn btn-warning" />
  </form>
)

LoginForm.propTypes = {
  user: PropTypes.shape({
    email: PropTypes.string,
    password: PropTypes.string
  }),
  errors: PropTypes.shape({
    email: PropTypes.string,
    password: PropTypes.string,
    confirmPassword: PropTypes.string
  }),
  onChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired
}

export default LoginForm;
