/** @type {import('jest').Config} */
const config = {
  preset: "jest-expo",
  testEnvironment: "node",
  testMatch: ["<rootDir>/src/**/*.test.ts"],
  moduleNameMapper: {
    "^@/(.*)$": "<rootDir>/src/$1",
    "^@ui/(.*)$": "<rootDir>/src/components/ui/$1",
  },
  setupFilesAfterEnv: ["<rootDir>/src/testing/setup.ts"],
  globalSetup: "<rootDir>/src/testing/globals.ts",
};

export default config;
