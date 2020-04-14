const express = require("express");

const app = express();
const host = "192.168.4.1"; /* Your IP Address */
const port = 8080; /* Port */

app.get("/move", (req, res) => {
  const { dir } = req.query;
  switch (dir) {
    case "F":
      console.log("Move : forward");
      break;
    case "B":
      console.log("Move : backward");
      break;
    case "L":
      console.log("Move : left");
      break;
    case "R":
      console.log("Move : right");
      break;
    case "S":
      console.log("Move : stop");
      break;
    default:
      console.log("Move: undefined");
      break;
  }
  res.status(200).send();
});

app.get("/action", (req, res) => {
  const { type } = req.query;
  switch (type) {
    case "1":
      console.log("Action : 1");
      break;
    case "2":
      console.log("Action : 2");
      break;
    case "3":
      console.log("Action : 3");
      break;
    case "4":
      console.log("Action : 4");
      break;
    case "5":
      console.log("Action : 5");
      break;
    case "6":
      console.log("Action : 6");
      break;
    case "7":
      console.log("Action : 7");
      break;
    case "8":
      console.log("Action : 8");
      break;
    default:
      console.log("Action: undefined");
  }
  res.status(200).send();
});

app.get("/speed", (req, res) => {
  const { value } = req.query;
  console.log(`Speed : ${value} %`);
  res.status(200).send();
});

app.get("/", (req, res) => {
  res.status(404).send();
});

app.listen(port, host, () => {
  console.log(`Express server is listening on port ${port}.`);
});
