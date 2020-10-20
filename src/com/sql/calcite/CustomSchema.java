package com.sql.calcite;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import org.apache.calcite.linq4j.tree.Expression;
import org.apache.calcite.rel.type.RelProtoDataType;
import org.apache.calcite.schema.*;
import org.apache.calcite.schema.impl.AbstractSchema;
import org.apache.calcite.util.Source;
import org.apache.calcite.util.Sources;
 
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Set;
 
/**
 * 类似数据库，Schema表示数据库
 * */
public class CustomSchema extends AbstractSchema {
    private Map<String, Table> tableMap;
 
    @Override
    protected Map<String, Table> getTableMap() {
        URL url = CustomSchema.class.getResource("/data.csv");
        Source source = Sources.of(url);
        if (tableMap == null) {
            final ImmutableMap.Builder<String, Table> builder = ImmutableMap.builder();
            builder.put("TEST01",new CustomTable(source));    // 一个数据库有多个表名，这里初始化，大小写要注意了,TEST01是表名。
            tableMap = builder.build();
        }
        return tableMap;
    }
}