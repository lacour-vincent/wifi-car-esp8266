import React from "react";

import { render } from "@/testing/react-native";

import Dialog from "./index";

const defaultProps = {
  title: "dialog-title",
  description: "dialog-description",
  actions: [{ label: "OK", callback: jest.fn() }],
  visible: true,
  onClose: () => jest.fn(),
};

describe("<Dialog />", () => {
  it("should render dialog content", () => {
    const props = { ...defaultProps };
    const { getByText, getByRole } = render(<Dialog {...props} />);
    expect(getByText(props.title)).toBeDefined();
    expect(getByText(props.description)).toBeDefined();
    props.actions.forEach((action) => expect(getByRole("button", { name: action.label })).toBeDefined());
  });

  it("should call action callback", async () => {
    const props = { ...defaultProps };
    const { getByRole, event } = render(<Dialog {...props} />);
    await event.press(getByRole("button", { name: props.actions[0].label }));
    expect(props.actions[0].callback).toHaveBeenCalled();
  });
});
