import { createAction as createActionFn } from "@reduxjs/toolkit";

export enum Suffix {
  REQUEST = "REQUEST",
  SUCCESS = "SUCCESS",
  FAILURE = "FAILURE",
}

export interface FailureActionPayload {
  err: unknown;
}

export type RequestAction = ReturnType<typeof createRequestAction> | ReturnType<typeof createRequestPayloadAction>;

export const createAction = (type: string) => createActionFn(type);

export const createPayloadAction = <T>(type: string) => createActionFn<T>(type);

export const createRequestAction = <S = void>(type: string) => {
  return {
    request: createAction(`${type}_${Suffix.REQUEST}`),
    success: createPayloadAction<S>(`${type}_${Suffix.SUCCESS}`),
    failure: createPayloadAction<FailureActionPayload>(`${type}_${Suffix.FAILURE}`),
    toString: () => type,
  };
};

export const createRequestPayloadAction = <R, S = void>(type: string) => {
  return {
    request: createPayloadAction<R>(`${type}_${Suffix.REQUEST}`),
    success: createPayloadAction<S>(`${type}_${Suffix.SUCCESS}`),
    failure: createPayloadAction<FailureActionPayload>(`${type}_${Suffix.FAILURE}`),
    toString: () => type,
  };
};
