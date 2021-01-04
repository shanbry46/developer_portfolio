#! /bin/bash

# run 'which bash locally and set the above to your location for bash'
# run from root of the Accutreat project

# ECHO welcome
echo Welcome to Accutreat!
# login
heroku login -i
# Email: sbryant46@gatech.edu
# Password: M$Ksp82N!YDyi6

# deploy the app
cd src/accutreat-app
echo Running NPM install
npm install
# if you do not already have the remote added then this will add it
echo Checking out master
git checkout origin/master
echo Setting heroku values
git remote add heroku https://git.heroku.com/accutreat-gatech.git
heroku apps:destroy accutreat-gatech
heroku create accutreat-gatech --buildpack mars/create-react-app
git remote set-url heroku https://git.heroku.com/accutreat-gatech.git
# ensure you have your changes merged into a local master or remote master
# before you deploy
git push heroku origin/master

# deploy the api
echo sending over to the API location
cd ../accutreat-api
echo Running dotnet checks
dotnet clean
dotnet restore
dotnet build
echo changing the heroku remote to the api endpoint
heroku apps:destroy accutreat-api
heroku create accutreat-api
heroku buildpacks:set https://github.com/jincod/dotnetcore-buildpack
git remote set-url heroku https://git.heroku.com/accutreat-api.git
echo pushing the master to heroku on the api
git push heroku origin/master

# logging out
heroku logout
