import type { DialogId } from "@/referential/dialog";

import { createAction } from "@/store/actions";

export const openDialog = createAction<{ id: DialogId }>("OPEN_DIALOG");
export const closeDialog = createAction<{ id: DialogId }>("CLOSE_DIALOG");
