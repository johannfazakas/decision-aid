import React from "react";
import PropTypes from "prop-types";

const PropertyCell = props =>
  <th>
    <div className="text-center font-weight-bold">
      <span>{props.property.value || "-"}</span>
      {props.unitOfMeasure && props.property.value &&
      <span className="text-secondary">{" " + props.unitOfMeasure}</span>
      }
    </div>
  </th>


PropertyCell.propTypes = {
  property: PropTypes.shape({
    value: PropTypes.number
  }).isRequired,
  unitOfMeasure: PropTypes.string
}

export default PropertyCell;
