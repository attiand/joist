package features.domain;

import junit.framework.Assert;

import org.exigencecorp.domainobjects.Ids;

public class PrimitivesTest extends AbstractFeaturesTest {

    public void testSave() {
        Primitives foo = new Primitives();
        foo.setName("testSave");
        this.commitAndReOpen();
        Assert.assertEquals(2, foo.getId().intValue());

        Primitives reloaded = Primitives.queries.find(2);
        Assert.assertEquals(2, reloaded.getId().intValue());
        Assert.assertEquals("testSave", reloaded.getName());
    }

    public void testFlushMeansWeCanFindItRightAway() {
        Primitives foo = new Primitives();
        foo.setName("testSave");
        this.flush();

        Primitives reloaded = Primitives.queries.find(2);
        Assert.assertEquals("testSave", reloaded.getName());
    }

    public void testRollbackAfterFlushMeansItIsNotThere() {
        Primitives foo = new Primitives();
        foo.setName("testSave");
        this.flush();
        this.rollback();

        try {
            Primitives.queries.find(2);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Not found", e.getMessage());
        }
    }

    public void testSaveTwoInSameUowGetDifferentIds() {
        new Primitives().setName("foo");
        new Primitives().setName("bar");
        this.commitAndReOpen();
        Assert.assertEquals(2, Primitives.queries.findByName("foo").getId().intValue());
        Assert.assertEquals(3, Primitives.queries.findByName("bar").getId().intValue());
    }

    public void testLoadViaIdTwiceReturnsTheSameInstance() {
        new Primitives().setName("foo");
        this.commitAndReOpen();
        Primitives twp1 = Primitives.queries.find(2);
        Primitives twp2 = Primitives.queries.find(2);
        Assert.assertTrue(twp1 == twp2);
    }

    public void testLoadViaIdThenNameReturnsTheSameInstance() {
        new Primitives("foo");
        this.commitAndReOpen();
        Primitives twp1 = Primitives.queries.find(2);
        Primitives twp2 = Primitives.queries.findByName("foo");
        Assert.assertTrue(twp1 == twp2);
    }

    public void testUpdateTicksVersion() {
        new Primitives().setName("foo");
        this.commitAndReOpen();

        Primitives twp = Primitives.queries.find(2);
        Assert.assertEquals(0, twp.getVersion().intValue());
        twp.setName("bar");
        this.commitAndReOpen();
        // Should already see it tick
        Assert.assertEquals(1, twp.getVersion().intValue());

        // And after reloading
        twp = this.reload(twp);
        Assert.assertEquals(1, twp.getVersion().intValue());
    }

    public void testFindAllIds() {
        new Primitives("foo1");
        new Primitives("foo2");
        this.commitAndReOpen();
        Ids<Primitives> ids = Primitives.queries.findAllIds();
        Assert.assertEquals(2, ids.getIds().size());
        Assert.assertEquals(2, ids.getIds().get(0).intValue());
        Assert.assertEquals(3, ids.getIds().get(1).intValue());
    }

}
