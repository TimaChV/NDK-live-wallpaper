//
// Created by tima on 03.11.2018.
//

#ifndef LWP_TEST_GL_UTIL_H
#define LWP_TEST_GL_UTIL_H

#include <android/log.h>
#include <GLES2/gl2.h>
#include <stdio.h>
#include <stdlib.h>

#define  LOG_TAG    "lwp"
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

GLuint loadShader(GLenum shaderType, const char* pSource);

GLuint createProgram(const char* pVertexSource, const char* pFragmentSource);

void checkGlError(const char* op);

void printGLString(const char *name, GLenum s);

#endif //LWP_TEST_GL_UTIL_H
