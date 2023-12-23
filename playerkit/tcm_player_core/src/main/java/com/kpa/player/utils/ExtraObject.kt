package com.kpa.player.utils

import android.os.Parcelable
import java.io.Serializable

/**
 *
 * @author: kpa
 * @date: 2023/12/22
 * @description:用于处理额外的对象数据的基础类。
 */
open class ExtraObject {
    protected val mExtras = mutableMapOf<String, Any>()

    /**
     * 将额外数据添加到映射中。
     *
     * @param key 添加的数据的键
     * @param extra 要添加的额外数据，可以为 null
     * @throws RuntimeException 如果额外数据不是可序列化或可Parcelable的类型
     */
    fun putExtra(key: String, extra: Any?) {
        if (extra == null) {
            mExtras.remove(key)
        } else {
            if (extra is Serializable || extra is Parcelable) {
                mExtras[key] = extra
            } else {
                throw RuntimeException("必须是序列化后的数据")
            }
        }
    }

    /**
     * 从映射中获取指定键的额外数据。
     *
     * @param key 要获取的额外数据的键
     * @param clazz 额外数据的类型
     * @return 如果映射中存在对应键的额外数据且其类型符合指定类型，则返回该额外数据；否则返回 null
     * @throws ClassCastException 如果额外数据的类型无法转换为指定类型
     */
    fun <T> getExtra(key: String, clazz: Class<T>): T? {
        val extra = mExtras[key]
        extra?.let {
            if (clazz.isInstance(extra)) {
                return extra as T
            }
            throw ClassCastException("${extra::class.java.name}不能转换到$clazz")
        }
        return null
    }

    fun clearExtras() {
        mExtras.clear()
    }
}