import {
  type AsyncThunk,
  type AsyncThunkConfig,
  type AsyncThunkPayloadCreator,
  createAction as createActionFn,
  createAsyncThunk,
} from "@reduxjs/toolkit";

export enum Suffix {
  PENDING = "/pending",
  FULFILLED = "/fulfilled",
  REJECTED = "/rejected",
}

export type AsyncAction = AsyncThunk<void, void, AsyncThunkConfig>;

export const createAction = <Arg = void>(type: string) => createActionFn<Arg>(type);

export const createAsyncAction = <Arg = void, Returned = void>(
  type: string,
  handler: AsyncThunkPayloadCreator<Returned, Arg>,
) => {
  return createAsyncThunk(type, handler);
};
