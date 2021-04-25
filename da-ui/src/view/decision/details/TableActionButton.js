import React from "react"
import PropTypes from "prop-types";

const TableActionButton = props =>
  <div className={
    props.status === "aid" ? "btn btn-success"
      : props.aidWarnings && props.aidWarnings.length > 0 ? "btn btn-outline-warning" : "btn btn-warning"
  }>{
    props.status === "aid" ? "Define" : "Aid!"
  }</div>

TableActionButton.propTypes = {
  status: PropTypes.string.isRequired,
  aidWarnings: PropTypes.array
}

export default TableActionButton
