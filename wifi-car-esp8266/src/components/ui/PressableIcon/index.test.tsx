import React from "react";

import { render } from "@/testing/render";

import PressableIcon from "./index";

describe("<PressableIcon />", () => {
  it("should render without crashing", () => {
    const props = { icon: "emoticon" as const, label: "label", onPress: jest.fn() };
    const { getByRole, getByTestId } = render(<PressableIcon {...props} />);
    expect(getByRole("button", { name: props.label })).toBeDefined();
    expect(getByTestId(props.icon)).toBeDefined();
  });

  it("should call onPress", async () => {
    const props = { icon: "emoticon" as const, label: "label", onPress: jest.fn() };
    const { getByRole, getByTestId, event } = render(<PressableIcon {...props} />);
    await event.press(getByRole("button", { name: props.label }));
    expect(await getByTestId(props.icon)).toBeDefined();
    expect(props.onPress).toHaveBeenCalled();
  });
});
