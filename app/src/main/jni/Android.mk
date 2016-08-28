LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

# This is the target being built.
LOCAL_MODULE:= libbspatch


# All of the source files that we will compile.
LOCAL_SRC_FILES:= \
      patch.c


# No static libraries.
LOCAL_STATIC_LIBRARIES := \
         lbz2


# Also need the JNI headers.
LOCAL_C_INCLUDES += \
        $(JNI_H_INCLUDE) bzip2

# No special compiler flags.
LOCAL_CFLAGS +=

include $(BUILD_SHARED_LIBRARY)