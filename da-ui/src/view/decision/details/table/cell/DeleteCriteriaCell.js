import React from "react"
import PropTypes from "prop-types"

const DeleteCriteriaCell = props =>
  <td className="text-center">
    <div
      className="btn btn-outline-danger"
      onClick={props.onDeleteCriteria}
    >
      ↑ Delete criteria
    </div>
  </td>

DeleteCriteriaCell.propTypes = {
  onDeleteCriteria: PropTypes.func.isRequired
}

export default DeleteCriteriaCell
