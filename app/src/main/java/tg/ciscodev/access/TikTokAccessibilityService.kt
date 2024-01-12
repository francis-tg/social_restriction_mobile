package tg.ciscodev.access
import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

class TikTokAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.packageName != null && event.packageName.toString() == "com.zhiliaoapp.musically") {
            // TikTok est en cours d'exécution
            print("tiktk is used "+event.getSource())
            // Vous pouvez prendre des mesures ici, par exemple, afficher une notification, enregistrer le temps d'utilisation, etc.
        }
    }

    override fun onInterrupt() {
        // Cette méthode est appelée lorsque le service est interrompu
        print("i")
    }
}
