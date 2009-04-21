package features.domain;

import java.util.ArrayList;
import java.util.List;
import joist.domain.orm.queries.Alias;
import joist.domain.orm.queries.columns.AliasColumn;
import joist.domain.orm.queries.columns.ForeignKeyAliasColumn;
import joist.domain.orm.queries.columns.IdAliasColumn;
import joist.domain.orm.queries.columns.IntAliasColumn;
import joist.domain.orm.queries.columns.StringAliasColumn;

public class ParentBChildFooAlias extends Alias<ParentBChildFoo> {

    private final List<AliasColumn<ParentBChildFoo, ?, ?>> columns = new ArrayList<AliasColumn<ParentBChildFoo, ?, ?>>();
    public final IdAliasColumn<ParentBChildFoo> id = new IdAliasColumn<ParentBChildFoo>(this, "id", ParentBChildFooCodegen.Shims.id);
    public final StringAliasColumn<ParentBChildFoo> name = new StringAliasColumn<ParentBChildFoo>(this, "name", ParentBChildFooCodegen.Shims.name);
    public final IntAliasColumn<ParentBChildFoo> version = new IntAliasColumn<ParentBChildFoo>(this, "version", ParentBChildFooCodegen.Shims.version);
    public final ForeignKeyAliasColumn<ParentBChildFoo, ParentBParent> parentBParent = new ForeignKeyAliasColumn<ParentBChildFoo, ParentBParent>(this, "parent_b_parent_id", ParentBChildFooCodegen.Shims.parentBParentId);

    public ParentBChildFooAlias(String alias) {
        super(ParentBChildFoo.class, "parent_b_child_foo", alias);
        this.columns.add(this.id);
        this.columns.add(this.name);
        this.columns.add(this.version);
        this.columns.add(this.parentBParent);
    }

    public List<AliasColumn<ParentBChildFoo, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<ParentBChildFoo> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<ParentBChildFoo> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<ParentBChildFoo> getSubClassIdColumn() {
        return null;
    }

    public int getOrder() {
        return 24;
    }

}
