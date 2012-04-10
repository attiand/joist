package features.domain.builders;

import features.domain.ManyToManyBBar;
import features.domain.ManyToManyBFooToBar;
import java.util.ArrayList;
import java.util.List;
import joist.domain.builders.AbstractBuilder;
import joist.domain.uow.UoW;

@SuppressWarnings("all")
public abstract class ManyToManyBBarBuilderCodegen extends AbstractBuilder<ManyToManyBBar> {

  public ManyToManyBBarBuilderCodegen(ManyToManyBBar instance) {
    super(instance);
  }

  public Long id() {
    if (UoW.isOpen() && get().getId() == null) {
      UoW.flush();
    }
    return get().getId();
  }

  public ManyToManyBBarBuilder id(Long id) {
    get().setId(id);
    return (ManyToManyBBarBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ManyToManyBBarBuilder name(String name) {
    get().setName(name);
    return (ManyToManyBBarBuilder) this;
  }

  public ManyToManyBBarBuilder with(String name) {
    return name(name);
  }

  @Override
  public ManyToManyBBarBuilder defaults() {
    if (name() == null) {
      name("name");
    }
    return (ManyToManyBBarBuilder) super.defaults();
  }

  public List<ManyToManyBFooToBarBuilder> greenManyToManyBFooToBars() {
    List<ManyToManyBFooToBarBuilder> b = new ArrayList<ManyToManyBFooToBarBuilder>();
    for (ManyToManyBFooToBar e : get().getGreenManyToManyBFooToBars()) {
      b.add(Builders.existing(e));
    }
    return b;
  }

  public ManyToManyBFooToBarBuilder greenManyToManyBFooToBar(int i) {
    return Builders.existing(get().getGreenManyToManyBFooToBars().get(i));
  }

  public ManyToManyBBar get() {
    return (features.domain.ManyToManyBBar) super.get();
  }

}
