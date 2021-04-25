import React from "react"
import PropTypes from "prop-types"

const SelectInput = props => (
  <div className="form-group">
    <label htmlFor={props.name}>{props.label}</label>
    <div className="field">
      <select
        name={props.name}
        value={props.value}
        onChange={props.onChange}
        className="form-control"
      >
        <option value="">{props.defaultOption}</option>
        {props.options.map(option =>
          <option key={option.value} value={option.value}>{option.text}</option>)
        }
      </select>
      {props.error && <div className="alert alert-danger">{props.error}</div>}
    </div>
  </div>
)

SelectInput.propTypes = {
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  value: PropTypes.string,
  defaultOption: PropTypes.string,
  options: PropTypes.arrayOf(PropTypes.shape({
    text: PropTypes.string.isRequired,
    value: PropTypes.string.isRequired
  })).isRequired,
  onChange: PropTypes.func.isRequired
}

export default SelectInput;
