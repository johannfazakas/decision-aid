import React from "react";
import { Route, Switch } from 'react-router-dom';

import Header from "./common/Header";
import HomePage from "./home/HomePage";
import NotFoundPage from "./error/NotFoundPage";
import DecisionDetailsPage from "./decision/details/DecisionDetailsPage";
import DecisionListPage from "./decision/list/DecisionListPage";
import DecisionPage from "./decision/manage/DecisionPage";
import CriteriaPage from "./decision/criteria/CriteriaPage";
import AlternativePage from "./decision/alternative/AlternativePage";
import RegisterPage from "./auth/RegisterPage";
import LogoutPage from "./auth/LogoutPage";
import LoginPage from "./auth/LoginPage";

const App = () => {
  return (
    <div className="container-fluid">
      <Header />
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/decision/:decisionId/criteria/:criteriaId" component={CriteriaPage} />
        <Route path="/decision/:decisionId/criteria" component={CriteriaPage} />
        <Route path="/decision/:decisionId/alternative/:alternativeId" component={AlternativePage} />
        <Route path="/decision/:decisionId/alternative" component={AlternativePage} />
        <Route path="/decision/:decisionId/details" component={DecisionDetailsPage} />
        <Route path="/decisions" component={DecisionListPage} />
        <Route path="/decision/:decisionId" component={DecisionPage} />
        <Route path="/decision" component={DecisionPage} />
        <Route path="/register" component={RegisterPage} />
        <Route path="/login" component={LoginPage} />
        <Route path="/logout" component={LogoutPage} />
        <Route component={NotFoundPage} />
      </Switch>
    </div>
  );
};

export default App;
