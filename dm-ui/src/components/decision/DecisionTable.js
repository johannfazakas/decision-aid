import React from "react";
import PropTypes from "prop-types";

const DecisionTable = props => {
  return (
    <table className="table">
      <thead>
      <tr>
        <th>
          ↓ Alternatives | Criteria →
        </th>
        {props.decision.criteria.map(criteria => {
          return <th key={criteria.id}>{criteria.name}</th>
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
          <td>{alternative.name}</td>
          {[...props.decision.criteria.keys()].map(key => {
            return <td key={key}>?</td>
          })}
          <td>
            <div
              className="btn btn-outline-danger"
              onClick={() => props.onDeleteAlternative(alternative.id)}
            >
              Delete alternative
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
              Delete criteria
            </div>
          </td>
        })}
      </tr>
      </tbody>
    </table>
  );
};

DecisionTable.propTypes = {
  decision: PropTypes.shape({
    criteria: PropTypes.arrayOf(PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired
    })).isRequired,
    alternatives: PropTypes.arrayOf(PropTypes.shape({
      id: PropTypes.string.isRequired,
      name: PropTypes.string.isRequired
    })).isRequired
  }).isRequired,
  onAddCriteria: PropTypes.func.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onDeleteAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired
}

export default DecisionTable;
