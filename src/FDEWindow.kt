import actions.FDEOpenAction
import disk.FDEDiskFile
import disk.FDEGarbageSide
import disk.block.FDEDiskDataBlock
import disk.block.FDEDiskHeaderBlock
import disk.block.FDEDiskInfoBlock
import org.fife.ui.hex.swing.HexEditor
import util.MZEncoding
import java.awt.Dimension
import java.awt.event.KeyEvent
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream
import javax.swing.*
import javax.swing.filechooser.FileFilter
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import kotlin.system.exitProcess

class FDEWindow(title: String) : JFrame() {

    val splitPane = JSplitPane()
    var tree = JTree(DefaultMutableTreeNode("(no file loaded)"))
    var treeScroll = JScrollPane(tree)
    val value = JList<String>(arrayOf())
    val hexEditor = HexEditor()
    val valueScroll = JScrollPane(value)

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
        open.action = object: FDEOpenAction() {
            override fun doSomethingWithFile(file: File) {
                openFile(file)
            }
        }
        file.add(open)
        file.addSeparator()
        file.add(exit)
        menu.add(file)
        jMenuBar = menu


        tree.addTreeSelectionListener {
            if(currentFile == null) return@addTreeSelectionListener

            val node = (tree.lastSelectedPathComponent ?: return@addTreeSelectionListener) as DefaultMutableTreeNode

            if(!node.isLeaf) {
                return@addTreeSelectionListener
            }

            val obj = node.userObject

            val model = DefaultListModel<String>()

            if(obj is FDEDiskInfoBlock) {
                model.addElement("<html><b>Verified</b>: ${obj.verified}</html>")
                model.addElement("<html><b>Manufacturer</b>: ${obj.manufacturerCode}</html>")
                model.addElement("<html><b>Game Name</b>: ${obj.gameName}</html>")
                model.addElement("<html><b>Game Type</b>: ${obj.gameType}</html>")
                model.addElement("<html><b>Game Revision</b>: ${obj.gameRevision}</html>")
                model.addElement("<html><b>Side Number</b>: ${obj.sideNumber}</html>")
                model.addElement("<html><b>Disk Number</b>: ${obj.diskNumber}</html>")
                model.addElement("<html><b>Disk Type</b>: ${obj.diskType}</html>")
                model.addElement("<html><em>Unknown 1</em>: ${obj.unknown1.toRealString()}</html>")
                model.addElement("<html><b>Boot File Code</b>: ${obj.bootFile.toRealString()}</html>")
                model.addElement("<html><em>Unknown 2</em>: ${obj.unknown2}</html>")
                model.addElement("<html><b>Manufacturing Date</b>: ${obj.manufacturingDate}</html>")
                model.addElement("<html><b>Country</b>: ${obj.countryCode}</html>")
                model.addElement("<html><em>Unknown 3</em>: ${obj.unknown3.toRealString()}</html>")
                model.addElement("<html><em>Unknown 4</em>: ${obj.unknown4.toRealString()}</html>")
                model.addElement("<html><em>Unknown 5</em>: ${obj.unknown5}</html>")
                model.addElement("<html><em>Unknown 6</em>: ${obj.unknown6}</html>")
                model.addElement("<html><b>Rewrite Date</b>: ${obj.rewriteDate}</html>")
                model.addElement("<html><em>Unknown 7</em>: ${obj.unknown7.toRealString()}</html>")
                model.addElement("<html><em>Unknown 8</em>: ${obj.unknown8.toRealString()}</html>")
                model.addElement("<html><b>Disk Writer Serial No.</b>: ${obj.diskWriterSerial}</html>")
                model.addElement("<html><em>Unknown 9</em>: ${obj.unknown9.toRealString()}</html>")
                model.addElement("<html><b>Rewrite Count</b>: ${obj.rewriteCount}</html>")
                model.addElement("<html><b>Actual Side</b>: ${obj.actualSide}</html>")
                model.addElement("<html><em>Unknown 10</em>: ${obj.unknown10.toRealString()}</html>")
                model.addElement("<html><b>Price</b>: ${obj.price}</html>")
            }

            if(obj is FDEDiskHeaderBlock) {
                model.addElement("<html><b>File Number</b>: ${obj.fileNumber.toRealString()}</html>")
                model.addElement("<html><b>File ID</b>: ${obj.fileIndicateCode.toRealString()}</html>")
                model.addElement("<html><b>File Name</b>: ${obj.fileName}</html>")
                model.addElement("<html><b>File Dest. Address</b>: ${obj.fileAddress.toRealString()}</html>")
                model.addElement("<html><b>File Size</b>: ${obj.fileSize.toRealString()}</html>")
                model.addElement("<html><b>File Type</b>: ${obj.fileType}</html>")
            }

            fun openHexEdit(input: InputStream) {
                splitPane.rightComponent = hexEditor
                hexEditor.open(input)
            }

            when (obj) {
                is FDEDiskDataBlock -> {
                    /*if(obj.data.size == 224) { // TODO: Probably add more of a check then just size
                        model.addElement(MZEncoding.fromUByteArray(obj.data))
                        splitPane.rightComponent = valueScroll
                    } else {*/
                        openHexEdit(ByteArrayInputStream(obj.data.toByteArray()))
                    //}
                }
                is FDEGarbageSide.FDEGarbagePile -> {
                    openHexEdit(ByteArrayInputStream(obj.data.toByteArray()))
                }
                else -> {
                    splitPane.rightComponent = valueScroll
                }
            }

            value.model = model
            dirty()
        }
        splitPane.dividerLocation = 150
        treeScroll.size = Dimension(300, this.height)
        valueScroll.size = Dimension(this.width-300, this.height)
        treeScroll.minimumSize = Dimension(300, this.height)
        valueScroll.minimumSize =  Dimension(this.width-300, this.height)

        splitPane.leftComponent = treeScroll
        splitPane.rightComponent = valueScroll

        this.add(splitPane)

        isVisible = true
    }

    fun openFile(file: File) {
        if(currentFile != null) {
            TODO("close the file")
        }
        currentFile = FDEDiskFile.parseFromBytes(file.readBytes())

        val root = DefaultMutableTreeNode(file.name)
        for(s in currentFile?.sides!!) {
            val side = DefaultMutableTreeNode(s)
            if(s is FDEGarbageSide) {
                side.add(DefaultMutableTreeNode(s.pile))
            } else {
                side.add(DefaultMutableTreeNode(s.info))
                side.add(DefaultMutableTreeNode(s.amount))
                val files = DefaultMutableTreeNode("Files")
                for (f in s.files) {
                    val fileNode = DefaultMutableTreeNode(f)
                    fileNode.add(DefaultMutableTreeNode(f.header))
                    fileNode.add(DefaultMutableTreeNode(f.data))
                    files.add(fileNode)
                }
                side.add(files)
            }
            root.add(side)
        }

        tree.model = DefaultTreeModel(root)
        dirty()
    }

    fun dirty() {
        this.invalidate()
        this.validate()
        this.repaint()
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
        val chooser = JFileChooser()

        private val label = JLabel()
        private var lastFile: File? = null
        private var currentFile: FDEDiskFile? = null

        init {
            chooser.fileFilter = FDEFileFilter()
        }
    }
}

private fun UShort.toRealString(): String {
    return "0x${String.format("%04x", this.toInt())}"
}

private fun UByte.toRealString(): String {
    return "0x${String.format("%02x", this.toInt())}"
}
