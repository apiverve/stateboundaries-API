declare module '@apiverve/stateboundaries' {
  export interface stateboundariesOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface stateboundariesResponse {
    status: string;
    error: string | null;
    data: StateBoundariesData;
    code?: number;
  }


  interface StateBoundariesData {
      type:     string;
      features: Feature[];
  }
  
  interface Feature {
      type:       string;
      geometry:   Geometry;
      properties: Properties;
  }
  
  interface Geometry {
      coordinates: Array<Array<Array<number[]>>>;
      type:        string;
  }
  
  interface Properties {
      gid:        number;
      arealand:   string;
      division:   number;
      intptlat:   number;
      name:       string;
      objectid:   number;
      areawater:  string;
      intptlon:   number;
      oid:        string;
      funcstat:   string;
      centlon:    number;
      stusab:     string;
      state:      string;
      statens:    string;
      centlat:    number;
      basename:   string;
      mtfcc:      string;
      region:     number;
      lsadc:      string;
      geoid:      string;
      geoPoint2D: GeoPoint2D;
  }
  
  interface GeoPoint2D {
      lon: number;
      lat: number;
  }

  export default class stateboundariesWrapper {
    constructor(options: stateboundariesOptions);

    execute(callback: (error: any, data: stateboundariesResponse | null) => void): Promise<stateboundariesResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: stateboundariesResponse | null) => void): Promise<stateboundariesResponse>;
    execute(query?: Record<string, any>): Promise<stateboundariesResponse>;
  }
}
