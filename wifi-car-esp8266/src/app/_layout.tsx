import React, { type FC, StrictMode } from "react";

import { Stack } from "expo-router";
import * as SplashScreen from "expo-splash-screen";

SplashScreen.hide();
SplashScreen.setOptions({ duration: 400, fade: true });

const RootLayout: FC = () => {
  return (
    <StrictMode>
      <Stack screenOptions={{ title: "Wifi RC Car ESP8266" }} />
    </StrictMode>
  );
};

export default RootLayout;
