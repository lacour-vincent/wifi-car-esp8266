import React from "react";
import { Text } from "react-native";

import { render } from "@/testing/render";

import Link from "./index";

const defaultProps = { href: "/some-route" };

describe("<Link />", () => {
  it("should render without crashing", () => {
    const props = { ...defaultProps, children: <Text>Link</Text> };
    const { getByRole } = render(<Link {...props} />);
    expect(getByRole("link", { name: "Link" })).toBeDefined();
    expect(getByRole("link", { name: "Link" })).toHaveProp("href", props.href);
  });
});
