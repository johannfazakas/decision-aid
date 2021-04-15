import React, { useEffect, useState } from "react";
import { listDecisions } from "../api/decisionsApi";
import DecisionList from "./DecisionList";
import { Link } from "react-router-dom";

const DecisionsPage = () => {
  const [decisions, setDecisions] = useState([]);

  useEffect(() => {
    listDecisions().then(response => setDecisions(response.items));
  }, []);

  return (
    <div className="jumbotron">
      <h1>My decisions</h1>
      <DecisionList decisions={decisions}/>
      <Link to="/decision" className="btn btn-primary">
        Create new
      </Link>
    </div>
  );
}

export default DecisionsPage;
