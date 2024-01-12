package tg.ciscodev.access

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast

class MyDeviceAdminReceiver : DeviceAdminReceiver() {

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        showToast(context, "Administrateur de périphérique activé")
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        showToast(context, "Administrateur de périphérique désactivé")
    }

    override fun onPasswordChanged(context: Context, intent: Intent) {
        super.onPasswordChanged(context, intent)
        showToast(context, "Mot de passe du périphérique changé")
    }

    override fun onPasswordFailed(context: Context, intent: Intent) {
        super.onPasswordFailed(context, intent)
        showToast(context, "Tentative de déverrouillage échouée")
    }

    override fun onPasswordSucceeded(context: Context, intent: Intent) {
        super.onPasswordSucceeded(context, intent)
        showToast(context, "Déverrouillage réussi du périphérique")
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    // Ajoutez cette méthode pour désactiver TikTok
    fun disableTikTok(context: Context) {
        try {
            val packageManager = context.packageManager
            packageManager.setApplicationEnabledSetting(
                "com.zhiliaoapp.musically",
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            showToast(context, "TikTok désactivé")
        } catch (e: Exception) {
            Log.e("MyDeviceAdminReceiver", "Erreur lors de la désactivation de TikTok", e)
            showToast(context, "Erreur lors de la désactivation de TikTok")
        }
    }
}
