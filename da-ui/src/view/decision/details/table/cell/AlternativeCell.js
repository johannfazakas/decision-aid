import React from "react"
import PropTypes from "prop-types"
import { Link } from "react-router-dom";

const AlternativeCell = props =>
  <th className="text-center">
    <Link to="#" onClick={props.onUpdateAlternative}>{props.alternative.name}</Link>
  </th>

AlternativeCell.propTypes = {
  alternative: PropTypes.object.isRequired,
  onUpdateAlternative: PropTypes.func.isRequired
}

export default AlternativeCell
