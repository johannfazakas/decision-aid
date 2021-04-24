import React from "react";
import { Route, Switch } from 'react-router-dom';

import Header from "./common/Header";
import HomePage from "./home/HomePage";
import AboutPage from "./about/AboutPage";
import DecisionDetailsPage from "./decision/details/DecisionDetailsPage";
import NotFoundPage from "./error/NotFoundPage";
import DecisionListPage from "./decision/list/DecisionListPage";
import AddCriteriaPage from "./decision/criteria/AddCriteriaPage";
import AddAlternativePage from "./decision/alternative/AddAlternativePage";
import UpdateAlternativePage from "./decision/alternative/UpdateAlternativePage";
import UpdateCriteriaPage from "./decision/criteria/UpdateCriteriaPage";
import ManageDecisionPage from "./decision/manage/DecisionPage";

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
        <Route path="/decisions" component={DecisionListPage} />
        <Route path="/decision/:decisionId" component={ManageDecisionPage} />
        <Route path="/decision" component={ManageDecisionPage} />
        <Route path="/about" component={AboutPage} />
        <Route component={NotFoundPage} />
      </Switch>
    </div>
  );
};

export default App;
