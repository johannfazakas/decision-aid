import React from "react";
import PropTypes from "prop-types";

import AlternativeRow from "./row/AlternativeRow";
import HeaderRow from "./row/HeaderRow";
import FooterRow from "./row/FooterRow";

const DecisionTable = props =>
  <table className="table table-bordered">
    <thead>
    <HeaderRow
      decisionId={props.decision.id}
      decisionStatus={props.decision.status}
      criteria={props.decision.criteria}
      onAddCriteria={props.onAddCriteria}
    />
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
    <FooterRow
      criteria={props.decision.criteria}
      decisionStatus={props.decision.status}
      aidWarning={props.aidWarning}
      onAddAlternative={props.onAddAlternative}
      onDeleteCriteria={props.onDeleteCriteria}
      onAid={props.onAid}
      onReset={props.onReset}
    />
    </tbody>
  </table>

DecisionTable.propTypes = {
  decision: PropTypes.object.isRequired,
  // TODO change warning type. a boolean is enough
  aidWarning: PropTypes.string.isRequired,
  onAddCriteria: PropTypes.func.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onDeleteAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired,
  onAid: PropTypes.func.isRequired,
  onReset: PropTypes.func.isRequired
}

export default DecisionTable;
