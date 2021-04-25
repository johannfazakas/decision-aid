import React from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";

import PropertyCell from "./PropertyCell";

const AlternativeRow = props => {
  return (
    <tr>
      <th className="text-right">
        <Link
          to={"/decision/" + props.decision.id + "/alternative/" + props.alternative.id}
        >
          {props.alternative.name}
        </Link>
      </th>
      {props.decision.criteria
        .map(criteria => {
          const property = props.decision.properties
            .find(p => p.criteriaId === criteria.id && p.alternativeId === props.alternative.id) || {}
          return (
            <td key={criteria.id}>
              <PropertyCell property={property} unitOfMeasure={criteria.unitOfMeasure} />
            </td>
          );
        })}
      <td>
        <div className="btn btn-outline-danger" onClick={props.onDelete}>← Delete alternative</div>
      </td>
    </tr>
  )
}

AlternativeRow.propTypes = {
  decision: PropTypes.object.isRequired,
  alternative: PropTypes.object.isRequired,
  onDelete: PropTypes.func.isRequired
}

export default AlternativeRow
