package features.domain.queries;

import static features.domain.builders.Builders.aChild;

import java.util.List;

import joist.domain.orm.queries.Select;
import joist.domain.orm.queries.columns.Aggregate;

import org.junit.Assert;
import org.junit.Test;

import features.domain.AbstractFeaturesTest;
import features.domain.Child;
import features.domain.ChildAlias;
import features.domain.builders.ChildBuilder;

public class DomainObjectGroupByTest extends AbstractFeaturesTest {

  @Test
  public void testGroupByWithOneResult() {
    ChildBuilder child = aChild().defaults();
    aChild().with(child.parent()).defaults();
    this.commitAndReOpen();

    ChildAlias c = new ChildAlias("c");
    Select<Child> q = Select.from(c);
    q.select(c.parent.as("parentId"));
    q.groupBy(c.parent);
    List<ByParent> l = q.list(ByParent.class);
    Assert.assertEquals(1, l.size());
    Assert.assertEquals(1L, l.get(0).parentId.longValue());
  }

  @Test
  public void testGroupByWithTwoResults() {
    aChild().defaults();
    aChild().defaults();
    this.commitAndReOpen();

    ChildAlias c = new ChildAlias("c");
    Select<Child> q = Select.from(c);
    q.select(c.parent.as("parentId"));
    q.groupBy(c.parent);
    List<ByParent> l = q.list(ByParent.class);

    Assert.assertEquals(2, l.size()); // now two
    Assert.assertEquals(1L, l.get(0).parentId.longValue());
    Assert.assertEquals(2L, l.get(1).parentId.longValue());
  }

  @Test
  public void testHavingWithTwoResultsAndOneFiltered() {
    ChildBuilder child = aChild().defaults();
    aChild().with(child.parent()).defaults();
    aChild().defaults();
    this.commitAndReOpen();

    ChildAlias c = new ChildAlias("c");
    Select<Child> q = Select.from(c);
    q.select(c.parent.as("parentId"));
    q.groupBy(c.parent);
    q.having(Aggregate.count().greaterThan(1L));
    List<ByParent> l = q.list(ByParent.class);

    Assert.assertEquals(1, l.size()); // back to one
    Assert.assertEquals(1L, l.get(0).parentId.longValue());
  }

  @Deprecated
  @Test
  public void testHavingWithTwoResultsAndOneFilteredWithDeprecatedSelectCountBuilder() {
    ChildBuilder child = aChild().defaults();
    aChild().with(child.parent()).defaults();
    aChild().defaults();
    this.commitAndReOpen();

    ChildAlias c = new ChildAlias("c");
    Select<Child> q = Select.from(c);
    q.select(c.parent.as("parentId"));
    q.groupBy(c.parent);
    q.having(q.count.greatherThan(1));
    List<ByParent> l = q.list(ByParent.class);

    Assert.assertEquals(1, l.size()); // back to one
    Assert.assertEquals(1L, l.get(0).parentId.longValue());
  }

  public void testGroupByWithSumInSelect() {
    aChild().defaults();
    ChildBuilder child = aChild().defaults();
    aChild().with(child.parent()).defaults();
    this.commitAndReOpen();

    ChildAlias c = new ChildAlias("c");
    Select<Child> q = Select.from(c);
    // Summing ID's is hokey, but it fulfills the function of needing something to sum
    q.select(c.parent.as("parentId"), Aggregate.sum(c.id).as("sum"));
    q.groupBy(c.parent);
    q.orderBy(c.parent.asc());
    List<ByParent> l = q.list(ByParent.class);
    Assert.assertEquals(2, l.size());
    Assert.assertEquals(1L, l.get(0).parentId.longValue());
    Assert.assertEquals(1L, l.get(0).sum.intValue());
    Assert.assertEquals(2L, l.get(1).parentId.longValue());
    Assert.assertEquals(5L, l.get(1).sum.intValue());
  }

  public static class ByParent {
    public Long parentId;
    public Long count;
    public Long sum;
  }
}
