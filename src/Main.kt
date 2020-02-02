import util.ExplicitUByteArray
import util.FDEBCDUtil
import util.FDEOSUtil

fun main(args: Array<String>) {
    FDEOSUtil.initForOS()
    java.awt.EventQueue.invokeLater { FDEWindow("Famicom Disk Explorer") }
}