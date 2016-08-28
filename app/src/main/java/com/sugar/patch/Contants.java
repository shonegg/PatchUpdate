package com.sugar.patch;

import android.os.Environment;

import java.io.File;

/**
 * Author   Shone
 * Date     28/08/16.
 * Github   https://github.com/shonegg
 */
public interface Contants {

    String rootDir = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "PatchCache" + File.separator;
    String downPatchPath = rootDir + "apk.patch";
    String newVersionPath = rootDir + "PatchUpdate_v_2_0.apk";
}
