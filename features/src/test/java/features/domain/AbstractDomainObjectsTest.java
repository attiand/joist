package features.domain;

import joist.domain.DomainObject;
import joist.domain.orm.NamedUpdater;
import joist.domain.uow.UoW;
import joist.util.TestCounters;
import junit.framework.TestCase;

public abstract class AbstractDomainObjectsTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
        TestCounters.resetAll();
        // Protect against previous tests that didn't clean up
        if (UoW.isOpen()) {
            UoW.close();
        }
        UoW.open(new NamedUpdater("testing"));
    }

    public void tearDown() throws Exception {
        if (UoW.isOpen()) {
            UoW.close();
        }
        super.tearDown();
    }

    protected void commitAndReOpen() {
        UoW.commitAndReOpen();
    }

    protected void rollback() {
        UoW.rollback();
    }

    protected void flush() {
        UoW.flush();
    }

    protected <T extends DomainObject> T reload(T instance) {
        return (T) UoW.load(instance.getClass(), instance.getId());
    }

}