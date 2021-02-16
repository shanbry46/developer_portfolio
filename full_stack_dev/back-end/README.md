# Overview

There are several Java assignments 1-7 listed here that display the use of Java with the FHIR hapi.  

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
* PostgreSQL
