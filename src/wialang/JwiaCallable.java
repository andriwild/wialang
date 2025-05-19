package wialang;

import java.util.List;

interface JwiaCallable {
  int arity();
  Object call(Interpreter interpreter, List<Object> arguments);
}
