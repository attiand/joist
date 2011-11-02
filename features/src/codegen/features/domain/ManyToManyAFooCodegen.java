package features.domain;

import java.util.ArrayList;
import java.util.List;

import joist.domain.AbstractChanged;
import joist.domain.AbstractDomainObject;
import joist.domain.Changed;
import joist.domain.Shim;
import joist.domain.orm.ForeignKeyListHolder;
import joist.domain.uow.UoW;
import joist.domain.validation.rules.MaxLength;
import joist.domain.validation.rules.NotNull;
import joist.util.Copy;
import features.domain.queries.ManyToManyAFooQueries;

@SuppressWarnings("all")
public abstract class ManyToManyAFooCodegen extends AbstractDomainObject {

  public static final ManyToManyAFooQueries queries;
  private Long id = null;
  private String name = null;
  private Long version = null;
  private ForeignKeyListHolder<ManyToManyAFoo, ManyToManyAFooToBar> manyToManyAFooToBars = new ForeignKeyListHolder<ManyToManyAFoo, ManyToManyAFooToBar>((ManyToManyAFoo) this, Aliases.manyToManyAFooToBar(), Aliases.manyToManyAFooToBar().manyToManyAFoo);
  protected Changed changed;

  static {
    Aliases.manyToManyAFoo();
    queries = new ManyToManyAFooQueries();
  }

  protected ManyToManyAFooCodegen() {
    this.addExtraRules();
  }

  private void addExtraRules() {
    this.addRule(new NotNull<ManyToManyAFoo>(Shims.name));
    this.addRule(new MaxLength<ManyToManyAFoo>(Shims.name, 100));
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.getChanged().record("id", this.id, id);
    this.id = id;
    if (UoW.isOpen()) {
      UoW.getIdentityMap().store(this);
    }
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.getChanged().record("name", this.name, name);
    this.name = name;
  }

  protected void defaultName(String name) {
    this.name = name;
  }

  public Long getVersion() {
    return this.version;
  }

  public List<ManyToManyAFooToBar> getManyToManyAFooToBars() {
    return this.manyToManyAFooToBars.get();
  }

  public void setManyToManyAFooToBars(List<ManyToManyAFooToBar> manyToManyAFooToBars) {
    for (ManyToManyAFooToBar o : Copy.list(this.getManyToManyAFooToBars())) {
      this.removeManyToManyAFooToBar(o);
    }
    for (ManyToManyAFooToBar o : manyToManyAFooToBars) {
      this.addManyToManyAFooToBar(o);
    }
  }

  public void addManyToManyAFooToBar(ManyToManyAFooToBar o) {
    o.setManyToManyAFooWithoutPercolation((ManyToManyAFoo) this);
    this.addManyToManyAFooToBarWithoutPercolation(o);
  }

  public void removeManyToManyAFooToBar(ManyToManyAFooToBar o) {
    o.setManyToManyAFooWithoutPercolation(null);
    this.removeManyToManyAFooToBarWithoutPercolation(o);
  }

  protected void addManyToManyAFooToBarWithoutPercolation(ManyToManyAFooToBar o) {
    this.getChanged().record("manyToManyAFooToBars");
    this.manyToManyAFooToBars.add(o);
  }

  protected void removeManyToManyAFooToBarWithoutPercolation(ManyToManyAFooToBar o) {
    this.getChanged().record("manyToManyAFooToBars");
    this.manyToManyAFooToBars.remove(o);
  }

  public List<ManyToManyABar> getManyToManyABars() {
    List<ManyToManyABar> l = new ArrayList<ManyToManyABar>();
    for (ManyToManyAFooToBar o : this.getManyToManyAFooToBars()) {
      l.add(o.getManyToManyABar());
    }
    return l;
  }

  public void setManyToManyABars(List<ManyToManyABar> manyToManyABars) {
    for (ManyToManyABar o : Copy.list(this.getManyToManyABars())) {
      this.removeManyToManyABar(o);
    }
    for (ManyToManyABar o : manyToManyABars) {
      this.addManyToManyABar(o);
    }
  }

  public void addManyToManyABar(ManyToManyABar o) {
    ManyToManyAFooToBar a = new ManyToManyAFooToBar();
    a.setManyToManyAFoo((ManyToManyAFoo) this);
    a.setManyToManyABar(o);
  }

  public void removeManyToManyABar(ManyToManyABar o) {
    for (ManyToManyAFooToBar a : Copy.list(this.getManyToManyAFooToBars())) {
      if (a.getManyToManyABar().equals(o)) {
        a.setManyToManyABar(null);
        a.setManyToManyAFoo(null);
        if (UoW.isOpen()) {
          UoW.delete(a);
        }
      }
    }
  }

  public ManyToManyAFooChanged getChanged() {
    if (this.changed == null) {
      this.changed = new ManyToManyAFooChanged((ManyToManyAFoo) this);
    }
    return (ManyToManyAFooChanged) this.changed;
  }

  @Override
  public void clearAssociations() {
    super.clearAssociations();
    for (ManyToManyAFooToBar o : Copy.list(this.getManyToManyAFooToBars())) {
      o.setManyToManyAFooWithoutPercolation(null);
    }
  }

  static class Shims {
    protected static final Shim<ManyToManyAFoo, Long> id = new Shim<ManyToManyAFoo, Long>() {
      public void set(ManyToManyAFoo instance, Long id) {
        ((ManyToManyAFooCodegen) instance).id = id;
      }
      public Long get(ManyToManyAFoo instance) {
        return ((ManyToManyAFooCodegen) instance).id;
      }
      public String getName() {
        return "id";
      }
    };
    protected static final Shim<ManyToManyAFoo, String> name = new Shim<ManyToManyAFoo, String>() {
      public void set(ManyToManyAFoo instance, String name) {
        ((ManyToManyAFooCodegen) instance).name = name;
      }
      public String get(ManyToManyAFoo instance) {
        return ((ManyToManyAFooCodegen) instance).name;
      }
      public String getName() {
        return "name";
      }
    };
    protected static final Shim<ManyToManyAFoo, Long> version = new Shim<ManyToManyAFoo, Long>() {
      public void set(ManyToManyAFoo instance, Long version) {
        ((ManyToManyAFooCodegen) instance).version = version;
      }
      public Long get(ManyToManyAFoo instance) {
        return ((ManyToManyAFooCodegen) instance).version;
      }
      public String getName() {
        return "version";
      }
    };
  }

  public static class ManyToManyAFooChanged extends AbstractChanged {
    public ManyToManyAFooChanged(ManyToManyAFoo instance) {
      super(instance);
    }
    public boolean hasId() {
      return this.contains("id");
    }
    public Long getOriginalId() {
      return (Long) this.getOriginal("id");
    }
    public boolean hasName() {
      return this.contains("name");
    }
    public String getOriginalName() {
      return (java.lang.String) this.getOriginal("name");
    }
    public boolean hasVersion() {
      return this.contains("version");
    }
    public Long getOriginalVersion() {
      return (Long) this.getOriginal("version");
    }
    public boolean hasManyToManyAFooToBars() {
      return this.contains("manyToManyAFooToBars");
    }
  }

}
