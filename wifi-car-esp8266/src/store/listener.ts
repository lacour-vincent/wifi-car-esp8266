import { createListenerMiddleware } from "@reduxjs/toolkit";

import extra from "@/store/extra";

const listener = createListenerMiddleware({ extra });

export default listener;
