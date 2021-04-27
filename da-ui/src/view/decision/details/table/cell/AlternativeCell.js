import React from "react"
import PropTypes from "prop-types"
import { Link } from "react-router-dom";

const AlternativeCell = props =>
  <th className="text-center">
    <div className="row text-center">
      <div className="col text-danger">
        {props.alternative.rank === 1 && "I"}
        {props.alternative.rank === 2 && "II"}
        {props.alternative.rank === 3 && "III"}
      </div>
      <div className="col font-weight-bold">
        <Link to="#" onClick={props.onUpdateAlternative}>{props.alternative.name}</Link>
      </div>
      <div className="col text-success">
        {props.alternative.utility && props.alternative.utility.toFixed(2)}
      </div>
    </div>

  </th>

AlternativeCell.propTypes = {
  alternative: PropTypes.shape({
    name: PropTypes.name,
    rank: PropTypes.rank,
    utility: PropTypes.number
  }).isRequired,
  onUpdateAlternative: PropTypes.func.isRequired
}

export default AlternativeCell
