import {
  type AsyncThunkAction,
  type AsyncThunkConfig,
  type Middleware,
  type UnknownAction,
  configureStore as configureStoreFn,
  type createAction,
} from "@reduxjs/toolkit";

import createRootReducer, { type State } from "@/store/reducers";

interface LookUpAction extends UnknownAction {
  type: string;
  counter: number;
  resolvers: Map<number, () => void>;
}

class StoreTester {
  private store = this.configureStore();
  private lookups: Record<UnknownAction["type"], LookUpAction> = {};

  private configureStore() {
    const testingMiddleware = this.createTestingMiddleware();
    const middlewares = [testingMiddleware];
    const reducers = createRootReducer();
    const store = configureStoreFn({
      reducer: reducers,
      middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(middlewares),
    });
    return store;
  }

  private createTestingMiddleware(): Middleware<unknown, State> {
    return () => (next) => (action: unknown) => {
      const type = (action as UnknownAction).type;
      if (!this.lookups[type]) this.lookups[type] = { type, counter: 0, resolvers: new Map() };
      const lookup = this.lookups[type];
      if (lookup) {
        lookup.counter++;
        lookup.resolvers.get(lookup.counter)?.();
      }
      return next(action);
    };
  }

  dispatch(action: UnknownAction | AsyncThunkAction<void, void, AsyncThunkConfig>) {
    return this.store.dispatch(action);
  }

  getState(): State {
    return this.store.getState();
  }

  waitFor(action: ReturnType<typeof createAction>, count: number = 1): Promise<void> {
    const type = action.type;
    if (!this.lookups[type]) this.lookups[type] = { type, counter: 0, resolvers: new Map() };
    const lookup = this.lookups[type];
    if (lookup.counter === count) return Promise.resolve();
    return new Promise((resolve) => {
      lookup.resolvers.set(count, resolve);
    });
  }
}

export default StoreTester;
