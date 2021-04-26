import React from "react"
import PropTypes from "prop-types"

const AddCriteriaCell = props =>
  <th className="text-center">
    <div
      className={props.readOnly ? "btn btn-outline-dark" : "btn btn-dark"}
      onClick={props.onSubmit}
    >
      New criteria
    </div>
  </th>

AddCriteriaCell.propTypes = {
  readOnly: PropTypes.bool.isRequired,
  onSubmit: PropTypes.func.isRequired
}

export default AddCriteriaCell
