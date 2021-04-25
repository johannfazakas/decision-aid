import React, { useEffect, useState } from "react"
import * as PropTypes from "prop-types";

const MainTableHeader = props => {
  const [totalWeight, setTotalWeight] = useState(0)

  useEffect(() => {
    setTotalWeight(props.criteria
      .map(criteria => criteria.weight || 0)
      .reduce((sum, it) => sum + it, 0))
  }, [props.criteria])
  return <>
    <div className="text-right">
      Criteria (
      <span className={totalWeight < 100 ? "text-success" : totalWeight > 100 ? "text-danger" : "text-warning"}>
        {totalWeight + "%"}
      </span>
      ) →
    </div>
    <div className="text-right">
      Alternatives ↓
    </div>
  </>;
}

MainTableHeader.propTypes = {
  criteria: PropTypes.array.isRequired
}

export default MainTableHeader