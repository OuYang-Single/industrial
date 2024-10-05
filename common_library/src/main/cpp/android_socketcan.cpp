#include <jni.h>
#include <linux/if.h>
#include <linux/can.h>

#include <sys/ioctl.h>

#include <zconf.h>
#include <string.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_ijcsj_common_1library_can_Socketcan_OpenCan(JNIEnv *env, jclass clazz, jstring canx) {
    int fd;
    struct ifreq ifr;
    struct sockaddr_can addr;

    /* open socket */
    if ((fd = socket(PF_CAN, SOCK_RAW, CAN_RAW)) < 0) {
        return -1;
    }

    const char *str = env->GetStringUTFChars(canx, 0);
    strcpy(ifr.ifr_name, str);
    ioctl(fd, SIOCGIFINDEX, &ifr);

    memset(&addr, 0, sizeof(addr));
    addr.can_family = AF_CAN;
    addr.can_ifindex = ifr.ifr_ifindex;

    if (bind(fd, (struct sockaddr *)&addr, sizeof(addr)) < 0) {
        return -2;
    }

    return fd;
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_ijcsj_common_1library_can_Socketcan_CanWrite(JNIEnv *env, jclass clazz, jint fd,
                                                      jint can_id, jbyteArray data) {
    // 获取 JNI 数据
    jbyte *pdata = env->GetByteArrayElements(data, NULL);
    jsize data_len = env->GetArrayLength(data);

    // 确保数据长度不超过 CAN 最大数据长度
    if (data_len > 8) {
        data_len = 8; // CAN 数据长度最大为 8 字节
    }

    // 创建 CAN 帧
    struct can_frame frame;
    frame.can_id = can_id;
    frame.can_dlc = data_len;

    // 将数据复制到 CAN 帧中
    for (jsize i = 0; i < data_len; i++) {
        frame.data[i] = pdata[i] & 0xFF;
    }
    // 将剩余的数据部分置为 0（如果数据长度小于 8）
    for (jsize i = data_len; i < 8; i++) {
        frame.data[i] = 0;
    }

    // 释放 JNI 数据
    env->ReleaseByteArrayElements(data, pdata, 0);

    // 写入 CAN 总线
    int ret = write(fd, &frame, sizeof(struct can_frame));
    return ret;
}
extern "C"
JNIEXPORT jobject JNICALL
Java_com_ijcsj_common_1library_can_Socketcan_CanRead(JNIEnv *env, jclass clazz, jobject mcanFrame,
                                                     jint time) {
    struct can_frame frame;
    read(time, &frame, sizeof(struct can_frame));

    jclass canFrameClass = env->GetObjectClass(mcanFrame);

    jfieldID canIdField = env->GetFieldID(canFrameClass, "can_id", "I");
    jfieldID canDlcField = env->GetFieldID(canFrameClass, "can_dlc", "C");
    jfieldID dataField = env->GetFieldID(canFrameClass, "data", "[B");

    env->SetIntField(mcanFrame, canIdField, static_cast<jint>(frame.can_id));
    env->SetCharField(mcanFrame, canDlcField, frame.can_dlc);

    jbyteArray dataArray = env->NewByteArray(frame.can_dlc);
    env->SetByteArrayRegion(dataArray, 0, frame.can_dlc, (jbyte *)frame.data);
    env->SetObjectField(mcanFrame, dataField, dataArray);

    return mcanFrame;
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_ijcsj_common_1library_can_Socketcan_CloseCan(JNIEnv *env, jclass clazz, jint fd) {
    return close(fd);
}