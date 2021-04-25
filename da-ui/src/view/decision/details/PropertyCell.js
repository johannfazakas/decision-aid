import React from "react";
import PropTypes from "prop-types";

const PropertyCell = props => {
  return (
    <div className="text-center font-weight-bold">
      {props.property.value || "-"}
    </div>
  );
};

PropertyCell.propTypes = {
  property: PropTypes.shape({
    value: PropTypes.number
  }).isRequired
};

export default PropertyCell;
