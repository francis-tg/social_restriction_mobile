package tg.ciscodev.access



import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Activez le gestionnaire de politiques de périphérique
        val deviceAdminReceiver = ComponentName(this, MyDeviceAdminReceiver::class.java)
        val devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

        if (!devicePolicyManager.isAdminActive(deviceAdminReceiver)) {
            // Demandez à l'utilisateur d'activer le gestionnaire de politiques de périphérique
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdminReceiver)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Description de l'application")
            startActivity(intent)
        }
    }
}
