package com.weicools.ktx.widget

/**
 * @author weicools
 * @date 2021.05.17
 */

@PublishedApi
internal const val NO_GETTER = "Property does not have a getter"

/**
 * Usage example:
 * `@Deprecated(NO_GETTER, level = DeprecationLevel.HIDDEN) get() = noGetter`
 */
@PublishedApi
internal inline val noGetter: Nothing
  get() = throw UnsupportedOperationException(NO_GETTER)