#include "tox_jni.h"
#include <tox/tox.h>

namespace ToxJni {
    jint registerNatives(JNIEnv *env) {
        jclass clazz = env->FindClass("ltd/toktok/tox4j/Tox");
        if (clazz == nullptr) return JNI_ERR;

        static const JNINativeMethod methods[] = {
                {"getVersionMajor",           "()I", reinterpret_cast<void *>(getVersionMajor)},
                {"getVersionMinor",           "()I", reinterpret_cast<void *>(getVersionMinor)},
                {"getVersionPatch",           "()I", reinterpret_cast<void *>(getVersionPatch)},
                {"getPublicKeySize",          "()I", reinterpret_cast<void *>(getPublicKeySize)},
                {"getSecretKeySize",          "()I", reinterpret_cast<void *>(getSecretKeySize)},
                {"getConferenceIdSize",       "()I", reinterpret_cast<void *>(getConferenceIdSize)},
                {"getNospamSize",             "()I", reinterpret_cast<void *>(getNospamSize)},
                {"getAddressSize",            "()I", reinterpret_cast<void *>(getAddressSize)},
                {"getMaxNameLength",          "()I", reinterpret_cast<void *>(getMaxNameLength)},
                {"getMaxStatusMessageLength", "()I", reinterpret_cast<void *>(getMaxStatusMessageLength)},
                {"getMaxFriendRequestLength", "()I", reinterpret_cast<void *>(getMaxFriendRequestLength)},
                {"getMaxMessageLength",       "()I", reinterpret_cast<void *>(getMaxMessageLength)},
                {"getMaxCustomPacketSize",    "()I", reinterpret_cast<void *>(getMaxCustomPacketSize)},
                {"getMaxFilenameLength",      "()I", reinterpret_cast<void *>(getMaxFilenameLength)},
                {"getMaxHostnameLength",      "()I", reinterpret_cast<void *>(getMaxHostnameLength)},
        };

        return env->RegisterNatives(clazz, methods, sizeof(methods) / sizeof(JNINativeMethod));
    }

    jint getVersionMajor(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_version_major());
    }

    jint getVersionMinor(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_version_minor());
    }

    jint getVersionPatch(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_version_patch());
    }

    jint getPublicKeySize(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_public_key_size());
    }

    jint getSecretKeySize(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_secret_key_size());
    }

    jint getConferenceIdSize(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_conference_id_size());
    }

    jint getNospamSize(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_nospam_size());
    }

    jint getAddressSize(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_address_size());
    }

    jint getMaxNameLength(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_max_name_length());
    }

    jint getMaxStatusMessageLength(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_max_status_message_length());
    }

    jint getMaxFriendRequestLength(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_max_friend_request_length());
    }

    jint getMaxMessageLength(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_max_message_length());
    }

    jint getMaxCustomPacketSize(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_max_custom_packet_size());
    }

    jint getMaxFilenameLength(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_max_filename_length());
    }

    jint getMaxHostnameLength(JNIEnv *env, jclass clazz) {
        return static_cast<jint>(tox_max_hostname_length());
    }
}
