# Overview

This application is targeted to practioners to compare two patient profiles focusing on their history. This history is represented via a patient timeline of Encounters and Medication Requests, as well as a timeline of Blood Pressure observations. This is a standalone SMART App with a preconfigured FHIR client to kick off an Oauth 2 authorization flow. To use the application enter a patient ID in one or both patient panels presented on the application and click Get Patient.

# To Run


1. Go to: `https://sbryant46-miniproject3.web.app/`
1. Click Approve
1. Enter a patient ID, or multiple patient IDs such as: `494743a2-fea5-4827-8f02-c2b91e4a4c9e`, `326b4675-0bc8-4dbd-b406-a5564c282401`, and/or `d64b37f5-d3b5-4c25-abe8-23ebe8f5a04e` in each panel
1. Click "Get Patient"
1. View and compare patient information clicking the Timeline or Observation Tabs

# To build and deploy

1. Clone this repository
1. Run `npm i` to install necessary node modules
1. Run `npm run build` to build the react app, compiles to the `build` folder
1. Run `npm run deploy` to deploy the application, in this case to firebase.
    * To setup a firebase deployment, follow the firebase documentation: https://firebase.google.com/docs/hosting/quickstart

## To run locally

1. Run `npm run start`
1. This should automatically launch the application at `localhost:3000`

# Built with

* react.js
* highcharts for the timeline
* chart.js
* React bootstrap
* FHIR Client: http://docs.smarthealthit.org/client-js/
* App Routing, FHIR Client Context, and initial client calls implemented referencing the example provided here: https://codesandbox.io/s/fhir-client-react-react-router-context-0q3n8
