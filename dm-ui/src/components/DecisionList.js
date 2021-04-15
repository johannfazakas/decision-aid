import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const DecisionList = (props) => {
  return (
    <table className="table">
      <thead>
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
      </thead>
      <tbody>
      {props.decisions.map(decision => {
        return <tr key={decision.id}>
          <td><Link to={"/decision/" + decision.id + "/table"}>{decision.name}</Link></td>
          <td>{decision.description}</td>
        </tr>
      })}
      </tbody>
    </table>
  );
}

DecisionList.propTypes = {
  decisions: PropTypes.arrayOf(PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    description: PropTypes.string.description,
  })).isRequired
}

export default DecisionList;
