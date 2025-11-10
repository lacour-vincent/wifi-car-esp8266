import { Action, Direction } from "@/typings/car";

export const DEFAULT_DIRECTION_VALUES: Record<Direction, string> = {
  [Direction.FORWARD]: "F",
  [Direction.BACKWARD]: "B",
  [Direction.TURN_RIGHT]: "R",
  [Direction.TURN_LEFT]: "L",
};

export const DEFAULT_ACTION_VALUES: Record<Action, string> = {
  [Action.ONE]: "1",
  [Action.TWO]: "2",
  [Action.THREE]: "3",
  [Action.FOUR]: "4",
  [Action.FIVE]: "5",
  [Action.SIX]: "6",
  [Action.SEVEN]: "7",
  [Action.EIGHT]: "8",
};
