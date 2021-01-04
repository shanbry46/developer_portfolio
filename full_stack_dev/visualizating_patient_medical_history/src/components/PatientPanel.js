import { Component } from "react";
import PatientSearch from "./PatientSearch";
import PatientContainer from "./PatientContainer/PatientContainer";
import Patient from "./Patient/Patient";
import { FHIRContext } from '../FHIRContext';
import Alert from "react-bootstrap/Alert";


export default class PatientPanel extends Component {
  static contextType = FHIRContext;
  constructor(props) {
    super(props);
    this.props = props;
    this.state = { patientId: "", patient: {}, encounters: null, medicationRequests: null, bloodPressure: null, error: null }
    this.setPatientId = this.setPatientId.bind(this);
  }

  lookUpPatient(firstName, lastName) {

  }

  setPatientId(patientId) {
    console.log(patientId)
    //    this.setState({patientId: patientId})
    this.loadPatient(patientId);
    this.loadEncounters(patientId);
    this.loadMedications(patientId);
    this.loadObservations(patientId);
  }

  loadPatient(patientId) {
    console.log(`Patient ID: ${patientId}`);
    if (patientId) {
      const client = this.context.client;
      this._loader = client.request(`Patient/${patientId}`)
        .then(patient => {
          console.log(patient)
          return this.setState({
            patient: {
              name: patient.name,
              gender: patient.gender,
              birthDate: patient.birthDate
            }, loading: false, error: null
          });
        })
        .catch(error => {
          this.setState({ error });
        });
    }
  }
  loadEncounters(patientId) {
    const client = this.context.client;
    const q = new URLSearchParams();
    q.set("patient", patientId);
    client
      .request(`Encounter?${q}`, {
        pageLimit: 0,
        flat: true
      })
      .then(encounters => {
        this.setState({ encounters })
      }).catch(error => {
        console.error(error)
      });
  }
  loadObservations(patientId) {
    const client = this.context.client;
    const q = new URLSearchParams();
    q.set("code", "http://loinc.org|55284-4");
    q.set("subject", patientId);
    client
      .request(`Observation?${q}`, {
        pageLimit: 0,
        flat: true
      })
      .then(bp => {
        const bpMap = {
          systolic: [],
          diastolic: []
        };
        bp.forEach(o => {
          o.component.forEach(c => {
            const code = client.getPath(c, "code.coding.0.code");
            if (code === "8480-6") {
              bpMap.systolic.push({
                x: new Date(o.effectiveDateTime),
                y: c.valueQuantity.value
              });
            } else if (code === "8462-4") {
              bpMap.diastolic.push({
                x: new Date(o.effectiveDateTime),
                y: c.valueQuantity.value
              });
            }
          });
        });
        bpMap.systolic.sort((a, b) => a.x - b.x);
        bpMap.diastolic.sort((a, b) => a.x - b.x);
        this.setState({ bloodPressure: bpMap })
      });
  }

  loadMedications(patientId) {
    const client = this.context.client;
    const q = new URLSearchParams();
    q.set("patient", patientId);
    client
      .request(`MedicationRequest?${q}`, {
        pageLimit: 0,
        flat: true
      })
      .then(medicationRequests => {
        this.setState({ medicationRequests });
        console.log(`Medication Requests: ${medicationRequests}`);
      }).catch(err => {
        console.error(err);
      });

  }

  render() {
    console.log(`State: ${JSON.stringify(this.state)}`)
    const { patient, encounters, medicationRequests, bloodPressure, error } = this.state;
    if (error) {
      return (
        <div>
          <PatientSearch setPatientId={this.setPatientId} />
          <Alert variant="danger">
            <Alert.Heading>Error Retrieving Patient</Alert.Heading>
            <p>
              Please try a different ID.
            </p>
            <hr />
            <p className="mb-0">
            </p>
          </Alert>
        </div>
      )

    }
    return (
      <div>
        <PatientSearch setPatientId={this.setPatientId} />
        <Patient patient={patient} />
        <hr />
        <PatientContainer encounters={encounters} medicationRequests={medicationRequests} bloodPressure={bloodPressure} />
        <br />
      </div>

    )
  }

}