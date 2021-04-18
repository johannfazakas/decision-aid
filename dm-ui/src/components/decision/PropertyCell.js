import React, { useState } from "react";
import PropTypes from "prop-types";

const PropertyCell = props => {
  return (
    <>
      {props.property.value || ""}
    </>
  );
};

PropertyCell.propTypes = {
  property: PropTypes.shape({
    value: PropTypes.number
  }).isRequired
};

export default PropertyCell;
