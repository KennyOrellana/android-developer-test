package app.kaisa.parky.utils

object DateTime {
    fun formatMinutes(time: Int) : String {
        val hours: Int = time / 60
        val minutes: Int = time % 60
        return when {
            hours == 0 && minutes == 0 -> ""
            hours == 0 && minutes == 1 -> "1 m"
            hours == 0 -> "$minutes m"
            else -> String.format("%dh %02dm", hours, minutes)
        }
    }

}