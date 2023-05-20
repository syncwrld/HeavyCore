package com.redeheavy.heavycore.commons.database.objects;

import com.redeheavy.heavycore.commons.database.enums.ContentType;
import lombok.Getter;

import java.util.List;

public class MySQLTable {

    @Getter
    private String tableName;
    @Getter
    private List<TableContent> contentList;
    @Getter
    private TableContent content;

    public MySQLTable(String tableName, TableContent content) {
        this.tableName = (tableName);
        this.content = (content);
    }

    public MySQLTable(String tableName, List<TableContent> contentList) {
        this.tableName = (tableName);
        this.contentList = (contentList);
    }

    public static String getQuery(MySQLTable table) {
        String BASE_SQL = "CREATE TABLE IF NOT EXISTS " + table.getTableName() + " (";

        if ((table.getContentList() != null) && !table.getContentList().isEmpty()) {

            for (int i = 0; i < table.getContentList().size(); i++) {

                TableContent value = table.getContentList().get(i);

                String IS_NULLABLE = value.isNullable() ? "".trim() : " NOT NULL ";
                String IS_PRIMARY = value.isPrimary() ? "PRIMARY KEY" : "".trim();
                String VALUE_SQL;

                if (i < table.getContentList().size() - 1) {
                    VALUE_SQL = value.getKey() + " " + ContentType.getValue(value.getContentType()) + IS_NULLABLE + IS_PRIMARY + ", ";
                } else {
                    VALUE_SQL = value.getKey() + " " + ContentType.getValue(value.getContentType()) + IS_NULLABLE + IS_PRIMARY + ");";
                }

                BASE_SQL = BASE_SQL + (VALUE_SQL);
            }
        } else {
            TableContent value = table.getContent();
            String IS_NULLABLE = value.isNullable() ? "".trim() : " NOT NULL ";
            String IS_PRIMARY = value.isPrimary() ? "PRIMARY KEY" : "".trim();

            BASE_SQL = BASE_SQL + value.getKey() + " " + ContentType.getValue(value.getContentType()) + IS_NULLABLE + IS_PRIMARY + ");";
        }
        return BASE_SQL;
    }

}
