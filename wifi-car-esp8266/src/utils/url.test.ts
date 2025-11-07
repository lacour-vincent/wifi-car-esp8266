import { getRouteWithParams, parse, stringify } from "@/utils/url";

describe("parse", () => {
  test.each([
    ["", {}],
    ["?id=abc", { id: "abc" }],
    ["?id=42", { id: 42 }],
    ["?bool=true", { bool: true }],
    ["?id=42&rid=abc", { id: 42, rid: "abc" }],
    ["?id=1%202%203", { id: "1 2 3" }],
    ["?id=1&id=2", { id: [1, 2] }],
    ["id=abc", { id: "abc" }],
  ])("should parse params %p -> %p", (params, expected) => {
    expect(parse(params)).toStrictEqual(expected);
  });
});

describe("stringify", () => {
  test.each([
    [{}, ""],
    [{ id: "abc" }, "id=abc"],
    [{ id: 42 }, "id=42"],
    [{ bool: true }, "bool=true"],
    [{ id: 42, rid: "abc" }, "id=42&rid=abc"],
    [{ id: "1 2 3" }, "id=1%202%203"],
    [{ id: ["1", "2"] }, "id=1&id=2"],
    [{ id: "" }, ""],
    [{ id: null }, ""],
  ])("should stringify params %p -> %p", (params, expected) => {
    expect(stringify(params)).toStrictEqual(expected);
  });
});

describe("getRouteWithParams", () => {
  it.each([
    ["/example", {}, {}, "/example"],
    ["/example/:id", {}, {}, "/example/:id"],
    ["/example", {}, { id: undefined }, "/example"],
  ])("should return path without params : %s %s %s - %s", (path, pathParams, queryParams, expected) => {
    expect(getRouteWithParams(path, pathParams, queryParams)).toBe(expected);
  });

  it.each([
    ["/example/:id", { id: "123" }, {}, "/example/123"],
    ["/example/:id", { id: "a b c" }, {}, "/example/a%20b%20c"],
    ["/example", {}, { id: "123" }, "/example?id=123"],
    ["/example", {}, { id: "a b c" }, "/example?id=a%20b%20c"],
    ["/example", {}, { id: ["a"] }, "/example?id=a"],

    ["/example/:id", { id: "123" }, {}, "/example/123"],
    ["/example", {}, { id: "123" }, "/example?id=123"],
    ["/example/:id", { id: "abc" }, { id: 123 }, "/example/abc?id=123"],
  ])("should return path with one param : %s %s %s %s - %s", (path, pathParams, queryParams, expected) => {
    expect(getRouteWithParams(path, pathParams, queryParams)).toBe(expected);
  });

  it.each([
    ["/example", {}, { id: "abc", rid: "123" }, "/example?id=abc&rid=123"],
    ["/example/:id/:rid", { id: "abc", rid: "123" }, {}, "/example/abc/123"],
    ["/example/:id/:rid", { id: "abc", rid: "123" }, { id: "abc", rid: "123" }, "/example/abc/123?id=abc&rid=123"],
  ])("should return path with many params : %s %s %s - %s", (path, pathParams, queryParams, expected) => {
    expect(getRouteWithParams(path, pathParams, queryParams)).toBe(expected);
  });
});
