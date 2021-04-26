import React from "react"
import PropTypes from "prop-types"

const DeleteCriteriaCell = props =>
  <td className="text-center">
    <div
      className={props.readOnly ? "btn btn-outline-danger" : "btn btn-danger"}
      onClick={props.onDeleteCriteria}
    >
      ↑ Delete criteria
    </div>
  </td>

DeleteCriteriaCell.propTypes = {
  readOnly: PropTypes.bool.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired
}

export default DeleteCriteriaCell
