package org.rick.calcite;

import org.apache.calcite.config.CalciteConnectionConfigImpl;
import org.apache.calcite.config.Lex;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.prepare.CalciteCatalogReader;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.rel.type.RelDataTypeSystemImpl;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.impl.AbstractTable;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.SqlParserPos;
import org.apache.calcite.sql.type.BasicSqlType;
import org.apache.calcite.sql.type.SqlTypeFactoryImpl;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.tools.Frameworks;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

public class SqlNodeTest {

    private SqlParser.Config config = SqlParser.configBuilder()
            .setLex(Lex.MYSQL_ANSI) //使用mysql 语法
            .build();
    //SqlParser 语法解析器
    private SqlParser sqlParser = null;
    private SqlNode sqlNode = null;

    @Test
    public void testSqlSelect() {
        try {
            sqlParser = SqlParser
                    .create("select id,name,age FROM stu where age<20", config);
            sqlNode = sqlParser.parseStmt();
            System.out.println(sqlNode + "\n");
            System.out.println(sqlNode.getKind() + "\n");

            if(SqlKind.SELECT.equals(sqlNode.getKind())){
                SqlSelect sqlSelect = (SqlSelect) sqlNode;
                SqlNode from=sqlSelect.getFrom();
                SqlNode where=sqlSelect.getWhere();
                SqlNodeList selectList=sqlSelect.getSelectList();
                //标识符
                if(SqlKind.IDENTIFIER.equals(from.getKind())){
                    System.out.println("查的表:" + from.toString());
                }

                if(SqlKind.LESS_THAN.equals(where.getKind())){
                    SqlBasicCall sqlBasicCall=(SqlBasicCall)where;
                    Arrays.stream(sqlBasicCall.getOperandList().toArray(new SqlNode[0]))
                            .filter(x -> SqlKind.LITERAL.equals(x.getKind()))
                            .forEach(x -> System.out.println("过滤小于literal的值:" + x.toString()));
                }

                System.out.println("选择列");
                selectList.getList().forEach(x->{
                    if(SqlKind.IDENTIFIER.equals(x.getKind())){
                        System.out.print(x.toString() + ",");
                    }
                });
            }
        } catch (SqlParseException e) {
            throw new RuntimeException("", e);
        }
    }

    @Test
    public void testWhere() throws SqlParseException {
        sqlParser = SqlParser
                .create("select id,count(1) cnt FROM stu where age>20 or id=10 and a=10 and b=10 group by id", config);
        sqlNode = sqlParser.parseStmt();
        System.out.println(sqlNode + "\n");

        if(sqlNode.getKind().equals(SqlKind.SELECT)){
            SqlSelect sqlSelect = (SqlSelect)sqlNode;
            SqlNode where = sqlSelect.getWhere();
            System.out.println("where的类型：" + where.getKind());
            SqlBasicCall sqlBasicCall=(SqlBasicCall)where;
            Arrays.stream(sqlBasicCall.getOperandList().toArray(new SqlNode[0]))
                    .forEach(x -> System.out.println(x + "\t >>>" + x.getKind().toString()));
//                    .filter(x -> SqlKind.LITERAL.equals(x.getKind()))
//                    .forEach(x -> System.out.println("过滤小于literal的值:" + x.toString()));
        }
    }

    @Test
    public void testSqlToRelConverter() throws SqlParseException {
        SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        rootSchema.add("USERS", new AbstractTable() { //note: add a table
            @Override
            public RelDataType getRowType(final RelDataTypeFactory typeFactory) {
                RelDataTypeFactory.Builder builder = typeFactory.builder();

                builder.add("ID", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.INTEGER));
                builder.add("NAME", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.CHAR));
                builder.add("AGE", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.INTEGER));
                return builder.build();
            }
        });

        rootSchema.add("JOBS", new AbstractTable() {
            @Override
            public RelDataType getRowType(final RelDataTypeFactory typeFactory) {
                RelDataTypeFactory.Builder builder = typeFactory.builder();

                builder.add("ID", new BasicSqlType(RelDataTypeSystemImpl.DEFAULT, SqlTypeName.INTEGER));
                builder.add("NAME", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.CHAR));
                builder.add("COMPANY", new BasicSqlType(new RelDataTypeSystemImpl() {}, SqlTypeName.CHAR));
                return builder.build();
            }
        });

        String sql = "select id from USERS";
        SqlParser parser = SqlParser.create(sql, SqlParser.Config.DEFAULT);
        SqlNode sqlNode = parser.parseStmt();

        //note: 二、sql validate（会先通过Catalog读取获取相应的metadata和namespace）
//note: get metadata and namespace
        SqlTypeFactoryImpl factory = new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
        CalciteCatalogReader calciteCatalogReader = new CalciteCatalogReader(
                CalciteSchema.from(rootSchema),
                CalciteSchema.from(rootSchema).path(null),
                factory,
                new CalciteConnectionConfigImpl(new Properties()));

//note: 校验（包括对表名，字段名，函数名，字段类型的校验。）
//        SqlValidator validator = SqlValidatorUtil.newValidator(SqlStdOperatorTable.instance(), calciteCatalogReader, factory,
//                conformance(frameworkConfig));
//        SqlNode validateSqlNode = validator.validate(sqlNode);

    }

    @Test
    public void testSqlOperator(){
        SqlOperator operator = new SqlAsOperator();
        SqlParserPos sqlParserPos = new SqlParserPos(1, 1);
        SqlIdentifier name = new SqlIdentifier("orders", null, sqlParserPos);
        SqlIdentifier alias = new SqlIdentifier("o", null, sqlParserPos);
        SqlNode[] sqlNodes = new SqlNode[2];
        sqlNodes[0] = name;
        sqlNodes[1] = alias;
        SqlBasicCall sqlBasicCall = (SqlBasicCall)operator.createCall(sqlParserPos,sqlNodes);
        System.out.println(sqlBasicCall); //得到的就是 Order as o
    }

}
