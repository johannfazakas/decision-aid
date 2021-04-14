import React, { useEffect, useState } from "react";
import { getDecisions } from "../api/decisionsApi";
import DecisionList from "./DecisionList";
import { Link } from "react-router-dom";

const DecisionsPage = () => {
  const [decisions, setDecisions] = useState([]);

  useEffect(() => {
    getDecisions().then(response => setDecisions(response.items));
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
