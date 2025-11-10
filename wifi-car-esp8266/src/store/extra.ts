import { router as expoRouter } from "expo-router";

import car, { type CarService } from "@/services/car";

export interface Extra {
  services: Services;
  router: Router;
}

interface Services {
  car: CarService;
}

const services: Services = {
  car: car.impl,
};

interface Router {
  navigate: (href: string) => void;
  replace: (href: string) => void;
}

const router: Router = {
  navigate: expoRouter.navigate,
  replace: expoRouter.replace,
};

const extra: Extra = { services, router };

export default extra;
