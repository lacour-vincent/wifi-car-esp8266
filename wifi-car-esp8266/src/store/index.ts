import { type Store, configureStore as configureStoreFn } from "@reduxjs/toolkit";

import extra from "@/store/extra";
import listener from "@/store/listener";
import createRootReducer from "@/store/reducers";

export const createStore = (): { store: Store } => {
  const reducer = createRootReducer();
  const store = configureStoreFn({
    reducer,
    middleware: (getDefaultMiddleware) =>
      getDefaultMiddleware({ thunk: { extraArgument: extra } }).prepend(listener.middleware),
  });
  return { store };
};
