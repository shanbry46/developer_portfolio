import React from "react";
import ChartJS from "chart.js";
import { Line } from "react-chartjs-2"
import { FHIRContext } from "../FHIRContext";
let chart;

export default class Chart extends React.Component {
    static contextType = FHIRContext;


    shouldComponentUpdate() {
        return true;
    }

    render() {
        //        return <div><canvas id="myChart" width="600" height="400" /></div>;
        if (this.props.bloodPressure) {
            const data = {
                datasets: [
                    {
                        label: "Systolic",
                        data: this.props.bloodPressure.systolic,
                        borderWidth: 2,
                        borderColor: "rgba(200, 0, 127, 1)",
                        fill: false,
                        cubicInterpolationMode: "monotone"
                    },
                    {
                        label: "Diastolic",
                        data: this.props.bloodPressure.diastolic,
                        borderWidth: 2,
                        borderColor: "rgba(0, 127, 255, 1)",
                        fill: false,
                        cubicInterpolationMode: "monotone"
                    }
                ]
            }
            const options = {
                responsive: true,
                scales: {
                    yAxes: [
                        {
                            offset: true,
                            ticks: {
                                beginAtZero: true,
                                min: 0,
                                max: 200,
                                stepSize: 20
                            }
                        }
                    ],
                    xAxes: [
                        {
                            type: "time"
                        }
                    ]
                },
                title: {
                    text: "Blood Pressure",
                    display: true,
                    fontSize: 20
                }
            };
            return (
                <div>
                    <Line data={data} options={options} />
                </div>
            );
        } else {
            return <div />
        }
    }
}
