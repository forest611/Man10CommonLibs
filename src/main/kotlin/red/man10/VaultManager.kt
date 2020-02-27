package red.man10

import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.OfflinePlayer
import org.bukkit.plugin.java.JavaPlugin
import java.util.*


/**
 * Created by takatronix on 2017/03/04.
 */
class VaultManager(private val plugin: JavaPlugin) {
    var showMessage = false
    private fun setupEconomy(): Boolean {
        plugin.logger.info("setupEconomy")
        if (plugin.server.pluginManager.getPlugin("Vault") == null) {
            plugin.logger.warning("Vault plugin is not installed")
            return false
        }
        val rsp = plugin.server.servicesManager.getRegistration(Economy::class.java)
        if (rsp == null) {
            plugin.logger.warning("Can't get vault service")
            return false
        }
        economy = rsp.provider
        plugin.logger.info("Economy setup")
        return economy != null
    }

    fun canUseVault(): Boolean {
        return economy != null
    }

    /////////////////////////////////////
    //      残高確認
    /////////////////////////////////////
    fun getBalance(uuid: UUID): Double {
        val p: OfflinePlayer? = Bukkit.getOfflinePlayer(uuid).player
        if (p == null) {
            Bukkit.getLogger().warning("vault getbalance:Cant get player :$uuid")
            return 0.0
        }
        return economy!!.getBalance(p)
    }

    /////////////////////////////////////
    //      残高確認
    /////////////////////////////////////
    fun showBalance(uuid: UUID) {
        val p: OfflinePlayer? = Bukkit.getOfflinePlayer(uuid).player
        val money = getBalance(uuid)
        p!!.player!!.sendMessage(ChatColor.YELLOW.toString() + "あなたの所持金は$" + money)
    }

    /////////////////////////////////////
    //      引き出し
    /////////////////////////////////////
    fun withdraw(uuid: UUID, money: Double): Boolean {
        val p = Bukkit.getOfflinePlayer(uuid)
        val resp = economy!!.withdrawPlayer(p, money)
        if (resp.transactionSuccess()) {
            if (p.isOnline) {
                if (showMessage) {
                    p.player!!.sendMessage(ChatColor.YELLOW.toString() + "$" + money + "支払いました")
                }
            }
            return true
        }
        return false
    }

    /////////////////////////////////////
    //      お金を入れる
    /////////////////////////////////////
    fun deposit(uuid: UUID, money: Double): Boolean {
        val p = Bukkit.getOfflinePlayer(uuid)
        val resp = economy!!.depositPlayer(p, money)
        if (resp.transactionSuccess()) {
            if (p.isOnline) {
                if (showMessage) {
                    p.player!!.sendMessage(ChatColor.YELLOW.toString() + "$" + money + "受取りました")
                }
            }
            return true
        }
        return false
    }

    companion object {
        var economy: Economy? = null
    }

    init {
        setupEconomy()
    }
}