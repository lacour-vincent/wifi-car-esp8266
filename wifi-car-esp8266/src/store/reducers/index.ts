import { type UnknownAction, combineReducers } from "@reduxjs/toolkit";

import actions, { type ActionsState } from "@/store/reducers/actions";
import dialog, { type DialogState } from "@/store/reducers/dialog";

export interface State {
  actions: ActionsState;
  dialog: DialogState;
}

const createRootReducer = () => (state: State | undefined, action: UnknownAction) => {
  return combineReducers({ actions, dialog })(state, action);
};

export default createRootReducer;
