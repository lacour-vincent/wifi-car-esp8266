import type { DialogId } from "@/referential/dialog";

import type { State } from "@/store/reducers";

export const isDialogOpen = (dialogId: DialogId) => (state: State) => state.dialog.includes(dialogId);
