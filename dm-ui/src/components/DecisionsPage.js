import React from "react";
import { getDecisions } from "../api/decisionsApi";

class DecisionsPage extends React.Component {
  state = {
    decisions: []
  };

  componentDidMount() {
    getDecisions().then(response => this.setState({decisions: response.items}))
  }

  render() {
    return (
      <div className="jumbotron">
        <h1>Decisions Page</h1>
        <table className="table">
          <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
          </tr>
          </thead>
          <tbody>
          {this.state.decisions.map(decision => {
            return <tr key={decision.id}>
              <td>{decision.id}</td>
              <td>{decision.name}</td>
            </tr>
          })}
          </tbody>
        </table>
      </div>
    );
  }
}

export default DecisionsPage;
