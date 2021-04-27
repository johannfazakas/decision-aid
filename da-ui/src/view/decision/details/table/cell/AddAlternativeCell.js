import React from "react"
import PropTypes from "prop-types"

const AddAlternativeCell = props =>
  <td className="text-center">
    <div
      className="btn btn-dark"
      onClick={props.onSubmit}
    >
      New alternative
    </div>
  </td>

AddAlternativeCell.propTypes = {
  onSubmit: PropTypes.func.isRequired,
}

export default AddAlternativeCell;
