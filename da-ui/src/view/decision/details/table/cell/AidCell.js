import React from "react"
import PropTypes from "prop-types";

const AidCell = props => {
  return (
    <div className="text-center">
      {props.status === "design" &&
      <div
        onClick={props.onAid}
        className={props.aidWarning !== "" ? "btn btn-outline-warning" : "btn btn-warning"}
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

AidCell.propTypes = {
  status: PropTypes.string.isRequired,
  aidWarning: PropTypes.string,
  onAid: PropTypes.func.isRequired,
  onReset: PropTypes.func.isRequired
}

export default AidCell
