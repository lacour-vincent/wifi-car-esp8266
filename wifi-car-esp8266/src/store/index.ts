import { type Store, configureStore as configureStoreFn } from "@reduxjs/toolkit";

import createRootReducer from "@/store/reducers";

export const createStore = (): { store: Store } => {
  const reducer = createRootReducer();
  const store = configureStoreFn({ reducer, middleware: (getDefaultMiddleware) => getDefaultMiddleware() });
  return { store };
};
