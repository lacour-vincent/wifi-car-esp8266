import car from "@/services/car";

import type { Extra } from "@/store/extra";

const services: Extra["services"] = {
  car: car.fake,
};

const router: Extra["router"] = {
  navigate: () => true,
  replace: () => true,
};

const extra: Extra = { services, router };

export default extra;
