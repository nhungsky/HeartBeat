package com.nhunit.heartbeat.data

class StateDiagnose(
    val genderId: Int,
    val ageRange: String,
    val beatRange: Array<String>
) {
    companion object {
        val datas = arrayListOf(
            StateDiagnose(
                0, "18-25", arrayOf(
                    "49-55",
                    "56-61",
                    "62-65",
                    "66-69",
                    "70-73",
                    "74-81",
                    "82-1000"
                )
            ),
            StateDiagnose(
                1, "18-25", arrayOf(
                    "54-60",
                    "61-65",
                    "66-69",
                    "70-73",
                    "74-78",
                    "79-84",
                    "85-1000"
                )
            )
        )
    }
}