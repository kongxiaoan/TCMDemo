package com.kpa.player

import androidx.annotation.IntDef
import androidx.annotation.NonNull
import com.kpa.player.source.TCMSMediaSource

/**
 *
 * @author: kpa
 * @date: 2023/12/22
 * @description: 播放器约束
 * 一个可用于业务的播放器 需要哪些东西
 * 1. 播放器的状态
 * 2. 缩放模式
 * 3. 解码器类型
 * 4. 播放类型（音频、视频）
 * 5. 如果是一个适应流类型的播放器 还需要轨道变化原因的通知
 * 6. 附加信息 （一般情况下现在的播放器对应的附加信息是非常多的）
 * 7. 播放器事件监听
 * 8. 常见状态控制
 *
 */
interface TCMPlayer {

    companion object {
        @IntDef(
            STATE_IDLE,
            STATE_PREPARING,
            STATE_PREPARED,
            STATE_STARTED,
            STATE_PAUSED,
            STATE_COMPLETED,
            STATE_ERROR,
            STATE_STOPPED,
            STATE_RELEASED
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class PlayerState

        // -----------播放状态 start----------------------------------
        /**
         * 表示空闲状态。
         * <p>
         * <ul>
         *  <li>新创建的播放器实例处于空闲状态。</li>
         *  <li>从任何状态（不包括{@link #STATE_RELEASED}状态）调用{@link #reset()}将
         *  将播放器状态转换为空闲状态。</li>
         * </ul>
         */
        const val STATE_IDLE = 0

        /**
         * 表示准备状态。
         * <p>从{@link #STATE_IDLE}或{@link #STATE_STOPPED}调用{@link #prepare(MediaSource)}
         * 将把播放器状态转换为准备中。
         */
        const val STATE_PREPARING = 1

        /**
         * 表示已准备就绪状态。
         *
         * 在发出[StatePrepared]事件后，播放器状态将处于已准备就绪状态。
         */
        const val STATE_PREPARED = 2

        /**
         * 表示已启动状态。
         *
         * 从以下状态调用 [.start]：
         *
         *  * [.STATE_PREPARED]
         *  * [.STATE_PAUSED]
         *  * [.STATE_COMPLETED]
         *
         * 状态将转换为已启动状态。
         */
        const val STATE_STARTED = 3

        /**
         * 表示已暂停状态。
         *
         * 从以下状态调用 [.pause]：
         *
         *  * [.STATE_PREPARED]
         *  * [.STATE_STARTED]
         *  * [.STATE_COMPLETED]
         *
         * 状态将转换为已暂停状态。
         */
        const val STATE_PAUSED = 4

        /**
         * 表示已完成状态。
         *
         * 在发出[StateCompleted]事件后，播放器状态将处于已完成状态。
         */
        const val STATE_COMPLETED = 5

        /**
         * 表示错误状态。
         *
         * 在发出[StateError]事件后，播放器状态将处于错误状态。
         */
        const val STATE_ERROR = 6

        /**
         * 表示已停止状态。
         *
         * 在调用 [.stop] 后，播放器将处于已停止状态。
         */
        const val STATE_STOPPED = 7

        /**
         * 表示已释放状态。
         *
         * 在调用 [.release] 后，播放器将处于已释放状态。
         */
        const val STATE_RELEASED = 8
        // -----------播放状态 end----------------------------------

        // -----------缩放状态 start----------------------------------
        @IntDef(
            SCALING_MODE_DEFAULT,
            SCALING_MODE_ASPECT_FIT,
            SCALING_MODE_ASPECT_FILL
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class ScaleState

        /**
         * 默认缩放模式，即保持原始宽高比并适应视图。
         */
        const val SCALING_MODE_DEFAULT = 0

        /**
         * 等比例适应视图的缩放模式，可能会留有空白边。
         */
        const val SCALING_MODE_ASPECT_FIT = 1

        /**
         * 等比例填充视图的缩放模式，可能会裁剪图像以适应视图。
         */
        const val SCALING_MODE_ASPECT_FILL = 2

        // -----------缩放状态 end----------------------------------

    }

    interface Factory {
        class Default {
            companion object {
                private var sInstance: Factory? = null

                @JvmStatic
                @Synchronized
                fun set(factory: Factory) {
                    sInstance = factory
                }

                @JvmStatic
                @Synchronized
                fun get(): Factory? = sInstance
            }
        }

        fun create(@NonNull source: TCMSMediaSource)
    }
}