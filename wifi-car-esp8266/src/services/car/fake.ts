import type { CarService } from "@/services/car";

const move: CarService["move"] = async () => {
  return Promise.resolve();
};

const stop: CarService["stop"] = async () => {
  return Promise.resolve();
};

const action: CarService["action"] = async () => {
  return Promise.resolve();
};

const service: CarService = { move, stop, action };

export default service;
