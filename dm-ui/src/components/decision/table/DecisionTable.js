import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

import PropertyCell from "./PropertyCell";
import CriteriaCell from "./CriteriaCell";

const DecisionTable = props => {
  return (
    <table className="table">
      <thead>
      <tr>
        <th>
          ↓ Alternatives | Criteria →
        </th>
        {props.decision.criteria.map(criteria => {
          return <th key={criteria.id}>
            <CriteriaCell
              decisionId={props.decision.id}
              criteria={criteria}
            />
          </th>
        })}
        <th>
          <div
            className="btn btn-dark"
            onClick={props.onAddCriteria}
          >
            New criteria
          </div>
        </th>
      </tr>
      </thead>
      <tbody>
      {props.decision.alternatives.map(alternative => {
        return <tr key={alternative.id}>
          <th>
            <Link to={"/decision/" + props.decision.id + "/alternative/" + alternative.id}>
              {alternative.name}
            </Link>
          </th>
          {props.decision.criteria
            .map(criteria => [
              criteria,
              props.decision.properties
                .filter(p => p.criteriaId === criteria.id)
                .filter(p => p.alternativeId === alternative.id)
                [0] || {}
            ])
            .map(([criteria, property]) => {
              return <td key={criteria.id}>
                <PropertyCell
                  property={property}
                  onChange={value => props.onPropertyUpdated(alternative.id, criteria.id, value)}
                />
              </td>
            })}
          <td>
            <div
              className="btn btn-outline-danger"
              onClick={() => props.onDeleteAlternative(alternative.id)}
            >
              ← Delete alternative
            </div>
          </td>
        </tr>
      })}
      <tr>
        <th>
          <div
            className="btn btn-dark"
            onClick={props.onAddAlternative}
          >
            New alternative
          </div>
        </th>
        {[...props.decision.criteria].map(criteria => {
          return <td key={criteria.id}>
            <div
              className="btn btn-outline-danger"
              onClick={() => props.onDeleteCriteria(criteria.id)}
            >
              ↑ Delete criteria
            </div>
          </td>
        })}
        <th>
          <div
            className="btn btn-warning"
          >
            Resolve!
          </div>
        </th>
      </tr>
      </tbody>
    </table>
  );
};

DecisionTable.propTypes = {
  decision: PropTypes.shape({
    id: PropTypes.string.isRequired,
    criteria: PropTypes.arrayOf(PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired
    })).isRequired,
    alternatives: PropTypes.arrayOf(PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired
    })).isRequired,
    properties: PropTypes.arrayOf(PropTypes.shape({
      alternativeId: PropTypes.string.isRequired,
      criteriaId: PropTypes.string.isRequired,
      value: PropTypes.number.isRequired
    })).isRequired
  }).isRequired,
  onAddCriteria: PropTypes.func.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onDeleteAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired,
  onPropertyUpdated: PropTypes.func.isRequired
}

export default DecisionTable;
