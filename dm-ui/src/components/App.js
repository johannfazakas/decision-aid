import React from "react";
import { Route, Switch } from 'react-router-dom';

import Header from "./common/Header";
import HomePage from "./HomePage";
import AboutPage from "./AboutPage";
import DecisionDetailsPage from "./DecisionDetailsPage";
import NotFoundPage from "./NotFoundPage";
import AddDecisionPage from "./AddDecisionPage";
import DecisionsPage from "./DecisionsPage";
import UpdateDecisionPage from "./UpdateDecisionPage";
import AddCriteriaPage from "./AddCriteriaPage";
import AddAlternativePage from "./AddAlternativePage";

const App = () => {
  return (
    <div className="container-fluid">
      <Header />
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/decision/:decisionId/criteria" component={AddCriteriaPage} />
        <Route path="/decision/:decisionId/alternative" component={AddAlternativePage} />
        <Route path="/decision/:decisionId/details" component={DecisionDetailsPage} />
        <Route path="/decision/:decisionId" component={UpdateDecisionPage} />
        <Route path="/decisions" component={DecisionsPage} />
        <Route path="/decision" component={AddDecisionPage} />
        <Route path="/about" component={AboutPage} />
        <Route component={NotFoundPage} />
      </Switch>
    </div>
  );
};

export default App;
