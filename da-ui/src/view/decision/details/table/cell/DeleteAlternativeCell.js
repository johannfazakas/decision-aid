import React from "react"
import PropTypes from "prop-types"

const DeleteAlternativeCell = props =>
  <td className="text-center">
    <div
      className={props.readOnly ? "btn btn-outline-danger" : "btn btn-danger"}
      onClick={props.onDeleteAlternative}
    >
      ← Delete alternative
    </div>
  </td>

DeleteAlternativeCell.propTypes = {
  readOnly: PropTypes.bool.isRequired,
  onDeleteAlternative: PropTypes.func.isRequired
}

export default DeleteAlternativeCell
