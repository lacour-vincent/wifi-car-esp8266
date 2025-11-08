import { type UnknownAction, createReducer } from "@reduxjs/toolkit";

import { Suffix } from "@/store/actions";

export type ActionsState = Record<string, { request: boolean; success: boolean; failure: boolean }>;

export const initialState: ActionsState = {};

const isRequestAction = (action: UnknownAction): boolean => {
  return action.type.endsWith(Suffix.REQUEST);
};

const isSuccessAction = (action: UnknownAction): boolean => {
  return action.type.endsWith(Suffix.SUCCESS);
};

const isFailureAction = (action: UnknownAction): boolean => {
  return action.type.endsWith(Suffix.FAILURE);
};

export default createReducer(initialState, (builder) => {
  return builder
    .addMatcher(isRequestAction, (state, action) => {
      const key = action.type.replace(`_${Suffix.REQUEST}`, "");
      state[key] = { request: true, success: false, failure: false };
      return state;
    })
    .addMatcher(isSuccessAction, (state, action) => {
      const key = action.type.replace(`_${Suffix.SUCCESS}`, "");
      state[key] = { request: false, success: true, failure: false };
      return state;
    })
    .addMatcher(isFailureAction, (state, action) => {
      const key = action.type.replace(`_${Suffix.FAILURE}`, "");
      state[key] = { request: false, success: false, failure: true };
      return state;
    });
});
