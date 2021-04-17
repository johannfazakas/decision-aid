import React from "react";
import { Link } from "react-router-dom";

const AddCriteriaPage = props => {

  return (
    <div className="jumbotron">
      <h1>Add Criteria</h1>
      <Link
        to={"/decision/" + props.match.params.decisionId + "/details"}
        className="btn btn-primary"
      >
        Save
      </Link>
      <Link
        to={"/decision/" + props.match.params.decisionId + "/details"}
        className="btn btn-light"
      >
        Back
      </Link>
    </div>
  );
};

export default AddCriteriaPage;
