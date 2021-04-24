import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

import PropertyCell from "./PropertyCell";
import CriteriaCell from "./CriteriaCell";

const DecisionTable = props =>
  <table className="table">
    <thead>
    <tr>
      <th>↓ Alternatives | Criteria →</th>
      {props.decision.criteria.map(criteria =>
        <th key={criteria.id}>
          <CriteriaCell decisionId={props.decision.id} criteria={criteria} />
        </th>
      )}
      <th>
        <div className="btn btn-dark" onClick={props.onAddCriteria}>New criteria</div>
      </th>
    </tr>
    </thead>
    <tbody>
    {props.decision.alternatives.map(alternative =>
      <tr key={alternative.id}>
        <th>
          <Link to={"/decision/" + props.decision.id + "/alternative/" + alternative.id}>{alternative.name}</Link>
        </th>
        {props.decision.criteria
          .map(criteria => [
            criteria,
            props.decision.properties
              .filter(p => p.criteriaId === criteria.id)
              .filter(p => p.alternativeId === alternative.id)[0] || {}
          ])
          .map(([criteria, property]) =>
            <td key={criteria.id}>
              <PropertyCell property={property} />
            </td>)}
        <td>
          <div
            className="btn btn-outline-danger"
            onClick={() => props.onDeleteAlternative(props.decision.id, alternative.id)}
          >
            ← Delete alternative
          </div>
        </td>
      </tr>)}
    <tr>
      <th>
        <div className="btn btn-dark" onClick={props.onAddAlternative}>New alternative</div>
      </th>
      {[...props.decision.criteria].map(criteria =>
        <td key={criteria.id}>
          <div
            className="btn btn-outline-danger"
            onClick={() => props.onDeleteCriteria(props.decision.id, criteria.id)}
          >
            ↑ Delete criteria
          </div>
        </td>)}
      <th>
        <div className="btn btn-warning">Aid!</div>
      </th>
    </tr>
    </tbody>
  </table>;

DecisionTable.propTypes = {
  decision: PropTypes.object.isRequired,
  onAddCriteria: PropTypes.func.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onDeleteAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired
}

export default DecisionTable;
