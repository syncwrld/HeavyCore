package com.redeheavy.heavycore.commons.database.objects;

import com.redeheavy.heavycore.commons.database.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TableContent {

    public ContentType contentType;
    private String key;
    private boolean nullable;
    private boolean primary;

}