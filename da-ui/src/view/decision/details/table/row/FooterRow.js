import React from "react"
import PropTypes from "prop-types"

import AddAlternativeCell from "../cell/AddAlternativeCell";
import DeleteCriteriaCell from "../cell/DeleteCriteriaCell";
import WarningCell from "../cell/WarningCell";

const FooterRow = props =>
  <tr>
    <AddAlternativeCell
      onSubmit={props.onAddAlternative}
    />
    {
      props.criteria
        .map(criteria =>
          <DeleteCriteriaCell
            key={criteria.id}
            onDeleteCriteria={() => props.onDeleteCriteria(criteria.id)}
          />
        )
    }
    <WarningCell warning={props.warning} />
  </tr>

FooterRow.propTypes = {
  criteria: PropTypes.array.isRequired,
  warning: PropTypes.string.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired,
  onAid: PropTypes.func.isRequired,
  onReset: PropTypes.func.isRequired
}

export default FooterRow
