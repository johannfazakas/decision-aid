import React from "react"
import PropTypes from "prop-types"

const DeleteAlternativeCell = props =>
  <td className="text-center">
    <div
      className="btn btn-outline-danger"
      onClick={props.onDeleteAlternative}
    >
      ← Delete alternative
    </div>
  </td>

DeleteAlternativeCell.propTypes = {
  onDeleteAlternative: PropTypes.func.isRequired
}

export default DeleteAlternativeCell
