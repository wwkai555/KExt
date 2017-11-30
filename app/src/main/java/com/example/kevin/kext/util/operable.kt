package com.example.kevin.kext.util

/**
 * Created by kevin on 28/11/2017.
 */
abstract class OperableObservable<T>(private var value: T) : () -> T {
    override fun invoke() = value
    abstract fun onChange(old: T, new: T)
    fun get() = value
    private fun set(value: T) {
        onChange(this.value, value)
        this.value = value
    }

    infix fun to(new: T) {
        set(new)
    }

    companion object {
        fun <T> create(default: T, block: (T, T) -> Unit): OperableObservable<T> = object : OperableObservable<T>(default) {
            override fun onChange(old: T, new: T) {
                block(old, new)
            }
        }
    }
}
