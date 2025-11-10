import { getRouteWithParams } from "@/utils/url";

const DEFAULT_TIMEOUT_IN_MS = 3000;

export enum HTTP_METHOD {
  GET = "GET",
  POST = "POST",
  PATCH = "PATCH",
  PUT = "PUT",
  DELETE = "DELETE",
}

interface FetchParams {
  headers?: Record<string, string>;
  pathParams?: Record<string, string>;
  queryParams?: Record<string, unknown>;
  payload?: unknown;
}

export class FetchError extends Error {
  status: number;
  text: string;
  data: unknown;

  constructor(status: number, text: string, data: unknown) {
    super(text);
    this.status = status;
    this.text = text;
    this.data = data;
  }
}

export function get<T>(base: string, path: string, params: FetchParams = {}): Promise<{ data: T; response: Response }> {
  return fetcher<T>(base, HTTP_METHOD.GET, path, params);
}

export function post<T>(
  base: string,
  path: string,
  params: FetchParams = {},
): Promise<{ data: T; response: Response }> {
  return fetcher<T>(base, HTTP_METHOD.POST, path, params);
}

export function patch<T>(
  base: string,
  path: string,
  params: FetchParams = {},
): Promise<{ data: T; response: Response }> {
  return fetcher<T>(base, HTTP_METHOD.PATCH, path, params);
}

export function put<T>(base: string, path: string, params: FetchParams = {}): Promise<{ data: T; response: Response }> {
  return fetcher<T>(base, HTTP_METHOD.PUT, path, params);
}

export function delete_<T>(
  base: string,
  path: string,
  params: FetchParams = {},
): Promise<{ data: T; response: Response }> {
  return fetcher<T>(base, HTTP_METHOD.DELETE, path, params);
}

async function fetcher<T>(
  base: string,
  method: string,
  path: string,
  params: FetchParams,
): Promise<{ data: T; response: Response }> {
  const headers = getHeaders(params);
  const url = getUrl(base, path, params);
  const body = getBody(params);
  const response: Response = await fetch(url, {
    method,
    headers,
    body,
    signal: AbortSignal.timeout(DEFAULT_TIMEOUT_IN_MS),
  });
  const data: T = await getResponse<T>(response, headers);
  if (!response.ok) {
    const { status, statusText } = response;
    throw new FetchError(status, statusText, data);
  }
  return { data, response };
}

function getHeaders(params: FetchParams): Headers {
  const headers = new Headers();
  headers.set("Accept", "application/json");
  headers.set("Content-Type", "application/json");
  if (!params.headers) return headers;
  Object.entries(params.headers).forEach(([key, value]) => {
    headers.set(key, value);
  });
  return headers;
}

function getUrl(base: string, path: string, params: FetchParams): RequestInfo {
  const url = `${base}${getRouteWithParams(path, params.pathParams, params.queryParams)}`;
  return url;
}

function getBody(params: FetchParams): BodyInit | null {
  if (!params.payload) return null;
  const body: string = JSON.stringify(params.payload);
  return body;
}

function parser(response: Response, headers: Headers) {
  switch (headers.get("accept")) {
    case "text/plain":
      return response.text();
    default:
      return response.json();
  }
}

async function getResponse<T>(response: Response, headers: Headers): Promise<T> {
  try {
    const data = await parser(response, headers);
    return data as T;
  } catch {
    return {} as T;
  }
}
