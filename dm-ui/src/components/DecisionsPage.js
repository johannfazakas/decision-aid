import React, { useEffect, useState } from "react";
import { getDecisions } from "../api/decisionsApi";
import DecisionList from "./DecisionList";

const DecisionsPage = () => {
  const [decisions, setDecisions] = useState([]);

  useEffect(() => {
    getDecisions().then(response => setDecisions(response.items));
  }, []);

  return (
    <div className="jumbotron">
      <h1>Decisions</h1>
      <DecisionList decisions={decisions}/>
    </div>
  );
}

export default DecisionsPage;
