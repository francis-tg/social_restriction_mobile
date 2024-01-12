package tg.ciscodev.access

import java.io.DataOutputStream
import java.io.IOException

class FirewallManager {

    // Méthode pour bloquer les requêtes contenant les domaines spécifiés
    fun blockTikTokDomains() {
        try {
            val process = Runtime.getRuntime().exec("su")
            val os = DataOutputStream(process.outputStream)

            // Bloquer les requêtes sortantes vers les domaines spécifiés
            os.writeBytes("iptables -A OUTPUT -p tcp -m multiport --sports 80,443 -m string --algo bm --string 'tiktok.com' -j DROP\n")
            os.writeBytes("iptables -A OUTPUT -p tcp -m multiport --sports 80,443 -m string --algo bm --string 'tik-tok.com' -j DROP\n")
            os.writeBytes("iptables -A OUTPUT -p tcp -m multiport --sports 80,443 -m string --algo bm --string 'tiktokcdn.com' -j DROP\n")

            // Bloquer les requêtes entrantes depuis les domaines spécifiés (si nécessaire)
            os.writeBytes("iptables -A INPUT -p tcp -m multiport --dports 80,443 -m string --algo bm --string 'tiktok.com' -j DROP\n")
            os.writeBytes("iptables -A INPUT -p tcp -m multiport --dports 80,443 -m string --algo bm --string 'tik-tok.com' -j DROP\n")
            os.writeBytes("iptables -A INPUT -p tcp -m multiport --dports 80,443 -m string --algo bm --string 'tiktokcdn.com' -j DROP\n")

            os.writeBytes("exit\n")
            os.flush()
            os.close()
            process.waitFor()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
