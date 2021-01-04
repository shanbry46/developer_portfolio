FROM node:alpine
WORKDIR '/app'

COPY package.json .

RUN npm install --silent

COPY . .

EXPOSE ${PORT}

RUN npm run-script build

CMD ["npm", "start"]