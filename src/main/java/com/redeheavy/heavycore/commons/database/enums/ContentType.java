package com.redeheavy.heavycore.commons.database.enums;

public enum ContentType {

    TINYINT,
    SMALLINT,
    MEDIUMINT,
    BIGINT,
    INT,
    DECIMAL,
    FLOAT,
    DOUBLE,
    REAL,
    BIT,
    BOOLEAN,
    SERIAL,
    DATE,
    DATETIME,
    TIMESTAMP,
    TIME,
    YEAR,
    CHAR,
    VARCHAR,
    TINYTEXT,
    MEDIUMTEXT,
    LONGTEXT,
    TEXT,
    BINARY,
    VARBINARY,
    TINYBLOB,
    MEDIUMBLOB,
    LONGBLOB,
    BLOB,
    ENUM,
    SET,
    GEOMETRY,
    POINT,
    LINESTRING,
    POLYGON,
    MULTIPOINT,
    MULTILINESTRING,
    MULTIPOLYGON,
    GEOMETRYCOLLECTION;

    public static String getValue(ContentType contentType) {
        if (contentType == CHAR) return "CHAR(64)";
        if (contentType == VARCHAR) return "VARCHAR(64)";
        return contentType.name().toUpperCase();
    }

}
