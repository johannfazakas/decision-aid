import React from "react"
import PropTypes from "prop-types"

const AddCriteriaCell = props =>
  <th className="text-center">
    <div
      className="btn btn-dark"
      onClick={props.onSubmit}
    >
      New criteria
    </div>
  </th>

AddCriteriaCell.propTypes = {
  onSubmit: PropTypes.func.isRequired
}

export default AddCriteriaCell
