package com.kpa.player.source

import androidx.annotation.IntDef
import com.kpa.player.utils.ExtraObject
import java.io.Serializable

/**
 *
 * @author: kpa
 * @date: 2023/12/22
 * @description: 媒体创建类，可以按照次类进行扩展
 */
class TCMSMediaSource : ExtraObject(), Serializable {

    companion object {
        @IntDef(
            MEDIA_PROTOCOL_DEFAULT,
            MEDIA_PROTOCOL_DASH,
            MEDIA_PROTOCOL_HLS
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class MediaProtocol

        /**
         * 默认媒体协议类型，例如 mp4/flv 和其他流媒体。
         *
         * @see .setMediaProtocol
         * @see .getMediaProtocol
         */
        const val MEDIA_PROTOCOL_DEFAULT = 0

        /**
         * DASH媒体协议类型。与[.SOURCE_TYPE_ID]配合使用。
         */
        const val MEDIA_PROTOCOL_DASH = 1

        /**
         * HLS媒体协议类型。与[.SOURCE_TYPE_ID]配合使用。
         */
        const val MEDIA_PROTOCOL_HLS = 2

    }

}