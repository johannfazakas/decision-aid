import React from "react";
import { Route, Switch } from 'react-router-dom';

import Header from "./common/Header";
import HomePage from "./HomePage";
import AboutPage from "./AboutPage";
import DecisionDetailsPage from "./decision/DecisionDetailsPage";
import NotFoundPage from "./NotFoundPage";
import AddDecisionPage from "./decision/AddDecisionPage";
import DecisionsPage from "./decision/DecisionsPage";
import UpdateDecisionPage from "./decision/UpdateDecisionPage";
import AddCriteriaPage from "./criteria/AddCriteriaPage";
import AddAlternativePage from "./alternative/AddAlternativePage";
import UpdateAlternativePage from "./alternative/UpdateAlternativePage";
import UpdateCriteriaPage from "./criteria/UpdateCriteriaPage";

const App = () => {
  return (
    <div className="container-fluid">
      <Header />
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/decision/:decisionId/criteria/:criteriaId" component={UpdateCriteriaPage} />
        <Route path="/decision/:decisionId/criteria" component={AddCriteriaPage} />
        <Route path="/decision/:decisionId/alternative/:alternativeId" component={UpdateAlternativePage} />
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
