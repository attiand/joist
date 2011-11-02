package features.domain.builders;

import java.util.ArrayList;
import java.util.List;

import joist.domain.builders.AbstractBuilder;
import features.domain.ParentDChildC;
import features.domain.ParentDToChildC;

@SuppressWarnings("all")
public abstract class ParentDChildCBuilderCodegen extends AbstractBuilder<ParentDChildC> {

  public ParentDChildCBuilderCodegen(ParentDChildC instance) {
    super(instance);
  }

  public Long id() {
    return get().getId();
  }

  public ParentDChildCBuilder id(Long id) {
    get().setId(id);
    return (ParentDChildCBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public ParentDChildCBuilder name(String name) {
    get().setName(name);
    return (ParentDChildCBuilder) this;
  }

  public ParentDChildCBuilder with(String name) {
    get().setName(name);
    return (ParentDChildCBuilder) this;
  }

  public List<ParentDToChildCBuilder> parentDToChildCs() {
    List<ParentDToChildCBuilder> b = new ArrayList<ParentDToChildCBuilder>();
    for (ParentDToChildC e : get().getParentDToChildCs()) {
      b.add(Builders.existing(e));
    }
    return b;
  }

  public ParentDToChildCBuilder parentDToChildC(int i) {
    return Builders.existing(get().getParentDToChildCs().get(i));
  }

  public ParentDChildC get() {
    return (features.domain.ParentDChildC) super.get();
  }

}
