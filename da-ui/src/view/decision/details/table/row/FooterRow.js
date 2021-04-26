import React from "react"
import PropTypes from "prop-types"

import AidCell from "../cell/AidCell";
import AddAlternativeCell from "../cell/AddAlternativeCell";
import DeleteCriteriaCell from "../cell/DeleteCriteriaCell";

const FooterRow = props =>
  <tr>
    <AddAlternativeCell
      readOnly={props.readOnly}
      onSubmit={props.onAddAlternative}
    />
    {
      props.criteria
        .map(criteria =>
          <DeleteCriteriaCell
            key={criteria.id}
            readOnly={props.readOnly}
            onDeleteCriteria={() => props.onDeleteCriteria(criteria.id)}
          />
        )
    }
    <AidCell
      readOnly={props.readOnly}
      onAid={props.onAid}
      onReset={props.onReset}
    />
  </tr>

FooterRow.propTypes = {
  criteria: PropTypes.array.isRequired,
  readOnly: PropTypes.bool.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired,
  onAid: PropTypes.func.isRequired,
  onReset: PropTypes.func.isRequired
}

export default FooterRow
