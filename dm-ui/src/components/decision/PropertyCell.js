import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";

const PropertyCell = props => {
  const [isEditMode, setEditMode] = useState(false);
  const [propertyValue, setPropertyValue] = useState(0);

  useEffect(() => {
    setPropertyValue(props.value);
  }, [props.value])

  const handleOnChange = ({target}) => {
    setPropertyValue(target.value);
  }

  const handleConfirm = () => {
    props.onChange(propertyValue)
    setEditMode(false);
  };

  const handleCancel = () => {
    setEditMode(false);
  }

  return (
    <>
      {isEditMode ?
        <div className="input-group-sm">
          <input
            id="value"
            type="number"
            onChange={handleOnChange}
            name="value"
            value={propertyValue}
          />
          <div className="btn btn-dark" onClick={handleCancel}>X</div>
          <div className="btn btn-warning" onClick={handleConfirm}>✓</div>
        </div>
        :
        <div onClick={() => setEditMode(true)}>
          {props.value}
        </div>
      }
    </>
  );
};

PropertyCell.propTypes = {
  value: PropTypes.number.isRequired,
  onChange: PropTypes.func.isRequired
};

export default PropertyCell;
