import { type FC, type ReactElement, type ReactNode } from "react";

// eslint-disable-next-line no-restricted-imports
import { render, userEvent } from "@testing-library/react-native";

const customRender = (ui: ReactElement) => {
  const event = userEvent.setup();

  const Wrapper: FC<{ children: ReactNode }> = ({ children }) => {
    return children;
  };

  return { event, ...render(ui, { wrapper: Wrapper }) };
};

export { customRender as render };
