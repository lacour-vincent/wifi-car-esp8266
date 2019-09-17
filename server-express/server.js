const express = require("express");

const app = express();
const host = "192.168.0.18"; /* Your IP Address */
const port = 5000; /* Port */

app.get("/", (req, res) => {
  res.setHeader("Content-Type", "text/plain");
  res.sendStatus(404);
});

app.get("/move", (req, res) => {
  res.setHeader("Content-Type", "text/plain");
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
  res.sendStatus(200);
});

app.get("/action", (req, res) => {
  res.setHeader("Content-Type", "text/plain");
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
  res.sendStatus(200);
});

app.get("/speed", (req, res) => {
  res.setHeader("Content-Type", "text/plain");
  const { value } = req.query;
  console.log(`Speed : ${value} %`);
  res.sendStatus(200);
});

app.listen(port, host, () => {
  console.log(`Express server is listening on port ${port}.`);
});
