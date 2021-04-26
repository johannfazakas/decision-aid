import React from "react"
import PropTypes from "prop-types"

const AddAlternativeCell = props =>
  <td className="text-center">
    <div
      className={props.readOnly ? "btn btn-outline-dark" : "btn btn-dark"}
      onClick={props.onSubmit}
    >
      New alternative
    </div>
  </td>

AddAlternativeCell.propTypes = {
  readOnly: PropTypes.bool.isRequired,
  onSubmit: PropTypes.func.isRequired,
}

export default AddAlternativeCell;
