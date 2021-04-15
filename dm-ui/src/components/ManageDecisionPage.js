import React from "react";
import { Link } from "react-router-dom";

const ManageDecisionPage = (props) => {
  return (
    <div className="jumbotron">
      <h1>Decision Table Page</h1>
      <p1>To be implemented. Decision id: {props.match.params.decisionId}</p1>
      <br/>
      <Link to={"/decision/" + props.match.params.decisionId } className="btn btn-primary">
        Update decision
      </Link>
    </div>
  );
}

export default ManageDecisionPage;