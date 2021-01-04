import { Component } from "react";
import React from "react";
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';
import timeLine from 'highcharts/modules/timeline';
import { FHIRContext } from "../FHIRContext";

export default class Timeline extends Component {

    static contextType = FHIRContext;
    internalChart;

    constructor(props) {
        super(props)
        this.state = {}
    };

    options = {
        chart: {
            zoomType: 'x',
            type: 'timeline',
            inverted: false
        },
        xAxis: {
            type: 'datetime',
            visible: true
        },
        yAxis: {
            gridLineWidth: 1,
            title: null,
            labels: {
                enabled: false
            }
        },
        legend: {
            enabled: false
        },
        title: {
            text: 'Patient Encounter Timeline'
        },
        tooltip: {
            style: {
                width: 300
            }
        },
        series: [{
            dataLabels: {
                allowOverlap: false,
                format: '<span style="color:{point.color}">● </span><span style="font-weight: bold;" > ' +
                    '{point.x:%d %m %Y}</span><br/>{point.label}'
            },
            marker: {
                symbol: 'circle'
            },
            data: [
            ]
        }]
    };

    loadMedications() {
        const data = []
        this.props.medicationRequests.forEach(medicationRequest => {
            const entry = {
                x: Date.parse(medicationRequest.authoredOn),
                name: medicationRequest.medicationCodeableConcept.text,
                label: medicationRequest.medicationCodeableConcept.text
            };
            data.push(entry)
        });
        this.internalChart.addSeries(
            {
                dataLabels: {
                    allowOverlap: false,
                    format: '<span style="color:{point.color}">● </span><span style="font-weight: bold;" > ' +
                        '{point.x:%b %d %Y}</span><br/>{point.label}',
                    backgroundColor: '#009DDC',
                    color: 'white',

                },
                marker: {
                    symbol: 'circle'
                },
                data: data,
                showInLegend: true,
                name: "Medications"
            }
        )
    }

    loadEncounters() {
        const data = []
        this.props.encounters.forEach(encounter => {
            const entry = {
                x: Date.parse(encounter.period.start),
                name: encounter.type[0].text,
                label: encounter.type[0].text
            };

            if ("reasonCode" in encounter) {
                entry.label += `: ${encounter.reasonCode[0].coding[0].display}`
                entry.name += `: ${encounter.reasonCode[0].coding[0].display}`
            }

            data.push(entry)
        });
        this.internalChart.addSeries(
            {
                dataLabels: {
                    allowOverlap: false,
                    format: '<span style="color:{point.color}">● </span><span style="fontweight: bold;" > ' +
                        '{point.x:%b %d %Y}</span><br/>{point.label}'
                },
                marker: {
                    symbol: 'circle'
                },
                data: data,
                name: "Encounters",
                showInLegend: true
            })
    }



    loadData() {
        if (this.internalChart) {
            if (this.props.encounters) {
                this.loadEncounters();
            } if (this.props.medicationRequests) {
                this.loadMedications();
            }
        }
    }

    componentDidUpdate() {
        this.loadData();
    }

    shouldComponentUpdate() {
        return true;
    }

    afterChartCreated = (chart) => {
        this.internalChart = chart;
    }

    render() {
        timeLine(Highcharts);
        return <div>
            <HighchartsReact
                highcharts={Highcharts}
                options={this.options}
                callback={this.afterChartCreated}
            />
        </div>;
    }
}