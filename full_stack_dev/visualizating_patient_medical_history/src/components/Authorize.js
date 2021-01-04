import { Component } from 'react';
import { oauth2 as FHIR } from "fhirclient"

export default class Authorize extends Component {

    componentDidMount() {
        FHIR.authorize({
            client_id: "18c189af-2069-4c29-9ce5-0c4248e831ed",
            scope:
                "launch launch/patient patient/read offline_access openid fhirUser",
            redirectUri: "./app",
            iss: "https://launch.smarthealthit.org/v/r4/sim/eyJoIjoiMSIsImIiOiIzMjZiNDY3NS0wYmM4LTRkYmQtYjQwNi1hNTU2NGMyODI0MDEiLCJpIjoiMSIsImUiOiI2NzcxYzllNC01YTdkLTRkMjAtYjFjMC04OGIyMTlmMDY2MWIifQ/fhir",
            completeInTarget: true
        }).catch(e => console.error(e))
    }

    render() {
        return "Launching...";
    }
}
