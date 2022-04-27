package org.rick.calcite;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResolveColumnName {
    public static void main(String[] args) throws Exception {
        System.out.println(
                handleSql("select myudf(id,NAME) + udf1(age) as newf,name,age FROM stu where age<20"));
    }

    public static Set<String> handleSql(String sql) throws SqlParseException {
        Set<String> result = new HashSet<>();
        SqlParser.Config config = SqlParser.configBuilder()
                .setLex(Lex.JAVA).build();
        SqlParser sqlParser = SqlParser
                .create(sql, config);
        SqlSelect sqlNode = (SqlSelect) sqlParser.parseStmt();
        List<SqlNode> list = sqlNode.getSelectList().getList();
        for (SqlNode field : list) {
            Set<String> cols = new HashSet<>();
            handleField(field, cols);
            result.addAll(cols);
        }
        return result;
    }

    public static void handleField(SqlNode field, Set<String> cols) {
        SqlKind kind = field.getKind();
        switch (kind) {
            case AS:
                SqlNode[] operands_as = ((SqlBasicCall) field).operands;
                SqlNode left_as = operands_as[0];
                handleField(left_as, cols);
                break;
            case IDENTIFIER:
                //表示当前为子节点
                SqlIdentifier sqlIdentifier = (SqlIdentifier) field;
                System.out.println(sqlIdentifier);
                cols.add(sqlIdentifier.toString());
                break;
            default:
                if (field instanceof SqlBasicCall) {
                    SqlNode[] nodes = ((SqlBasicCall) field).operands;
                    for (int i = 0; i < nodes.length; i++) {
                        handleField(nodes[i], cols);
                    }
                }
                if (field instanceof SqlNodeList) {
                    for (SqlNode node : ((SqlNodeList) field).getList()) {
                        handleField(node, cols);
                    }
                }
                break;
        }
    }
}
