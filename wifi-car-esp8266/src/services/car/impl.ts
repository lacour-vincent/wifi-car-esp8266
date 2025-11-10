import type { CarService } from "@/services/car";

const move: CarService["move"] = async () => {
  return;
};

const stop: CarService["stop"] = async () => {
  return;
};

const action: CarService["action"] = async () => {
  return;
};

const service: CarService = { move, stop, action };

export default service;
