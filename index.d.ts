declare module '@apiverve/stateboundaries' {
  export interface stateboundariesOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface stateboundariesResponse {
    status: string;
    error: string | null;
    data: any;
    code?: number;
  }

  export default class stateboundariesWrapper {
    constructor(options: stateboundariesOptions);

    execute(callback: (error: any, data: stateboundariesResponse | null) => void): Promise<stateboundariesResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: stateboundariesResponse | null) => void): Promise<stateboundariesResponse>;
    execute(query?: Record<string, any>): Promise<stateboundariesResponse>;
  }
}
