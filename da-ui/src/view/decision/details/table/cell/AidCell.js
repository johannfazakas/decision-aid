import React from "react"
import PropTypes from "prop-types";

const AidCell = props =>
  <td>
    <div className="text-center">
      {
        props.readOnly ||
        <div onClick={props.onAid} className="btn btn-warning">Aid</div>
      }
      {
        props.readOnly &&
        <div onClick={props.onReset} className="btn btn-success">Reset</div>
      }
    </div>
  </td>


AidCell.propTypes = {
  readOnly: PropTypes.bool.isRequired,
  onAid: PropTypes.func.isRequired,
  onReset: PropTypes.func.isRequired
}

export default AidCell
