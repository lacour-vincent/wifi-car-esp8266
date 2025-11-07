import React, { type FC, type ReactNode } from "react";
import { Pressable, type StyleProp, type TextStyle } from "react-native";

import { type LinkProps, Link as LinkUI } from "expo-router";

interface Props extends LinkProps {
  style?: StyleProp<TextStyle>;
  href: string;
  label?: string;
  children: ReactNode;
}

const Link: FC<Props> = ({ style, href, label, children }) => {
  return (
    <LinkUI
      style={style}
      href={href}
      role="link"
      aria-label={label}
      accessibilityRole="link"
      accessibilityLabel={label}
      asChild
    >
      <Pressable>{children}</Pressable>
    </LinkUI>
  );
};

export default Link;
