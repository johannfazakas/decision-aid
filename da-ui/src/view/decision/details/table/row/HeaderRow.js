import React from "react"
import * as PropTypes from "prop-types";

import MainHeaderCell from "../cell/MainHeaderCell";
import CriteriaCell from "../cell/CriteriaCell";

const HeaderRow = props =>
  <tr>
    <MainHeaderCell criteria={props.criteria} />
    {props.criteria.map(criteria =>
      <CriteriaCell
        key={criteria.id}
        decisionId={props.decisionId}
        criteria={criteria} />
    )}
    <th className="text-center">
      <div className="btn btn-dark" onClick={props.onAddCriteria}>New criteria</div>
    </th>
  </tr>

HeaderRow.propTypes = {
  decisionId: PropTypes.string.isRequired,
  criteria: PropTypes.array.isRequired,
  onAddCriteria: PropTypes.func.isRequired
}

export default HeaderRow