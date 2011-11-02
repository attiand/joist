package features.domain.builders;

import joist.domain.builders.AbstractBuilder;
import features.domain.ParentD;
import features.domain.ParentDChildA;

@SuppressWarnings("all")
public abstract class ParentDChildABuilderCodegen extends AbstractBuilder<ParentDChildA> {

  public ParentDChildABuilderCodegen(ParentDChildA instance) {
    super(instance);
  }

  public Long id() {
    return get().getId();
  }

  public ParentDChildABuilder id(Long id) {
    get().setId(id);
    return (ParentDChildABuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentDChildABuilder name(String name) {
    get().setName(name);
    return (ParentDChildABuilder) this;
  }

  public ParentDChildABuilder with(String name) {
    get().setName(name);
    return (ParentDChildABuilder) this;
  }

  public ParentDBuilder parentD() {
    if (get().getParentD() == null) {
      return null;
    }
    return Builders.existing(get().getParentD());
  }

  public ParentDChildABuilder parentD(ParentD parentD) {
    get().setParentD(parentD);
    return (ParentDChildABuilder) this;
  }

  public ParentDChildABuilder with(ParentD parentD) {
    get().setParentD(parentD);
    return (ParentDChildABuilder) this;
  }

  public ParentDChildABuilder parentD(ParentDBuilder parentD) {
    get().setParentD(parentD.get());
    return (ParentDChildABuilder) this;
  }

  public ParentDChildABuilder with(ParentDBuilder parentD) {
    get().setParentD(parentD.get());
    return (ParentDChildABuilder) this;
  }

  public ParentDChildA get() {
    return (features.domain.ParentDChildA) super.get();
  }

}
