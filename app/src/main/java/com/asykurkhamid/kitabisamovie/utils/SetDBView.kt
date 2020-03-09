package com.asykurkhamid.kitabisamovie.utils

interface SetDBView {
    fun showLoading()
    fun hideLoading()
    fun showMessage(message: String)
    fun updateBtnFav(isChecked: Boolean)
}