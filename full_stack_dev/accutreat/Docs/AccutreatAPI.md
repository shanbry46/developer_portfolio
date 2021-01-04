
# AccuTreat API

## How to run API in Docker Locally
Make sure docker is installed.

To build:
```
> docker build --tag accutreatapi:dev .
```

To run:

Decide on a port you'd  like to use on the container side, and a port you'd like to use on the local side.
They can be similar or different, doesn't matter.
For this example:
- X - port on the container/docker side.
- Y - port on the localhost side.

```
> docker run -d -p Y:X -e PORT=X -e ASPNETCORE_URLS=X -ti accutreatapi:dev
```

-d: Detached mode

-t: Allocate a pseudo-TTY

-i: Keep STDIN open even if not attached

-p: Publish a container’s port(s) to the host (hostport:dockerport)

To test:
```
GET http://localhost:Y/WeatherForecast
```

## How do debug in VS Code

1. Open the folder in Visual Studio Code (File -> Open Folder...).

2.  Click on the icon with the bug and the play button on the far left.

3.  At the top, there's a section that says "Run", make sure it's set to "Docker .NET Core Launch"

![](.\images\docker-dot-net-core-launch.png)

4.  Set a breakpoint wherever, for this example I set it on the UserController in the ``POST``.

![](.\images\breakpoint-example.png)

5.  Click the green play button, the container should build then run in debug mode.  The bottom should turn orange.

6. Check the port that comes up on your browser, mine was ``https://localhost:32773`` Using your tool of choice to send a request (I use [Postman](https://www.postman.com/downloads/)), hit the endpoint.

![](.\images\postman-post-user.png)

7. Your breakpoint should hit, and you can see the debug info:

![](.\images\hit-breakpoint.png)



## 1 Create

**User**
Request:  
`POST {baseurl}/user`
```
{
	"email":"student@gatech.edu",
	"user_name":"Santa Claus",
	"password_hash":"dsajkdhsk389q3ur89fncdx:)"
}
```

Response:  `201`  
`Location: {baseurl}/user/{email}`

**Drug Price**
Request:  
`POST {baseUrl}/drug/{drug_name}/price`
```
{
	"dp_id":"1",
	"drug_name":"lipitor",
	"rxcui":"12345",
	"zip_code":"90210",
	"price":"100000.00",
	"quantity":"2",
	"date":"10OCT2020", #optional
	"business":"CVS" #optional
}
```

Response:  `201`  
`Location: {baseUrl}/drug/{drug_name}/price/{uid}`


## 2 Read

**User**
Request:  
`GET {baseurl}/user/{email}`


Response:  `200`  
Body:
```
{
	"email":"student@gatech.edu",
	"user_name":"Santa Claus",
	"password_hash":"dsajkdhsk389q3ur89fncdx:)"
}
```

**Drug Price**
Request:  
`GET {baseUrl}/drug/{drug_name}/price`
or
`GET {baseUrl}/drug/{drug_name}/price?zip_code={zip_code}`


Response:  `200`  
Body:
```
{
	[
		{
			"dp_id":"1",
			"drug_name":"lipitor",
			"rxcui":"12345",
			"zip_code":"90210",
			"price":"100000.00"
			"date":"10OCT2020",
			"business":"CVS"
		},
		{
			"dp_id":"9",
			"drug_name":"lipitor",
			"rxcui":"23456",
			"zip_code":"91736",
			"price":"1.99"
			"date":"10OCT2020",
			"business":"Walgreens"
		}
	]
}
```

**Drug From RxNorm API**
Request:  
`GET {baseUrl}/drug/{drug_name}`

Response:  `200`  
Body:
```
{"drugGroup":
	{"name":"cymbalta","conceptGroup":[
		{"tty":"BPCK"},
		{"tty":"SBD","conceptProperties":[
			{"rxcui":"596928","name":"duloxetine 20 MG Delayed Release Oral Capsule [Cymbalta]","synonym":"Cymbalta 20 MG Delayed Release Oral Capsule","tty":"SBD","language":"ENG","suppress":"N","umlscui":"C1656295"},
			{"rxcui":"596932","name":"duloxetine 30 MG Delayed Release Oral Capsule [Cymbalta]","synonym":"Cymbalta 30 MG Delayed Release Oral Capsule","tty":"SBD","language":"ENG","suppress":"N","umlscui":"C1614249"},
			{"rxcui":"615186","name":"duloxetine 60 MG Delayed Release Oral Capsule [Cymbalta]","synonym":"Cymbalta 60 MG (as duloxetine HCl 67.3 MG) Delayed Release Oral Capsule","tty":"SBD","language":"ENG","suppress":"N","umlscui":"C1624617"}
		]}
	]}
}
```

## 3 Update

Update calls to the API will not be used.

## 4 Delete

**User**
Request:  
`DELETE {baseurl}/user/{email}`
Response:  `204 No Content`  


**Drug Price**
Request:  
`DELETE {baseUrl}/drug/price/{uid}`
Response:  `204 No Content`  
