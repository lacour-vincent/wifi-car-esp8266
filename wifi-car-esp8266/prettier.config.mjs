/** @type {import("prettier").Config} */
const config = {
  printWidth: 120,
  endOfLine: "auto",
  importOrder: [
    "^(react|react-native)$",
    "^(expo/(.*)|expo-(.*))$",
    "<THIRD_PARTY_MODULES>",
    "^@/(typings)(.*)$",
    "^@/(store)(.*)$",
    "^@/(hooks)(.*)$",
    "^@/(components)(.*)$",
    "^@ui/(.*)$",
    "^@/(styling|assets)(.*)$",
    "^[./]",
  ],
  importOrderSeparation: true,
  importOrderSortSpecifiers: true,
  plugins: ["@trivago/prettier-plugin-sort-imports"],
};

export default config;
