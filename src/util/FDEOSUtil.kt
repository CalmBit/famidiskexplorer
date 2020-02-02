package util

import java.awt.event.InputEvent
import javax.swing.UIManager

object FDEOSUtil {
    val current_os = System.getProperty("os.name")

    fun isMac(): Boolean {
        return current_os.contains("Mac OS X")
    }

    fun getKeyMod() = if(isMac()) InputEvent.META_MASK else InputEvent.CTRL_MASK

    fun initForOS() {
        if(isMac()) {
            System.setProperty("apple.awt.application.name", "Famicom Disk Explorer")
            System.setProperty("apple.laf.useScreenMenuBar", "true")
        }
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    }
}