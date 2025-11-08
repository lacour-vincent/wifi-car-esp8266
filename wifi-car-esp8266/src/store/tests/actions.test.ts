import { type RequestAction, createRequestAction } from "@/store/actions";
import { createFailureSelector, createRequestSelector, createSuccessSelector } from "@/store/selectors/actions";
import StoreTester from "@/store/tests";

const action: RequestAction = createRequestAction("ANY_ACTION");

const createActionsSelector = (actions: RequestAction[]) => {
  return {
    request: createRequestSelector(actions),
    success: createSuccessSelector(actions),
    failure: createFailureSelector(actions),
  };
};

describe("Store - actions", () => {
  let store: StoreTester;

  beforeEach(() => {
    store = new StoreTester();
  });

  it("should listen request action", () => {
    const { request, success, failure } = createActionsSelector([action]);
    expect(request(store.getState())).toBe(false);
    expect(success(store.getState())).toBe(false);
    expect(failure(store.getState())).toBe(false);
    store.dispatch(action.request());
    expect(request(store.getState())).toBe(true);
    expect(success(store.getState())).toBe(false);
    expect(failure(store.getState())).toBe(false);
  });

  it("should listen success action", () => {
    const { request, success, failure } = createActionsSelector([action]);
    expect(request(store.getState())).toBe(false);
    expect(success(store.getState())).toBe(false);
    expect(failure(store.getState())).toBe(false);
    store.dispatch(action.success({}));
    expect(request(store.getState())).toBe(false);
    expect(success(store.getState())).toBe(true);
    expect(failure(store.getState())).toBe(false);
  });

  it("should listen failure action", () => {
    const { request, success, failure } = createActionsSelector([action]);
    expect(request(store.getState())).toBe(false);
    expect(success(store.getState())).toBe(false);
    expect(failure(store.getState())).toBe(false);
    store.dispatch(action.failure({ err: new Error("failure") }));
    expect(request(store.getState())).toBe(false);
    expect(success(store.getState())).toBe(false);
    expect(failure(store.getState())).toBe(true);
  });
});
