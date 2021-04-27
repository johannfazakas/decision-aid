import React from "react";
import PropTypes from "prop-types";

const PropertyCell = props =>
  <td>
    <div className="row text-center">
      <div className="col text-danger">
        {props.property.rank === 1 && "I"}
        {props.property.rank === 2 && "II"}
        {props.property.rank === 3 && "III"}
      </div>
      <div className="col font-weight-bold">
        <span>{props.property.value || "-"}</span>
        {
          props.unitOfMeasure && props.property.value &&
          <span className="text-secondary">{" " + props.unitOfMeasure}</span>
        }
      </div>
      <div className="col text-success">
        {props.property.utility && props.property.utility.toFixed(2)}
      </div>
    </div>
  </td>


PropertyCell.propTypes = {
  property: PropTypes.shape({
    value: PropTypes.number,
    rank: PropTypes.rank,
    utility: PropTypes.number
  }).isRequired,
  unitOfMeasure: PropTypes.string
}

export default PropertyCell;
