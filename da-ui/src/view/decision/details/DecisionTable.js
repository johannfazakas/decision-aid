import React from "react";
import PropTypes from "prop-types";

import CriteriaCell from "./CriteriaCell";
import AlternativeRow from "./AlternativeRow";

const DecisionTable = props =>
  <table className="table table-bordered">
    <thead>
    <tr>
      <th className="text-right">↓ Alternatives | Criteria →</th>
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
    {props.decision.alternatives.map(alternative => (
      <AlternativeRow
        key={alternative.id}
        decision={props.decision}
        alternative={alternative}
        onDelete={() => props.onDeleteCriteria(props.decision.id, alternative.id)}
      />
    ))}
    <tr>
      <th className="text-right">
        <div className="btn btn-dark" onClick={props.onAddAlternative}>New alternative</div>
      </th>
      {[...props.decision.criteria].map(criteria =>
        <td key={criteria.id} className="text-center">
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
