import actions.FDEOpenAction
import disk.FDEDiskFile
import java.awt.event.KeyEvent
import java.io.File
import javax.swing.*
import javax.swing.filechooser.FileFilter
import kotlin.system.exitProcess

class FDEWindow(title: String) : JFrame() {

    val chooser = JFileChooser()
    init {
        setTitle(title)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(800,600)
        setLocationRelativeTo(null)

        val menu = JMenuBar()
        val file = JMenu("File")
        file.mnemonic = KeyEvent.VK_F
        val exit = JMenuItem("Exit")
        exit.addActionListener { exitProcess(0) }
        exit.mnemonic = KeyEvent.VK_E
        val open = JMenuItem("Open")
        open.action = FDEOpenAction()
        file.add(open)
        file.addSeparator()
        file.add(exit)
        menu.add(file)
        jMenuBar = menu

        this.add(label)

        isVisible = true
    }

    class FDEFileFilter : FileFilter() {
        override fun accept(f: File?): Boolean {
            return f != null && (f.isDirectory || f.extension == "fds")
        }

        override fun getDescription(): String {
            return "FDS file (*.fds)"
        }

    }

    companion object {
        private var count = 0;
        val chooser = JFileChooser()
        private val label = JLabel()
        private var lastFile: File? = null
        private var currentFile: FDEDiskFile? = null

        init {
            chooser.fileFilter = FDEFileFilter()
        }

        fun setCount(i: Int) {
            count = i
            label.text = i.toString()
        }

        fun getCount(): Int {
            return count
        }

        fun openFile(file: File) {
            if(currentFile != null) {
                TODO("close the file")
            }
            currentFile = FDEDiskFile.parseFromBytes(file.readBytes())
        }

    }
}