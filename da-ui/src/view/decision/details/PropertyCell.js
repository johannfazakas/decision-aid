import React from "react";
import PropTypes from "prop-types";

const PropertyCell = props =>
  <div className="text-center font-weight-bold">
    <span>{props.property.value || "-"}</span>
    {props.unitOfMeasure && props.property.value &&
    <span className="text-secondary">{" " + props.unitOfMeasure}</span>
    }
  </div>


PropertyCell.propTypes = {
  property: PropTypes.shape({
    value: PropTypes.number
  }).isRequired,
  unitOfMeasure: PropTypes.string
}

export default PropertyCell;
