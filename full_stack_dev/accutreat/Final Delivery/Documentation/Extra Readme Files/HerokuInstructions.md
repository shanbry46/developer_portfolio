
# Heroku Deployment
## How To Guide
### 1) Download the Heroku CLI 
### 2) Navigate to the root AccuTreat folder on your local machine
### 3) Type heroku container:login and type in username: sbryant46@gatech.edu and password: M$Ksp82N!YDyi6
#### Note: Login to the UI to review the build status
### 4) You can navigate to your local branch or master
### 5) Add the heroku remote by typing: git remote add heroku https://git.heroku.com/accutreat-gatech.git
### 6) Once added, push either master or your local branch changes by typing: git push heroku <<branchname>>:master or git push heroku master if you're on master
#### Note: To force a push type in git push -f heroku <<branchname>>:master
### 7) Once deployed, type in heroku open or navigate to https://accutreat-gatech.herokuapp.com/ 
## Resources
### 1) https://devcenter.heroku.com/articles/heroku-cli
### 2) https://devcenter.heroku.com/articles/git
### 3) For any issues, ping Shannon to set up a time to review and resolve


# Heroku Deployment (API Version)
## How To Guide
### 1) Download the Heroku CLI 
### 2) Navigate to the root AccuTreat folder on your local machine
### 3) Type heroku container:login and type in username: sbryant46@gatech.edu and password: M$Ksp82N!YDyi6
#### Note: Login to the UI to review the build status
### 4) You can navigate to your local branch or master
### 5) Add the heroku remote by typing: git remote add heroku https://git.heroku.com/accutreat-api.git
### 6) Once added, push either master or your local branch changes by typing: git push heroku <<branchname>>:master or git push heroku master if you're on master
#### Note: To force a push type in git push -f heroku <<branchname>>:master
### 7) Once deployed, type in heroku open or navigate to https://accutreat-api.herokuapp.com/ 
## Resources
### 1) https://devcenter.heroku.com/articles/heroku-cli
### 2) https://devcenter.heroku.com/articles/git
### 3) For any issues, ping Shannon or Joel to set up a time to review and resolve

* API Endpoint - https://accutreat-api.herokuapp.com/drug/ambien
* APP Endpoint - https://accutreat-gatech.herokuapp.com/