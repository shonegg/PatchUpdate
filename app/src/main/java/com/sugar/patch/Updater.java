package com.sugar.patch;

/**
 * Author   Shone
 * Date     27/08/16.
 * Github   https://github.com/shonegg
 */
public class Updater {

   static {
       System.loadLibrary("bspatch");
   }

    public static native void applyPatch(String oldPath, String newPath, String patchPath);

}
