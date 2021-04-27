import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const CriteriaCell = props =>
  <th>
    <div className="row">
      <div className="col text-warning text-right">
        {props.criteria.weight + "%"}
      </div>
      {
        props.criteria.type === "maximum" &&
        <div className="col text-danger text-left">Max +</div>
      }
      {
        props.criteria.type === "minimum" &&
        <div className="col text-success text-left">Min -</div>
      }
    </div>
    <div className="text-center">
      <Link to="#" onClick={props.onUpdateCriteria}>{props.criteria.name + " "}</Link>
    </div>
  </th>

CriteriaCell.propTypes = {
  criteria: PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    weight: PropTypes.number.isRequired,
    type: PropTypes.string.isRequired
  }).isRequired,
  onUpdateCriteria: PropTypes.func.isRequired,
}

export default CriteriaCell;