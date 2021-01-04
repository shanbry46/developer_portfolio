import { Component } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

export default class PatientSearch extends Component {
  constructor(props) {
    super(props)
    this.props = props
    this.state = {patientId: ""}
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  render() {
    return (
      <Form onSubmit={this.handleSubmit}>
        <Form.Group controlId="patient.PatientIdInput">
          <Form.Label>Patient ID</Form.Label>
          <Form.Control type="text" placeholder="39234650-0229-4aee-975b-c8ee68bab40b" onChange={this.handleChange} />
          <Button variant="primary" type="submit">
            Get Patient
          </Button>
        </Form.Group>
      </Form>
    )
  }

  handleChange(event) {
    console.log("Change state")
    this.setState({patientId: event.target.value});
  }

  handleSubmit(event) {
    event.preventDefault();
    this.props.setPatientId(this.state.patientId)
  }

  setId() {

  }



}