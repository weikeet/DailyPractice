package com.weiwei.practice.androidart.chapter_3

import android.content.Context
import android.util.Log
import android.widget.TextView

/**
 * @author Weicools
 *
 * @date 2021.10.24
 */
class ObtainParamsView(private val context: Context) {
  private val titleView = TextView(context)

  fun getViewPosition() {
    // View的位置主要由它的四个顶点来决定
    // 以下四个值不会随着 translationX translationY 变化
    val left = titleView.left // 左上角顶点横坐标
    val top = titleView.top // 左上角顶点横坐标
    val right = titleView.right // 右下角顶点横坐标
    val bottom = titleView.bottom // 右下角顶点横坐标
    Log.d("ObtainParamsView", "getViewPosition: left = $left, top = $top, right = $right, bottom = $bottom")

    // titleView.width
    val width = right - left
    // titleView.height
    val height = bottom - top
    Log.d("ObtainParamsView", "getViewPosition: width = ${titleView.width}, height = ${titleView.height}, width = $width, height = $height")

    // 从Android3.0开始，View增加 x y 表示 View 左上角坐标
    // 会随着 translationX translationY 变化
    val x = titleView.x
    val y = titleView.y
    Log.d("ObtainParamsView", "getViewPosition: x = $x, y = $y")

    // translationX 和 translationY 是View左上角相对于父容器的偏移量
    val translationX = titleView.translationX
    val translationY = titleView.translationY
    Log.d("ObtainParamsView", "getViewPosition: translationX = $translationX, translationY = $translationY")

    // x y 与 translationX translationY 计算关系
    val nx = titleView.left + titleView.translationX
    val ny = titleView.top + titleView.translationY
    Log.d("ObtainParamsView", "getViewPosition: nx = $nx, ny = $ny")
  }
}