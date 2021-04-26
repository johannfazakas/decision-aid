import React from "react"
import * as PropTypes from "prop-types";

import MainHeaderCell from "../cell/MainHeaderCell";
import CriteriaCell from "../cell/CriteriaCell";
import AddCriteriaCell from "../cell/AddCriteriaCell";

const HeaderRow = props =>
  <tr>
    <MainHeaderCell criteria={props.criteria} />
    {props.criteria.map(criteria =>
      <CriteriaCell
        key={criteria.id}
        decisionId={props.decisionId}
        criteria={criteria} />
    )}
    <AddCriteriaCell
      decisionStatus={props.decisionStatus}
      onSubmit={props.onAddCriteria}
    />
  </tr>

HeaderRow.propTypes = {
  decisionId: PropTypes.string.isRequired,
  decisionStatus: PropTypes.string.isRequired,
  criteria: PropTypes.array.isRequired,
  onAddCriteria: PropTypes.func.isRequired
}

export default HeaderRow