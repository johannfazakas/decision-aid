import React from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";

const CriteriaCell = props =>
  <th>
    <div className="row">
      <div className="col text-warning text-right">
        {props.criteria.weight + "%"}
      </div>
      {props.criteria.type === "maximum" && <div className="col text-danger text-left">Max +</div>}
      {props.criteria.type === "minimum" && <div className="col text-success text-left">Min -</div>}
    </div>
    <div className="col text-center">
      <Link to={"/decision/" + props.decisionId + "/criteria/" + props.criteria.id}>
        {props.criteria.name + " "}
      </Link>
    </div>
  </th>

CriteriaCell.propTypes = {
  decisionId: PropTypes.string.isRequired,
  criteria: PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    weight: PropTypes.number.isRequired,
    type: PropTypes.string.isRequired
  }).isRequired
}

export default CriteriaCell;