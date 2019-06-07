package omdbapp.odair.sdm.ifsp.edu.br.omdbapp.util

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes

fun AppCompatActivity.replaceFragmenty(
        fragment: Fragment,
        allowStateLoss: Boolean = false,
        @IdRes containerViewId: Int
) {
    val ft = supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}