const now = jest.spyOn(global.Date, "now");

beforeAll(() => {
  jest.useFakeTimers();
  now.mockReturnValue(new Date("1970-01-01T00:00:00Z").getTime());
});

afterAll(() => {
  jest.useRealTimers();
  now.mockRestore();
});

jest.mock("expo-image", () => {
  // eslint-disable-next-line @typescript-eslint/no-require-imports
  const { Image } = require("react-native");
  return { Image };
});

jest.mock("@expo/vector-icons/MaterialCommunityIcons", () => "FakeIcon");
