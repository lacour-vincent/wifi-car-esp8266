import { createListenerMiddleware } from "@reduxjs/toolkit";

import extra from "@/store/tests/extra";

const listener = createListenerMiddleware({ extra });

export default listener;
