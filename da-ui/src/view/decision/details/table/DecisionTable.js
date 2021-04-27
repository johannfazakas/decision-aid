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
      criteria={props.decision.criteria}
      onAddCriteria={props.onAddCriteria}
      onUpdateCriteria={props.onUpdateCriteria}
    />
    </thead>
    <tbody>
    {
      props.decision.alternatives
        .map(alternative => (
          <AlternativeRow
            key={alternative.id}
            decision={props.decision}
            alternative={alternative}
            onDeleteAlternative={props.onDeleteAlternative}
            onUpdateAlternative={props.onUpdateAlternative}
          />
        ))
    }
    <FooterRow
      criteria={props.decision.criteria}
      warning={props.decision.aidSummary && props.decision.aidSummary.reason || ""}
      onAddAlternative={props.onAddAlternative}
      onDeleteCriteria={props.onDeleteCriteria}
    />
    </tbody>
  </table>

DecisionTable.propTypes = {
  decision: PropTypes.object.isRequired,
  onAddCriteria: PropTypes.func.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onUpdateCriteria: PropTypes.func.isRequired,
  onUpdateAlternative: PropTypes.func.isRequired,
  onDeleteAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired,
}

export default DecisionTable;
