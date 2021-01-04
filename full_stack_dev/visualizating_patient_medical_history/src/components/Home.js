import FhirClientProvider from "./FhirClientProvider"
import { React, Component } from "react";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import PatientPanel from "./PatientPanel";


export default class Home extends Component {
    constructor(props) {
        super(props);
        this.props = props;
    }

    setPatientId() {

    }

    render() {
        return (
            <FhirClientProvider>
                <Container fluid>
                    <Row>
                        <Col>
                            <PatientPanel />
                        </Col>
                        <Col>
                            <PatientPanel />
                        </Col>
                    </Row>
                    <Row>
                    </Row>
                    <Row>
                        {/* <Col>
                    </Col>
                    <Col>
                        <Genogram height={480} width={480} />
                        <br />
                    </Col> */}

                    </Row>
                </Container>
            </FhirClientProvider>
        )

    }

}