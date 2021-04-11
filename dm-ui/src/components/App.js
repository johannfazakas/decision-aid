import React from "react";
import Header from "./common/Header";
import DecisionsPage from "./DecisionsPage";
import HomePage from "./HomePage";
import AboutPage from "./AboutPage";

function App() {
  function getPage() {
    const route = window.location.pathname;
    if (route === "/decisions") return <DecisionsPage/>;
    if (route === "/about") return <AboutPage/>;
    return <HomePage/>;
  }

  return (
    <div className="container-fluid">
      <Header/>
      {getPage()}
    </div>
  );
}

export default App;
