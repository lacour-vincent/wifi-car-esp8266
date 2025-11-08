import type { RequestAction } from "@/store/actions";
import type { State } from "@/store/reducers";

export const createRequestSelector = (actions: RequestAction[]) => (state: State) => {
  return actions.some((action: RequestAction) => {
    const key = action.toString();
    const value = state.actions[key];
    return value?.request;
  });
};

export const createSuccessSelector = (actions: RequestAction[]) => (state: State) => {
  return actions.some((action: RequestAction) => {
    const key = action.toString();
    const value = state.actions[key];
    return value?.success;
  });
};

export const createFailureSelector = (actions: RequestAction[]) => (state: State) => {
  return actions.some((action: RequestAction) => {
    const key = action.toString();
    const value = state.actions[key];
    return value?.failure;
  });
};
