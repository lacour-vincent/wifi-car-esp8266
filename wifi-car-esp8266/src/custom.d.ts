declare module "*.jpeg" {
  import type { ImageSourcePropType } from "react-native";
  const jpeg: ImageSourcePropType;
  export default jpeg;
}

declare module "*.png" {
  const png: ImageSourcePropType;
  export default png;
}
