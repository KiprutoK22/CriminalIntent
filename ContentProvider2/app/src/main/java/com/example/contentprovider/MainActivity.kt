package com.example.contentprovider

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private var mTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextView = findViewById(R.id.textview)
    }

    /**
     * onClick method for the UI buttons.
     *
     * IMPORTANT: We can do this query two ways.
     * 1. Use a URL with a # at the end and all other arguments null.
     * 2. Use the CONTENT_URI URL and specify selection criteria.
     * We chose the second option to demonstrate the SQL-ness of this API.
     *
     * @param view
     */
    fun onClickDisplayEntries(view: View) {

        // URI That identifies the content provider and the table.
        val queryUri: String = Contract.CONTENT_URI.toString()

        // The columns to return for each row. Setting this to null returns all of them.
        val projection = arrayOf(Contract.CONTENT_PATH)

        // Argument clause for the selection criteria for which rows to return.
        // Passing null returns all rows for the given URI.
        val selectionClause: String?

        // Argument values for the selection criteria.
        // If you include ?s in selection, they are replaced by values from selectionArgs,
        // in the order that they appear.
        val selectionArgs: Array<String>?

        val sortOrder: String? =
            null
        when (view.id) {
            R.id.button_display_all -> {
                selectionClause = null
                selectionArgs = null
            }
            R.id.button_display_first -> {
                selectionClause = Contract.WORD_ID + " = ?"
                selectionArgs = arrayOf("0")
            }
            else -> {
                selectionClause = null
                selectionArgs = null
            }
        }

        // Let the content resolver parse the query and do the right things with it.
        val cursor: Cursor? = contentResolver.query(
            Uri.parse(queryUri), projection, selectionClause,
            selectionArgs, sortOrder
        )

        // If we got data back, display it, otherwise report the error.
        // See WordList app and database chapter for more on cursors.
        if (cursor != null) {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(projection[0])
                do {
                    val word = cursor.getString(columnIndex)
                    mTextView!!.append("$word\n")
                } while (cursor.moveToNext())
            } else {
                Log.d(TAG, "onClickDisplayEntries ")
                mTextView!!.append("No data returned")
            }
            cursor.close()
        } else {
            Log.d(TAG, "onClickDisplayEntries ")
            mTextView!!.append("Cursor is null")
        }
    }
}