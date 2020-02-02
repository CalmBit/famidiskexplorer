package disk

import util.BCDInt
import java.text.SimpleDateFormat
import java.util.*

class FDEDiskDate(val year: BCDInt, val month: BCDInt, val day: BCDInt) {

    private val _cal = Calendar.getInstance()
    private val _format = SimpleDateFormat("yyyy-MM-dd")
    init {
        _cal.set(Calendar.YEAR, 1925 + year.asInt())
        _cal.set(Calendar.MONTH, month.asInt()-1)
        _cal.set(Calendar.DAY_OF_MONTH, day.asInt())
        println(_cal.get(Calendar.MONTH))
    }

    fun toDate(): Date {
        return _cal.time
    }

    override fun toString() = _format.format(toDate())
}