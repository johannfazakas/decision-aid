import React from "react"
import PropTypes from "prop-types";

const TableActionButton = props => {
  return (
    <div className="text-center">
      {props.status === "define" &&
      <div
        onClick={props.onAid}
        className={props.aidWarnings && props.aidWarnings.length > 0
          ? "btn btn-outline-warning"
          : "btn btn-warning"}
      >
        Aid
      </div>
      }
      {props.status === "aid" &&
      <div onClick={props.onDefine} className="btn btn-success">Define</div>
      }
    </div>
  )
}

TableActionButton.propTypes = {
  status: PropTypes.string.isRequired,
  aidWarnings: PropTypes.array,
  onAid: PropTypes.func.isRequired,
  onDefine: PropTypes.func.isRequired
}

export default TableActionButton
