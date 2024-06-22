package org.rick.calcite;

import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.RelBuilder;
import org.junit.Test;

public class RelNodeTest {
    @Test
    public void testPlan() {
        final FrameworkConfig config = Frameworks.newConfigBuilder()
                .build();
        final RelBuilder builder = RelBuilder.create(config);
        final RelNode node = builder
                .project(builder.field("Name"),builder.field("Score"))
                .filter(builder.call(SqlStdOperatorTable.GREATER_THAN,
                        builder.field("Score"),
                        builder.literal(90)))
                .build();
        System.out.println(RelOptUtil.toString(node));
    }
}
