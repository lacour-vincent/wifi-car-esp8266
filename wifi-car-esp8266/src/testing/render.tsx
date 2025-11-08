import React, { type FC, type ReactElement, type ReactNode } from "react";

import { createTestStore } from "@/testing/store";
// eslint-disable-next-line no-restricted-imports
import { type RenderOptions, render, userEvent } from "@testing-library/react-native";
import { Provider } from "react-redux";

interface Options extends RenderOptions {
  store?: ReturnType<typeof createTestStore>;
}

const customRender = (ui: ReactElement, options?: Options) => {
  const event = userEvent.setup();
  const store = options?.store ?? createTestStore();

  const Wrapper: FC<{ children: ReactNode }> = ({ children }) => {
    return <Provider store={store}>{children}</Provider>;
  };

  return { event, ...render(ui, { wrapper: Wrapper, ...options }) };
};

export { customRender as render };
