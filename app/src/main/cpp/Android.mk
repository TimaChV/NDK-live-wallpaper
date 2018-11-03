
LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_CPP_EXTENSION := .cpp .cxx
LOCAL_MODULE    := libnative-lib
LOCAL_CFLAGS    := -Werror -DKTX_OPENGL_ES2=1

LOCAL_SRC_FILES :=  native-lib.cpp \
                    gl_util.cpp \

				
LOCAL_LDLIBS    := -llog -lGLESv2 -landroid -lz # -lGLESv3 # -lEGL

LOCAL_STATIC_LIBRARIES := $(STATICLIBS)

LOCAL_CPP_FEATURES += rtti
LOCAL_CPPFLAGS += -std=c++11 

include $(BUILD_SHARED_LIBRARY)
