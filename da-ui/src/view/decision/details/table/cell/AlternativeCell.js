import React from "react"
import PropTypes from "prop-types"
import { Link } from "react-router-dom";

const AlternativeCell = props =>
  <th className="text-center">
    {
      props.readOnly ||
      <Link to="#" onClick={props.onUpdateAlternative} className=""><u>{props.alternative.name}</u></Link>
    }
    {
      props.readOnly &&
      <span className="font-weight-bold text-primary">{props.alternative.name}</span>
    }
  </th>

AlternativeCell.propTypes = {
  readOnly: PropTypes.bool.isRequired,
  alternative: PropTypes.object.isRequired,
  onUpdateAlternative: PropTypes.func.isRequired
}

export default AlternativeCell
