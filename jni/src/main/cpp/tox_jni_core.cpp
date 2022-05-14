#include <jni.h>

#include "tox_jni.h"

JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    jint result_code = ToxJni::registerNatives(env);
    if (result_code != JNI_OK) return result_code;

    return JNI_VERSION_1_6;
}
