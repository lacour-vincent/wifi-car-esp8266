import React from "react";

import { render } from "@/testing/render";

import Icon from "./index";

describe("<Icon />", () => {
  it("should render without crashing", async () => {
    const props = { name: "emoticon" as const };
    const { getByTestId } = render(<Icon {...props} />);
    expect(getByTestId(props.name)).toBeDefined();
  });
});
