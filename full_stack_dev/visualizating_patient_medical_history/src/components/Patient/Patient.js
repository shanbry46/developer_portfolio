import React from 'react';

function PatientBanner(patient) {
    return (
        <div>
            <PatientName name={patient.name} />
            <br></br>
            <span>
                Gender: <b>{patient.gender}</b>,{" "}
            </span>
            <span>
                DOB: <b>{patient.birthDate}</b>
            </span>
        </div>
    );
}

function PatientName({ name = [] }) {
    let entry =
        name.find(nameRecord => nameRecord.use === "official") || name[0];
    if (!entry) {
        return <h1>No Name</h1>;
    }
    return <h1>{entry.given.join(" ") + " " + entry.family}</h1>;
}

export default class Patient extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: true,
            patient: null,
            error: null
        };
    }

    render() {
        const {patient} = this.props;
        return <PatientBanner {...patient} />;
    }
}
