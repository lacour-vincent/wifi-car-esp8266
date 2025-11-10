import type { Action, Direction } from "@/typings/car";

import fake from "./fake";
import impl from "./impl";

export interface CarService {
  move: (direction: Direction) => void;
  stop: () => void;
  action: (action: Action) => void;
}

export default { impl, fake };
