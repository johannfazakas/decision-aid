import React from "react"
import PropTypes from "prop-types"

const AddAlternativeCell = props =>
  <th className="text-center">
    <div
      className={props.decisionStatus === "design" ? "btn btn-dark" : "btn btn-outline-dark"}
      onClick={props.onSubmit}
    >
      New alternative
    </div>
  </th>

AddAlternativeCell.propTypes = {
  decisionStatus: PropTypes.string.isRequired,
  onSubmit: PropTypes.func.isRequired,
}

export default AddAlternativeCell;
