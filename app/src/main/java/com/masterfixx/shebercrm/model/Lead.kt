package com.masterfixx.shebercrm.model

data class Lead(
    val id: Int,
    val name: String,
    val phone: String,
    val address: String,
    val objectType: String,
    val cameraCount: Int,
    val cameraType: String,
    val recorder: String,
    val status: LeadStatus,
    val createdAt: String,
    val notes: String = ""
)

enum class LeadStatus(val label: String) {
    NEW("Новая"),
    IN_PROGRESS("В работе"),
    INSTALLED("Установлено")
}

val objectTypes = listOf("Частный дом", "Квартира", "Офис", "Магазин", "Склад")
val cameraTypes = listOf("IP (Hikvision)", "IP (Dahua)", "Аналоговая")
val recorderTypes = listOf("NVR", "DVR", "Без регистратора (облако)")

// Тестовые данные, пока нет базы данных.
// В defense-версии можно объяснить: это временный источник данных в памяти.
val sampleLeads = listOf(
    Lead(1, "Алия Сатпаева", "+7 701 111 2233", "мкр. Самал-2, 15", "Квартира", 4, "IP (Hikvision)", "NVR", LeadStatus.NEW, "28 сентября"),
    Lead(2, "Бекзат Оспанов", "+7 701 222 3344", "ул. Абая, 44", "Офис", 8, "IP (Dahua)", "NVR", LeadStatus.IN_PROGRESS, "27 сентября"),
    Lead(3, "Гульнара Ахметова", "+7 701 333 4455", "мкр. Коктем, 8", "Частный дом", 6, "IP (Hikvision)", "NVR", LeadStatus.INSTALLED, "26 сентября"),
    Lead(4, "Даурен Смагулов", "+7 701 444 5566", "ул. Достык, 120", "Магазин", 12, "IP (Dahua)", "DVR", LeadStatus.NEW, "25 сентября"),
    Lead(5, "Ержан Тулеуов", "+7 701 555 6677", "мкр. Аксай-3, 22", "Квартира", 2, "Аналоговая", "DVR", LeadStatus.IN_PROGRESS, "24 сентября"),
    Lead(6, "Жанна Кабдулова", "+7 701 666 7788", "ул. Розыбакиева, 67", "Частный дом", 6, "IP (Hikvision)", "NVR", LeadStatus.NEW, "23 сентября"),
    Lead(7, "Ислам Нурланов", "+7 701 777 8899", "мкр. Орбита-1, 5", "Склад", 10, "IP (Dahua)", "NVR", LeadStatus.INSTALLED, "22 сентября"),
    Lead(8, "Камила Ержанова", "+7 701 888 9900", "ул. Тимирязева, 33", "Офис", 4, "IP (Hikvision)", "NVR", LeadStatus.NEW, "21 сентября"),
    Lead(9, "Мурат Бекенов", "+7 701 999 0011", "мкр. Жетысу-2, 11", "Квартира", 3, "Аналоговая", "DVR", LeadStatus.IN_PROGRESS, "20 сентября"),
    Lead(10, "Наталья Ким", "+7 701 000 1122", "ул. Сатпаева, 90", "Частный дом", 8, "IP (Hikvision)", "NVR", LeadStatus.NEW, "19 сентября"),
    Lead(11, "Олжас Дюсенов", "+7 701 123 4567", "мкр. Мамыр-1, 3", "Магазин", 6, "IP (Dahua)", "NVR", LeadStatus.INSTALLED, "18 сентября"),
    Lead(12, "Перизат Женисова", "+7 701 234 5678", "ул. Гагарина, 200", "Офис", 5, "IP (Hikvision)", "NVR", LeadStatus.NEW, "17 сентября")
)