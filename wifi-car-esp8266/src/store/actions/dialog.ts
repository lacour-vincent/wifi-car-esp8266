import type { DialogId } from "@/referential/dialog";

import { createPayloadAction } from "@/store/actions";

export const openDialog = createPayloadAction<{ id: DialogId }>("OPEN_DIALOG");
export const closeDialog = createPayloadAction<{ id: DialogId }>("CLOSE_DIALOG");
