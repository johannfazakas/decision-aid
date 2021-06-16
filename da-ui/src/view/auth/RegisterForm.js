import React from "react";
import * as PropTypes from "prop-types";
import TextInput from "../common/TextInput";

const RegisterForm = props => {
  return (
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
      <TextInput
        id="confirmPassword"
        name="confirmPassword"
        label="Confirm Password"
        value={props.user.confirmPassword}
        type="password"
        onChange={props.onChange}
        error={props.errors.confirmPassword}
      />
      <input type="submit" value="Register" className="btn btn-warning" />
    </form>
  );
}

RegisterForm.propTypes = {
  user: PropTypes.shape({
    email: PropTypes.string,
    password: PropTypes.string,
    confirmPassword: PropTypes.string
  }),
  errors: PropTypes.shape({
    email: PropTypes.string,
    password: PropTypes.string,
    confirmPassword: PropTypes.string
  }),
  onChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired
}

export default RegisterForm;
