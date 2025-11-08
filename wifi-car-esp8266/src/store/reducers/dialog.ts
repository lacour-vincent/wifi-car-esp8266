import type { DialogId } from "@/referential/dialog";
import { createReducer } from "@reduxjs/toolkit";

import { closeDialog, openDialog } from "@/store/actions/dialog";

export type DialogState = DialogId[];

const initialState: DialogState = [];

export default createReducer(initialState, (builder) => {
  return builder
    .addCase(openDialog, (state, action) => {
      state = state.concat(action.payload.id);
      return state;
    })
    .addCase(closeDialog, (state, action) => {
      state = state.filter((id) => action.payload.id !== id);
      return state;
    });
});
