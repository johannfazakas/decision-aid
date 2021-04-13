import React from "react";
import PropTypes from "prop-types";
import { Link} from "react-router-dom";

const DecisionList = (props) => {
  return (
    <table className="table">
      <thead>
      <tr>
        <th>Id</th>
        <th>Name</th>
      </tr>
      </thead>
      <tbody>
      {props.decisions.map(decision => {
        return <tr key={decision.id}>
          <td>{decision.id}</td>
          <td><Link to={"/decisions/" + decision.id}>{decision.name}</Link></td>
        </tr>
      })}
      </tbody>
    </table>
  );
}

DecisionList.propTypes = {
  decisions: PropTypes.arrayOf(PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired
  })).isRequired
}

export default DecisionList;
