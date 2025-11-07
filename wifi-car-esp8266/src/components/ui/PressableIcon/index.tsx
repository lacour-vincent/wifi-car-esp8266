import React, { type FC } from "react";
import { type GestureResponderEvent, Pressable, type StyleProp, type ViewStyle } from "react-native";

import Icon, { type Props as IconProps } from "@ui/Icon";

import theme from "@/styling";

import s from "./styles";

interface Props {
  style?: StyleProp<ViewStyle>;
  label: string;
  icon: IconProps["name"];
  onPress: (event: GestureResponderEvent) => void;
}

const ICON_SIZE = 22;

const PressableIcon: FC<Props> = ({ style, label, icon, onPress }) => {
  return (
    <Pressable
      style={[style, s.pressable]}
      role="button"
      aria-label={label}
      accessibilityRole="button"
      accessibilityLabel={label}
      android_ripple={{ radius: 0.8 * ICON_SIZE, foreground: true }}
      onPressIn={onPress}
    >
      <Icon name={icon} size={ICON_SIZE} color={theme["primary-color-text"]} />
    </Pressable>
  );
};

export default PressableIcon;
