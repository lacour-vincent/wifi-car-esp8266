import type { DialogId } from "@/referential/dialog";

import { closeDialog, openDialog } from "@/store/actions/dialog";
import { isDialogOpen } from "@/store/selectors/dialog";
import StoreTester from "@/store/tests";

describe("Store - dialog", () => {
  let store: StoreTester;

  beforeEach(() => {
    store = new StoreTester();
  });

  it("should perform open dialog action", () => {
    const id = "DIALOG_ID" as unknown as DialogId;
    const action = openDialog({ id });
    expect(isDialogOpen(id)(store.getState())).toBe(false);
    store.dispatch(action);
    expect(isDialogOpen(id)(store.getState())).toBe(true);
  });

  it("should perform close dialog action", () => {
    const id = "DIALOG_ID" as unknown as DialogId;
    const open = openDialog({ id });
    store.dispatch(open);
    const close = closeDialog({ id });
    store.dispatch(close);
    expect(isDialogOpen(id)(store.getState())).toBe(false);
  });
});
