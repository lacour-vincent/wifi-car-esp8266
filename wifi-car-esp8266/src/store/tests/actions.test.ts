import { type AsyncAction, createAsyncAction } from "@/store/actions";
import {
  createFulfilledActionsSelector,
  createPendingActionsSelector,
  createRejectedActionsSelector,
} from "@/store/selectors/actions";
import StoreTester from "@/store/tests";

const createActionsSelector = (actions: AsyncAction[]) => {
  return {
    pending: createPendingActionsSelector(actions),
    fulfilled: createFulfilledActionsSelector(actions),
    rejected: createRejectedActionsSelector(actions),
  };
};

describe("Store - actions", () => {
  let store: StoreTester;

  beforeEach(() => {
    store = new StoreTester();
  });

  it("should listen pending actions", () => {
    const action = createAsyncAction<void, void>("ANY_ACTION", () => Promise.resolve());
    const { pending, fulfilled, rejected } = createActionsSelector([action]);
    expect(pending(store.getState())).toBe(false);
    expect(fulfilled(store.getState())).toBe(false);
    expect(rejected(store.getState())).toBe(false);
    store.dispatch(action());
    expect(pending(store.getState())).toBe(true);
    expect(fulfilled(store.getState())).toBe(false);
    expect(rejected(store.getState())).toBe(false);
  });

  it("should listen fulfilled actions", async () => {
    const action = createAsyncAction<void, void>("ANY_ACTION", () => Promise.resolve());
    const { pending, fulfilled, rejected } = createActionsSelector([action]);
    expect(pending(store.getState())).toBe(false);
    expect(fulfilled(store.getState())).toBe(false);
    expect(rejected(store.getState())).toBe(false);
    store.dispatch(action());
    await store.waitFor(action.fulfilled);
    expect(pending(store.getState())).toBe(false);
    expect(fulfilled(store.getState())).toBe(true);
    expect(rejected(store.getState())).toBe(false);
  });

  it("should listen rejected actions", async () => {
    const action = createAsyncAction<void, void>("ANY_ACTION", () => Promise.reject());
    const { pending, fulfilled, rejected } = createActionsSelector([action]);
    expect(pending(store.getState())).toBe(false);
    expect(fulfilled(store.getState())).toBe(false);
    expect(rejected(store.getState())).toBe(false);
    store.dispatch(action());
    await store.waitFor(action.rejected);
    expect(pending(store.getState())).toBe(false);
    expect(fulfilled(store.getState())).toBe(false);
    expect(rejected(store.getState())).toBe(true);
  });
});
