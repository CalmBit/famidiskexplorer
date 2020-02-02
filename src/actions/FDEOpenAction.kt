package actions

import FDEWindow
import util.FDEOSUtil
import java.awt.Component
import java.awt.event.ActionEvent
import java.awt.event.KeyEvent
import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.KeyStroke

class FDEOpenAction : AbstractAction("Open") {
    init {
        this.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, FDEOSUtil.getKeyMod()))
    }
    override fun actionPerformed(e: ActionEvent?) {
        if(e?.source is Component) {
            FDEWindow.chooser.showOpenDialog(e.source as Component)
            FDEWindow.openFile(FDEWindow.chooser.selectedFile)
        }
    }



}