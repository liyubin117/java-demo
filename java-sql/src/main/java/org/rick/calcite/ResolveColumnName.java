package org.rick.calcite;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParser;

import java.util.List;

public class ResolveColumnName {
    public static void main(String[] args) throws Exception {
        SqlParser.Config config = SqlParser.configBuilder()
                .setLex(Lex.JAVA).build();
        SqlParser sqlParser = SqlParser
                .create("select myudf(id,NAME) + udf1(age) as newf,name,age FROM stu where age<20", config);
        SqlSelect sqlNode = (SqlSelect) sqlParser.parseStmt();
        List<SqlNode> list = sqlNode.getSelectList().getList();
        System.out.println(list);
        list.forEach(e->{
            handlerField(e);
        });
    }


    private static void handlerField(SqlNode field) {
        SqlKind kind = field.getKind();
        switch (kind) {
            case AS:
                SqlNode[] operands_as = ((SqlBasicCall) field).operands;
                SqlNode left_as = operands_as[0];
                handlerField(left_as);
                break;
            case IDENTIFIER:
                //表示当前为子节点
                SqlIdentifier sqlIdentifier = (SqlIdentifier) field;
                System.out.println("===field===" + sqlIdentifier.toString());
                break;
            default:
                if (field instanceof SqlBasicCall) {
                    SqlNode[] nodes = ((SqlBasicCall) field).operands;
                    for (int i = 0; i < nodes.length; i++) {
                        handlerField(nodes[i]);
                    }
                }
                if (field instanceof SqlNodeList) {
                    ((SqlNodeList) field).getList().forEach(node -> {
                        handlerField(node);
                    });
                }
                break;
        }
    }
}
