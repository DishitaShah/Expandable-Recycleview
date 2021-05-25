package com.example.myapplication.core

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

/**
 * Created by Dishita on 5/5/2016.
 */
object sharedpreference {
    const val MyPREFERENCES = "MyPrefs"
    var username = "username"

    var Cart_quan = "cart_quan"

    var EmailID = "emailid"

    var user_id = "user_id"
    var phoneno = "phoneno"
    var fname = "fname"
    var lname = "lname"
    var level = "level"


    fun setvalue(name: String?, value: String?, c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.putString(name, value)
        editor.apply()
    }

    fun setfingerprint(name: String?, value: Boolean, c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.putBoolean(name, value)
        editor.apply()
    }




    fun setvaluewithoutzero(name: String?, value: String?, c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.putString(name, value)
        editor.commit()
    }

    fun setvaluebrandids(
        name: String?,
        value: Set<String?>?,
        c1: Context
    ) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.putStringSet(name, value)
        editor.commit()
    }
    fun setvaluebrandnames(
        name: String?,
        value: Set<String?>?,
        c1: Context
    ) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.putStringSet(name, value)
        editor.commit()
    }


    fun setcartquan(value: String?, c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.putString(Cart_quan, value)
        editor.commit()
    }





    fun clearusername(c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.remove(username)
        editor.commit()
    }


    fun clearemail(c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.remove(EmailID)
        editor.commit()
    }

    fun clearuserid(c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.remove(user_id)
        editor.commit()
    }

    fun clearall(c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.clear()
        editor.commit()
    }

    fun clear(name: String?, c1: Context) {
        val sharedpreferences: SharedPreferences =
            c1.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor: Editor = sharedpreferences.edit()
        editor.remove(name)
        editor.commit()
    }
}