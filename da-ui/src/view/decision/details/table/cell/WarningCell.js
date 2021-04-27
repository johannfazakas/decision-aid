import React from "react"
import PropTypes from "prop-types"

const WarningCell = props =>
  <td>
    {props.warning && props.warning !== "" &&
    <p className="text-danger text-center">{props.warning}</p>
    }
  </td>

WarningCell.propTypes = {
  warning: PropTypes.string
}

export default WarningCell