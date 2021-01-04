import React from "react";
import Tabs from 'react-bootstrap/Tabs';
import Tab from 'react-bootstrap/Tab';
import Timeline from "../Timeline";
import Chart from "../Chart";



export default class PatientContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {}
  }
  render() {
    return (
      <Tabs defaultActiveKey="home" id="uncontrolled-tab-example" mountOnEnter={true} unmountOnExit={false}>
        <Tab eventKey="home" title="Timeline">
          <Timeline encounters={this.props.encounters} medicationRequests={this.props.medicationRequests} />
        </Tab>
        <Tab eventKey="observations" title="Observations">
          <Chart bloodPressure = {this.props.bloodPressure} />
        </Tab>
      </Tabs>
    )
  }
}
