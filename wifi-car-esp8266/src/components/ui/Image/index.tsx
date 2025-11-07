import React, { type FC } from "react";
import { type ImageSourcePropType, type ImageStyle, type StyleProp } from "react-native";

import { Image as ImageUI } from "expo-image";

import s from "./styles";

interface Props {
  style?: StyleProp<ImageStyle>;
  src: ImageSourcePropType;
  alt: string;
}

const Image: FC<Props> = ({ style, src, alt }) => {
  return (
    <ImageUI
      style={[s.image, style]}
      source={src}
      alt={alt}
      accessibilityLabel={alt}
      role="img"
      accessibilityRole="image"
      contentFit="contain"
    />
  );
};

export default Image;
