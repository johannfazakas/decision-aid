import React from "react";
import PropTypes from "prop-types";

import PropertyCell from "../cell/PropertyCell";
import AlternativeCell from "../cell/AlternativeCell";
import DeleteAlternativeCell from "../cell/DeleteAlternativeCell";

const AlternativeRow = props =>
  <tr>
    <AlternativeCell
      readOnly={props.readOnly}
      alternative={props.alternative}
      onUpdateAlternative={() => props.onUpdateAlternative(props.alternative.id)}
    />
    {
      props.decision.criteria
        .map(criteria => [
          criteria,
          props.decision.properties
            .find(p => p.criteriaId === criteria.id && p.alternativeId === props.alternative.id) || {}
        ])
        .map(([criteria, property]) =>
          <PropertyCell
            key={criteria.id}
            property={property}
            unitOfMeasure={criteria.unitOfMeasure}
          />
        )
    }
    <DeleteAlternativeCell
      readOnly={props.readOnly}
      onDeleteAlternative={() => props.onDeleteAlternative(props.alternative.id)}
    />
  </tr>

AlternativeRow.propTypes = {
  decision: PropTypes.object.isRequired,
  readOnly: PropTypes.bool.isRequired,
  alternative: PropTypes.object.isRequired,
  onDeleteAlternative: PropTypes.func.isRequired,
  onUpdateAlternative: PropTypes.func.isRequired
}

export default AlternativeRow
