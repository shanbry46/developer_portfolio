## Local AccuTreat Deployment

1.) Download this project directory.
2.) Navigate to the directory from CMD.
3.) Run `docker build --tag accutreat-app:dev .`
4.) After the build completes, run `docker run -ti -e PORT=3000 accutreat-app:dev`
5.) Open `http://localhost:3000` in your browser


