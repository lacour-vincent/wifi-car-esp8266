import { type UnknownAction, createReducer } from "@reduxjs/toolkit";

import { Suffix } from "@/store/actions";

export type ActionsState = Record<string, { pending: boolean; fulfilled: boolean; rejected: boolean }>;

export const initialState: ActionsState = {};

const isPendingAction = (action: UnknownAction): boolean => {
  return action.type.endsWith(Suffix.PENDING);
};

const isFulfilledAction = (action: UnknownAction): boolean => {
  return action.type.endsWith(Suffix.FULFILLED);
};

const isRejectedAction = (action: UnknownAction): boolean => {
  return action.type.endsWith(Suffix.REJECTED);
};

export default createReducer(initialState, (builder) => {
  return builder
    .addMatcher(isPendingAction, (state, action) => {
      const key = action.type.replace(Suffix.PENDING, "");
      state[key] = { pending: true, fulfilled: false, rejected: false };
      return state;
    })
    .addMatcher(isFulfilledAction, (state, action) => {
      const key = action.type.replace(Suffix.FULFILLED, "");
      state[key] = { pending: false, fulfilled: true, rejected: false };
      return state;
    })
    .addMatcher(isRejectedAction, (state, action) => {
      const key = action.type.replace(Suffix.REJECTED, "");
      state[key] = { pending: false, fulfilled: false, rejected: true };
      return state;
    });
});
