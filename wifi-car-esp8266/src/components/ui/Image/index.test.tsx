import React from "react";

import { render } from "@/testing/render";

import Image from "./index";

const defaultProps = { src: { uri: "image-uri" }, alt: "accessibility-label" };

describe("<Image />", () => {
  it("should render without crashing", () => {
    const props = { ...defaultProps };
    const { getByRole } = render(<Image {...props} />);
    expect(getByRole("img", { name: props.alt })).toBeDefined();
  });
});
