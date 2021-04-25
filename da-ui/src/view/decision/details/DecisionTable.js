import React from "react";
import PropTypes from "prop-types";

import CriteriaTableHeader from "./CriteriaTableHeader";
import AlternativeRow from "./AlternativeRow";
import MainTableHeader from "./MainTableHeader";
import TableActionButton from "./TableActionButton";

const DecisionTable = props =>
  <table className="table table-bordered">
    <thead>
    <tr>
      <th>
        <MainTableHeader criteria={props.decision.criteria} />
      </th>
      {props.decision.criteria.map(criteria =>
        <th key={criteria.id}>
          <CriteriaTableHeader decisionId={props.decision.id} criteria={criteria} />
        </th>
      )}
      <th className="text-center">
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
      <th className="text-center">
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
        <TableActionButton
          status={props.decision.status}
          aidWarnings={props.aidWarnings}
          onAid={props.onAid}
          onDefine={props.onDefine}
        />
      </th>
    </tr>
    </tbody>
  </table>

DecisionTable.propTypes = {
  decision: PropTypes.object.isRequired,
  aidWarnings: PropTypes.array.isRequired,
  onAddCriteria: PropTypes.func.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onDeleteAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired,
  onAid: PropTypes.func.isRequired,
  onDefine: PropTypes.func.isRequired
}

export default DecisionTable;
