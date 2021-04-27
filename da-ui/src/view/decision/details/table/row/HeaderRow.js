import React from "react"
import * as PropTypes from "prop-types";

import MainHeaderCell from "../cell/MainHeaderCell";
import CriteriaCell from "../cell/CriteriaCell";
import AddCriteriaCell from "../cell/AddCriteriaCell";

const HeaderRow = props =>
  <tr>
    <MainHeaderCell criteria={props.criteria} />
    {
      props.criteria
        .map(criteria =>
          <CriteriaCell
            key={criteria.id}
            criteria={criteria}
            onUpdateCriteria={() => props.onUpdateCriteria(criteria.id)}
          />
        )
    }
    <AddCriteriaCell
      onSubmit={props.onAddCriteria}
    />
  </tr>

HeaderRow.propTypes = {
  criteria: PropTypes.array.isRequired,
  onAddCriteria: PropTypes.func.isRequired,
  onUpdateCriteria: PropTypes.func.isRequired
}

export default HeaderRow