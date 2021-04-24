import React from "react";
import { Route, Switch } from 'react-router-dom';

import Header from "./common/Header";
import HomePage from "./home/HomePage";
import AboutPage from "./about/AboutPage";
import NotFoundPage from "./error/NotFoundPage";
import AddAlternativePage from "./decision/alternative/AddAlternativePage";
import UpdateAlternativePage from "./decision/alternative/UpdateAlternativePage";
import DecisionDetailsPage from "./decision/details/DecisionDetailsPage";
import DecisionListPage from "./decision/list/DecisionListPage";
import DecisionPage from "./decision/manage/DecisionPage";
import CriteriaPage from "./decision/criteria/CriteriaPage";

const App = () => {
  return (
    <div className="container-fluid">
      <Header />
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/decision/:decisionId/criteria/:criteriaId" component={CriteriaPage} />
        <Route path="/decision/:decisionId/criteria" component={CriteriaPage} />
        <Route path="/decision/:decisionId/alternative/:alternativeId" component={UpdateAlternativePage} />
        <Route path="/decision/:decisionId/alternative" component={AddAlternativePage} />
        <Route path="/decision/:decisionId/details" component={DecisionDetailsPage} />
        <Route path="/decisions" component={DecisionListPage} />
        <Route path="/decision/:decisionId" component={DecisionPage} />
        <Route path="/decision" component={DecisionPage} />
        <Route path="/about" component={AboutPage} />
        <Route component={NotFoundPage} />
      </Switch>
    </div>
  );
};

export default App;
