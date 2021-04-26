import React, { useEffect, useState } from "react"
import * as PropTypes from "prop-types";

const MainHeaderCell = props => {
  const [totalWeight, setTotalWeight] = useState(0)

  useEffect(() => {
    setTotalWeight(props.criteria
      .map(criteria => criteria.weight || 0)
      .reduce((sum, it) => sum + it, 0))
  }, [props.criteria])

  return (
    <th>
      <div className="text-center">
        Criteria (
        <span className={totalWeight < 100 ? "text-success" : totalWeight > 100 ? "text-danger" : "text-warning"}>
        {totalWeight + "%"}
      </span>
        ) →
      </div>
      <div className="text-center">
        Alternatives ↓
      </div>
    </th>
  )
}

MainHeaderCell.propTypes = {
  criteria: PropTypes.array.isRequired
}

export default MainHeaderCell
