import { type ExpoConfig } from "expo/config";

const config: ExpoConfig = {
  name: "Wifi RC Car ESP8266",
  slug: "wifi-car-esp8266",
  owner: "lacour-vincent",
  version: "2.0.0-SNAPSHOT",
  scheme: "wifi-car-esp8266",
  platforms: ["ios", "android"],
  orientation: "portrait",
  icon: "./assets/images/icon.png",
  ios: {
    bundleIdentifier: "com.lacour.vincent.wificaresp8266",
    buildNumber: "15",
    icon: "./assets/images/icon.png",
  },
  android: {
    package: "com.lacour.vincent.wificaresp8266",
    versionCode: 15,
    adaptiveIcon: {
      backgroundColor: "#E6F4FE",
      foregroundImage: "./assets/images/android-icon-foreground.png",
      backgroundImage: "./assets/images/android-icon-background.png",
    },
    permissions: ["android.permission.INTERNET"],
    blockedPermissions: [],
  },
  experiments: { reactCompiler: true },
  plugins: [
    "expo-router",
    [
      "expo-build-properties",
      {
        ios: { deploymentTarget: "15.1" },
        android: {
          buildToolsVersion: "36.1.0",
          compileSdkVersion: 36,
          targetSdkVersion: 36,
          minSdkVersion: 21,
        },
      },
    ],
    [
      "expo-splash-screen",
      {
        image: "./assets/images/splash-icon.png",
        imageWidth: 200,
        resizeMode: "contain",
        backgroundColor: "#ffffff",
      },
    ],
  ],
};

export default config;
