import React from "react";
import { Route, Switch } from 'react-router-dom';

import Header from "./common/Header";
import HomePage from "./HomePage";
import AboutPage from "./AboutPage";
import ManageDecisionPage from "./ManageDecisionPage";
import NotFoundPage from "./NotFoundPage";
import CreateDecisionPage from "./CreateDecisionPage";
import DecisionsPage from "./DecisionsPage";
import UpdateDecisionPage from "./UpdateDecisionPage";

const App = () => {
  return (
    <div className="container-fluid">
      <Header />
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/decision/:decisionId/details" component={ManageDecisionPage} />
        <Route path="/decision/:decisionId" component={UpdateDecisionPage} />
        <Route path="/decisions" component={DecisionsPage} />
        <Route path="/decision" component={CreateDecisionPage} />
        <Route path="/about" component={AboutPage} />
        <Route component={NotFoundPage} />
      </Switch>
    </div>
  );
}

export default App;
