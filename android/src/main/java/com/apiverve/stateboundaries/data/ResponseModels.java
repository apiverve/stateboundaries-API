// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     StateBoundariesData data = Converter.fromJsonString(jsonString);

package com.apiverve.stateboundaries.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static StateBoundariesData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(StateBoundariesData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(StateBoundariesData.class);
        writer = mapper.writerFor(StateBoundariesData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// StateBoundariesData.java

package com.apiverve.stateboundaries.data;

import com.fasterxml.jackson.annotation.*;

public class StateBoundariesData {
    private String type;
    private Feature[] features;

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("features")
    public Feature[] getFeatures() { return features; }
    @JsonProperty("features")
    public void setFeatures(Feature[] value) { this.features = value; }
}

// Feature.java

package com.apiverve.stateboundaries.data;

import com.fasterxml.jackson.annotation.*;

public class Feature {
    private String type;
    private Geometry geometry;
    private Properties properties;

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("geometry")
    public Geometry getGeometry() { return geometry; }
    @JsonProperty("geometry")
    public void setGeometry(Geometry value) { this.geometry = value; }

    @JsonProperty("properties")
    public Properties getProperties() { return properties; }
    @JsonProperty("properties")
    public void setProperties(Properties value) { this.properties = value; }
}

// Geometry.java

package com.apiverve.stateboundaries.data;

import com.fasterxml.jackson.annotation.*;

public class Geometry {
    private double[][][][] coordinates;
    private String type;

    @JsonProperty("coordinates")
    public double[][][][] getCoordinates() { return coordinates; }
    @JsonProperty("coordinates")
    public void setCoordinates(double[][][][] value) { this.coordinates = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }
}

// Properties.java

package com.apiverve.stateboundaries.data;

import com.fasterxml.jackson.annotation.*;

public class Properties {
    private long gid;
    private String arealand;
    private long division;
    private double intptlat;
    private String name;
    private long objectid;
    private String areawater;
    private double intptlon;
    private String oid;
    private String funcstat;
    private double centlon;
    private String stusab;
    private String state;
    private String statens;
    private double centlat;
    private String basename;
    private String mtfcc;
    private long region;
    private String lsadc;
    private String geoid;
    private GeoPoint2D geoPoint2D;

    @JsonProperty("gid")
    public long getGid() { return gid; }
    @JsonProperty("gid")
    public void setGid(long value) { this.gid = value; }

    @JsonProperty("arealand")
    public String getArealand() { return arealand; }
    @JsonProperty("arealand")
    public void setArealand(String value) { this.arealand = value; }

    @JsonProperty("division")
    public long getDivision() { return division; }
    @JsonProperty("division")
    public void setDivision(long value) { this.division = value; }

    @JsonProperty("intptlat")
    public double getIntptlat() { return intptlat; }
    @JsonProperty("intptlat")
    public void setIntptlat(double value) { this.intptlat = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("objectid")
    public long getObjectid() { return objectid; }
    @JsonProperty("objectid")
    public void setObjectid(long value) { this.objectid = value; }

    @JsonProperty("areawater")
    public String getAreawater() { return areawater; }
    @JsonProperty("areawater")
    public void setAreawater(String value) { this.areawater = value; }

    @JsonProperty("intptlon")
    public double getIntptlon() { return intptlon; }
    @JsonProperty("intptlon")
    public void setIntptlon(double value) { this.intptlon = value; }

    @JsonProperty("oid")
    public String getOID() { return oid; }
    @JsonProperty("oid")
    public void setOID(String value) { this.oid = value; }

    @JsonProperty("funcstat")
    public String getFuncstat() { return funcstat; }
    @JsonProperty("funcstat")
    public void setFuncstat(String value) { this.funcstat = value; }

    @JsonProperty("centlon")
    public double getCentlon() { return centlon; }
    @JsonProperty("centlon")
    public void setCentlon(double value) { this.centlon = value; }

    @JsonProperty("stusab")
    public String getStusab() { return stusab; }
    @JsonProperty("stusab")
    public void setStusab(String value) { this.stusab = value; }

    @JsonProperty("state")
    public String getState() { return state; }
    @JsonProperty("state")
    public void setState(String value) { this.state = value; }

    @JsonProperty("statens")
    public String getStatens() { return statens; }
    @JsonProperty("statens")
    public void setStatens(String value) { this.statens = value; }

    @JsonProperty("centlat")
    public double getCentlat() { return centlat; }
    @JsonProperty("centlat")
    public void setCentlat(double value) { this.centlat = value; }

    @JsonProperty("basename")
    public String getBasename() { return basename; }
    @JsonProperty("basename")
    public void setBasename(String value) { this.basename = value; }

    @JsonProperty("mtfcc")
    public String getMtfcc() { return mtfcc; }
    @JsonProperty("mtfcc")
    public void setMtfcc(String value) { this.mtfcc = value; }

    @JsonProperty("region")
    public long getRegion() { return region; }
    @JsonProperty("region")
    public void setRegion(long value) { this.region = value; }

    @JsonProperty("lsadc")
    public String getLsadc() { return lsadc; }
    @JsonProperty("lsadc")
    public void setLsadc(String value) { this.lsadc = value; }

    @JsonProperty("geoid")
    public String getGeoid() { return geoid; }
    @JsonProperty("geoid")
    public void setGeoid(String value) { this.geoid = value; }

    @JsonProperty("geo_point_2d")
    public GeoPoint2D getGeoPoint2D() { return geoPoint2D; }
    @JsonProperty("geo_point_2d")
    public void setGeoPoint2D(GeoPoint2D value) { this.geoPoint2D = value; }
}

// GeoPoint2D.java

package com.apiverve.stateboundaries.data;

import com.fasterxml.jackson.annotation.*;

public class GeoPoint2D {
    private double lon;
    private double lat;

    @JsonProperty("lon")
    public double getLon() { return lon; }
    @JsonProperty("lon")
    public void setLon(double value) { this.lon = value; }

    @JsonProperty("lat")
    public double getLat() { return lat; }
    @JsonProperty("lat")
    public void setLat(double value) { this.lat = value; }
}