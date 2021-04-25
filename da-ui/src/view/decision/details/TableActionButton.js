import React from "react"
import PropTypes from "prop-types";

const TableActionButton = props => {
  return (
    <div className="text-center">
      {props.status === "design" &&
      <div
        onClick={props.onAid}
        className={props.aidWarnings && props.aidWarnings.length > 0
          ? "btn btn-outline-warning"
          : "btn btn-warning"}
      >
        Aid
      </div>
      }
      {props.status === "processed" &&
      <div onClick={props.onReset} className="btn btn-success">Reset</div>
      }
    </div>
  )
}

TableActionButton.propTypes = {
  status: PropTypes.string.isRequired,
  aidWarnings: PropTypes.array,
  onAid: PropTypes.func.isRequired,
  onReset: PropTypes.func.isRequired
}

export default TableActionButton
