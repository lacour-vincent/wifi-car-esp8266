import React, { type FC } from "react";
import { Text, View } from "react-native";

import s from "./styles";

const WelcomeView: FC = () => {
  return (
    <View style={s.container}>
      <Text>Welcome</Text>
    </View>
  );
};

export default WelcomeView;
