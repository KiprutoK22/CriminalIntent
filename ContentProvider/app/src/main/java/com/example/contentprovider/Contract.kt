package com.example.contentprovider

import android.net.Uri

object Contract {
    // Customarily, to make Authority unique, it's the package name extended with "provider".
    // Must match with the authority defined in Android Manifest.
    const val AUTHORITY = "com.android.example.minimalistcontentprovider.provider"

    // The content path is an abstract semantic identifier of the data you are interested in.
    // It does not predict or presume in what form the data is stored or organized in the
    // background.
    const val CONTENT_PATH = "words"

    // A content:// style URI to the authority for this table */
    val CONTENT_URI: Uri? = Uri.parse("content://$AUTHORITY/$CONTENT_PATH")
    const val ALL_ITEMS = -2
    const val WORD_ID = "id"

    // MIME types for this content provider.
    const val SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.example.provider.words"
    const val MULTIPLE_RECORDS_MIME_TYPE = "vnd.android.cursor.dir/vnd.com.example.provider.words"
}
