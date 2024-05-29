package com.example.project2.model

enum class TaskPriority(val order: Int) {
    HIGH(1),
    MEDIUM(2),
    LOW(3);
    companion object {
        fun fromString(value: String): TaskPriority {
            return when (value) {
                "HIGH" -> HIGH
                "MEDIUM" -> MEDIUM
                "LOW" -> LOW
                else -> LOW
            }
        }
    }
}