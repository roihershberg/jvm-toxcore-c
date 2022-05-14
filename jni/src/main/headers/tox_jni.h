#ifndef TOX4J_TOX_JNI_H
#define TOX4J_TOX_JNI_H

#include <jni.h>

namespace ToxJni {
    jint registerNatives(JNIEnv *env);

    jint getVersionMajor(JNIEnv *env, jclass clazz);

    jint getVersionMinor(JNIEnv *env, jclass clazz);

    jint getVersionPatch(JNIEnv *env, jclass clazz);

    jint getPublicKeySize(JNIEnv *env, jclass clazz);

    jint getSecretKeySize(JNIEnv *env, jclass clazz);

    jint getConferenceIdSize(JNIEnv *env, jclass clazz);

    jint getNospamSize(JNIEnv *env, jclass clazz);

    jint getAddressSize(JNIEnv *env, jclass clazz);

    jint getMaxNameLength(JNIEnv *env, jclass clazz);

    jint getMaxStatusMessageLength(JNIEnv *env, jclass clazz);

    jint getMaxFriendRequestLength(JNIEnv *env, jclass clazz);

    jint getMaxMessageLength(JNIEnv *env, jclass clazz);

    jint getMaxCustomPacketSize(JNIEnv *env, jclass clazz);

    jint getMaxFilenameLength(JNIEnv *env, jclass clazz);

    jint getMaxHostnameLength(JNIEnv *env, jclass clazz);
}

#endif //TOX4J_TOX_JNI_H
