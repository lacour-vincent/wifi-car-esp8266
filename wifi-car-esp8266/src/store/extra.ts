import { router as expoRouter } from "expo-router";

export interface Extra {
  services: Services;
  router: Router;
}

interface Services {
  car: unknown;
}

const services: Services = {
  car: null,
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
