import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

const DecisionList = (props) =>
  <table className="table">
    <thead>
    <tr>
      <th>Name</th>
      <th>Description</th>
      <th>Created at</th>
    </tr>
    </thead>
    <tbody>
    {props.decisions.map(decision =>
      <tr key={decision.id}>
        <th>
          <Link to={"/decision/" + decision.id + "/details"}>{decision.name}</Link>
        </th>
        <td>{decision.description}</td>
        <td>{decision.createdAt}</td>
        <td>
          <div className="btn btn-danger" onClick={() => props.deleteDecision(decision.id)}>Delete</div>
        </td>
      </tr>
    )}
    </tbody>
  </table>;

DecisionList.propTypes = {
  decisions: PropTypes.arrayOf(PropTypes.shape({
    id: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    createdAt: PropTypes.string
  })).isRequired,
  deleteDecision: PropTypes.func.isRequired
};

export default DecisionList;
