package red.man10

import org.bukkit.plugin.java.JavaPlugin

class Man10CommonLibs : JavaPlugin(){
    override fun onEnable() {
        super.onEnable()
        logger.info("Man10CommonLibs enabled")
    }

    override fun onDisable() {
        super.onDisable()
    }
}