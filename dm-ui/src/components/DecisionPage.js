import React from "react";

const DecisionPage = (props) => {
  return (
    <div className="jumbotron">
      <h1>Decision Page</h1>
      <p1>To be implemented. Decision id: {props.match.params.decisionId}</p1>
    </div>
  );
}

export default DecisionPage;