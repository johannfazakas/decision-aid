import React from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";

const CriteriaCell = props => {
  return (
    <div className="row text-center">
      <div className="col">
        <Link to={"/decision/" + props.decisionId + "/criteria/" + props.criteria.id}>
          {props.criteria.name + " "}
        </Link>
      </div>
      <div className="col">
        {props.criteria.weight + "%"}
      </div>
      <div className="col text-warning">
        {props.criteria.type === "maximum" && "Max +"}
        {props.criteria.type === "minimum" && "Min -"}
      </div>
    </div>
  );
};

CriteriaCell.propTypes = {
  decisionId: PropTypes.string.isRequired,
  criteria: PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    weight: PropTypes.number.isRequired,
    type: PropTypes.string.isRequired
  }).isRequired
};

export default CriteriaCell;