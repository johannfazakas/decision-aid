import React from "react"
import PropTypes from "prop-types"
import AidCell from "../cell/AidCell";
import AddAlternativeCell from "../cell/AddAlternativeCell";

const FooterRow = props =>
  <tr>
    <AddAlternativeCell
      decisionStatus={props.decisionStatus}
      onSubmit={props.onAddAlternative}
    />
    {[...props.criteria].map(criteria =>
      <td key={criteria.id} className="text-center">
        <div
          className="btn btn-outline-danger"
          onClick={() => props.onDeleteCriteria(criteria.id)}
        >
          ↑ Delete criteria
        </div>
      </td>)}
    <th>
      <AidCell
        status={props.decisionStatus}
        aidWarning={props.aidWarning}
        onAid={props.onAid}
        onReset={props.onReset}
      />
    </th>
  </tr>

FooterRow.propTypes = {
  criteria: PropTypes.array.isRequired,
  decisionStatus: PropTypes.string.isRequired,
  aidWarning: PropTypes.string.isRequired,
  onAddAlternative: PropTypes.func.isRequired,
  onDeleteCriteria: PropTypes.func.isRequired,
  onAid: PropTypes.func.isRequired,
  onReset: PropTypes.func.isRequired
}

export default FooterRow
