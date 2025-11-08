import type { AsyncAction } from "@/store/actions";
import type { State } from "@/store/reducers";

export const createPendingActionsSelector = (actions: AsyncAction[]) => (state: State) => {
  return actions.some((action: AsyncAction) => {
    const key = action.typePrefix;
    const value = state.actions[key];
    return value?.pending;
  });
};

export const createFulfilledActionsSelector = (actions: AsyncAction[]) => (state: State) => {
  return actions.some((action: AsyncAction) => {
    const key = action.typePrefix;
    const value = state.actions[key];
    return value?.fulfilled;
  });
};

export const createRejectedActionsSelector = (actions: AsyncAction[]) => (state: State) => {
  return actions.some((action: AsyncAction) => {
    const key = action.typePrefix;
    const value = state.actions[key];
    return value?.rejected;
  });
};
