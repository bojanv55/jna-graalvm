package sample.jna.graalvm;

import com.sun.jna.Library;

public interface Kernelz32 extends Library {
  int GetTickCount();
}
