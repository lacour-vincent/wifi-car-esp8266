import eslint from "@eslint/js";
import jestlint from "eslint-plugin-jest";
import reactlint from "eslint-plugin-react";
import tseslint from "typescript-eslint";

export default tseslint.config([
  {
    files: ["src/**/*.{ts,tsx}"],
    plugins: { react: reactlint },
    extends: [eslint.configs.recommended, tseslint.configs.recommended],
    settings: { react: { version: "detect" } },
    languageOptions: { parserOptions: { ecmaFeatures: { jsx: true } } },
    rules: {
      ...reactlint.configs.recommended.rules,
      "@typescript-eslint/consistent-type-imports": "error",
      "no-duplicate-imports": "error",
      "no-console": ["warn", { allow: ["info", "error"] }],
      "no-restricted-imports": [
        "error",
        {
          patterns: [
            { group: ["*.png", "!@/assets/**/*.png"], message: "Please import assets from @/assets instead." },
            { group: ["@testing-library/*"], message: "Please import from @/testing/react-native instead." },
            { group: ["@/components/ui/*"], message: "Please import from @ui/* instead." },
          ],
        },
      ],
    },
  },
  {
    files: ["src/**/*.test.{ts,tsx}"],
    plugins: { jest: jestlint },
    rules: { ...jestlint.configs.recommended.rules },
  },
]);
