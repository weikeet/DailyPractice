package com.weiwei.core.recyclerview

/**
 * an interface should be implemented by object wanna be differentiated by [AsyncListDiffer]
 */
interface Differ {

  /**
   * whether this object and [other] is the same object
   *
   * Check that both data use the same ViewType
   */
  fun isSameAs(other: Any?): Boolean

  /**
   * whether this object has the same content with [other]
   *
   * check data content is same (if [isSameAs] is true)
   */
  fun isContentSameAs(other: Any?): Boolean

  /**
   * diff one object to [other] object
   *
   * diff data content (if [isContentSameAs] is false])
   *
   * @return the detail of difference defined by yourself
   */
  fun diff(other: Any?): Any?

}