import { decode, encode } from "qss";

export const parse = (url: string) => {
  const withoutPrefix = url.replace("?", "");
  return decode(withoutPrefix);
};

export const stringify = (params: Record<string, unknown>) => {
  const obj = Object.fromEntries(Object.entries(params).filter(([, v]) => v !== "" && v !== null));
  return encode(obj);
};

export const getRouteWithParams = (
  path: string,
  pathParams: Record<string, string> = {},
  queryParams: Record<string, unknown> = {},
): string => {
  const route = Object.entries(pathParams).reduce((acc, [key, value]) => {
    return acc.replace(`:${key}`, encodeURI(value));
  }, path);
  const params = stringify(queryParams);
  const query = params ? `?${params}` : "";
  return `${route}${query}`;
};
