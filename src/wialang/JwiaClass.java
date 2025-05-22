package wialang;

import java.util.List;
import java.util.Map;

class JwiaClass implements JwiaCallable {
  final String name;
  final JwiaClass superclass;
  private final Map<String, JwiaFunction> methods;

  JwiaClass(String name, JwiaClass superclass , Map<String, JwiaFunction> methods) {
    this.name = name;
    this.superclass = superclass;
    this.methods = methods;
  }

  JwiaFunction findMethod(String name) {
    if (methods.containsKey(name)) {
      return methods.get(name);
    }

   if (superclass != null) {
      return superclass.findMethod(name);
    }

    return null;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public Object call(Interpreter interpreter,
                     List<Object> arguments) {
    JwiaInstance instance = new JwiaInstance(this);
    JwiaFunction initializer = findMethod("init");
    if (initializer != null) {
      initializer.bind(instance).call(interpreter, arguments);
    }
    return instance;
  }

  @Override
  public int arity() {
    JwiaFunction initializer = findMethod("init");
    if (initializer == null) return 0;
    return initializer.arity();
  }
}