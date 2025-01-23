package com.tpsmedia.masterguidev4

interface DownloadCallback {
    fun onProgressUpdate(progress: Int)
    fun onDownloadComplete(filePath: String)
    fun onDownloadFailed(errorMessage: String)
}