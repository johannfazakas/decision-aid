import React from "react"
import PropTypes from "prop-types"

const Warnings = props =>
  <div>
    {props.warning && props.warning !== "" &&
    <div className="text-danger text-center">{props.warning}</div>
    }
  </div>

Warnings.propTypes = {
  warning: PropTypes.string
}

export default Warnings