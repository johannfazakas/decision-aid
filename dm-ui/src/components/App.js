import React from "react";
import { Route, Switch } from 'react-router-dom';

import Header from "./common/Header";
import DecisionsPage from "./DecisionsPage";
import HomePage from "./HomePage";
import AboutPage from "./AboutPage";
import DecisionPage from "./DecisionPage";
import NotFoundPage from "./NotFoundPage";

const App = () => {
  return (
    <div className="container-fluid">
      <Header/>
      <Switch>
        <Route path="/" exact component={HomePage} />
        <Route path="/decisions/:decisionId" component={DecisionPage} />
        <Route path="/decisions" component={DecisionsPage} />
        <Route path="/about" component={AboutPage} />
        <Route component={NotFoundPage} />
      </Switch>
    </div>
  );
}

export default App;
