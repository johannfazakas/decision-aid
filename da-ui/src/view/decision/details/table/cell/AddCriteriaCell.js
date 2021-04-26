import React from "react"
import PropTypes from "prop-types"

const AddCriteriaCell = props =>
  <th className="text-center">
    <div
      className={props.decisionStatus === "design" ? "btn btn-dark" : "btn btn-outline-dark"}
      onClick={props.onSubmit}
    >
      New criteria
    </div>
  </th>

AddCriteriaCell.propTypes = {
  decisionStatus: PropTypes.string.isRequired,
  onSubmit: PropTypes.func.isRequired
}

export default AddCriteriaCell
