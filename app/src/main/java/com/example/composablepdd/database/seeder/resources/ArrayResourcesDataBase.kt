package com.example.composablepdd.database.seeder.resources

import android.app.Application
import com.example.composablepdd.R
import java.io.InputStream
import javax.inject.Inject

class ArrayResourcesDataBase @Inject constructor(
   private val appContext: Application
) {

    val listThemeName = listOf("Общие положения", "Дорожные знаки", "Дорожная разметка",
        "Сигналы светофора и регулировщика", "Начало движения и маневрирование", "Скорость движения", "Обгон, опережение, встречный разъезд",
        "Остановка и стоянка", "проезд перекрестов", "пользование внешними световыми приборами и звуковыми синалами",
        "Неисправности и условия допуска транспортных средств к эксплуатации", "Безопасность движения и техника управления автомобилем",
        "Оказание первой медицинской помощи", "Общие обязанности водителей", "Разположение транспортных средств на проезжей части",
        "Приоритет маршрутных транспортных средств", "Буксировка механических транспортных средств", "Применение специальных сигналов",
        "Движение по автомагистралям", "Учебная езда и дополнительные требования к движению велосипедов", "Движение в жилых зонах",
        "Движение через железнодорожные пути", "Пешеходные переходы и места остановок маршрутных транспортных средств",
        "Перевозка людей и грузов", "Ответственность водителя", "Применение аварийной сигнализации и знака аварийной остановки"
    )

    val listThemeCount = listOf("25", "126", "40", "39", "113", "19", "36", "47", "113", "23",
        "26", "59", "20", "15", "24", "5", "8", "9", "13", "4", "7", "11", "5", "6", "15", "9"
    )

    val listNumTicket = (1..40).toList()

    val listName = appContext.resources.getStringArray(R.array.BiletesName)
    val listVar1 = appContext.resources.getStringArray(R.array.BiletesVop1)
    val listVar2 = appContext.resources.getStringArray(R.array.BiletesVop2)
    val listVar3 = appContext.resources.getStringArray(R.array.BiletesVop3)
    val listVar4 = appContext.resources.getStringArray(R.array.BiletesVop4)
    val listVar5 = appContext.resources.getStringArray(R.array.BiletesVop5)
    val listTextHelp = appContext.resources.getStringArray(R.array.BiletesTextHelp)

    val listImage = listOf(
        null, "b1_v2", "b1_v3", "b1_v4", "b1_v5", null,
        null, "b1_v8", "b1_v9", "b1_v10", "b1_v11", "b1_v12",
        "b1_v13", "b1_v14", "b1_v15", "b1_v16", null, null, null, null,
        "b2_v1", "b2_v2", "b2_v3", "b2_v4", "b2_v5",
        "b2_v6", "b2_v7", "b2_v8", "b2_v9", null,
        "b2_v11", "b2_v12", "b2_v13", "b2_v14", "b2_v15",
        "b2_v16", null, null, null, null, "b3_v1", "b3_v2", "b3_v3",
        "b3_v4", "b3_v5", "b3_v6", null, "b3_v8", "b3_v9",
        "b3_v10", null, "b3_v12", "b3_v13", "b3_v14", "b3_v15",
        "b3_v16", null, null, null, null, "b4_v1", "b4_v2", "b4_v3",
        "b4_v4", null, "b4_v6", "b4_v7", "b4_v8", "b4_v9", null,
        "b4_v11", "b4_v12", "b4_v13", "b4_v14", "b4_v15", null, null,
        null, null, null, "b5_v1", "b5_v2", "b5_v3", "b5_v4", "b5_v5",
        null, "b5_v7", "b5_v8", "b5_v9", "b5_v10", "b5_v11",
        "b5_v12", "b5_v13", "b5_v14", "b5_v15", "b5_v16", null, null,
        null, "b5_v20", null, "b6_v2", "b6_v3", "b6_v4", "b6_v5",
        null, "b6_v7", "b6_v8", "b6_v9", "b6_v10", "b6_v11",
        "b6_v12", "b6_v13", "b6_v14", "b6_v15", null, null, null,
        "b6_v19", null, null, "b7_v2", "b7_v3", "b7_v4", "b7_v5", "b7_v6", null,
        "b7_v8", "b7_v9", null, "b7_v11", "b7_v12", "b7_v13", "b7_v14",
        "b7_v15", null, "b7_v17", null, null, null, null, "b8_v2", "b8_v3", "b8_v4",
        "b8_v5", "b8_v6", "b8_v7", "b8_v8", "b8_v9", "b8_v10", "b8_v11",
        "b8_v12", "b8_v13", "b8_v14", "b8_v15", null, null, null, null, null, null, "b9_v2",
        "b9_v3", "b9_v4", "b9_v5", "b9_v6", null, "b9_v8", "b9_v9", "b9_v10",
        "b9_v11", "b9_v12", "b9_v13", "b9_v14", "b9_v15", null, null, null,
        "b9_v19", null, "b10_v1", "b10_v2", "b10_v3", "b10_v4", "b10_v5",
        "b10_v6", "b10_v7", "b10_v8", "b10_v9", null, "b10_v11", null,
        "b10_v13", "b10_v14", "b10_v15", "b10_v16", null, null, null, null, null, "b11_v2",
        "b11_v3", "b11_v4", "b11_v5", null, "b11_v7", "b11_v8", "b11_v9", null, null,
        "b11_v12", "b11_v13", "b11_v14", "b11_v15", null, null, null, "b11_v19", null, null,
        "b12_v2", "b12_v3", "b12_v4", "b12_v5", null, "b12_v7", "b12_v8",
        "b12_v9", "b12_v10", "b12_v11", "b12_v12", "b12_v13", "b12_v14",
        "b12_v15", null, null, null, null, null, "b13_v1", "b13_v2", "b13_v3", "b13_v4",
        "b13_v5", "b13_v6", null, "b13_v8", "b13_v9", "b13_v10", "b13_v11",
        "b13_v12", "b13_v13", "b13_v14", "b13_v15", "b13_v16", null, null, null, null, null,
        "b14_v2", "b14_v3", "b14_v4", "b14_v5", "b14_v6", "b14_v7", "b14_v8",
        "b14_v9", null, "b14_v11", "b14_v12", "b14_v13", "b14_v14", "b14_v15",
        "b14_v16", null, null, null, null, null, "b15_v2", "b15_v3", "b15_v4", "b15_v5",
        "b15_v6", "b15_v7", "b15_v8", "b15_v9", null, null, "b15_v12", "b15_v13",
        "b15_v14", "b15_v15", "b15_v16", null, null, null, null, null, "b16_v2", "b16_v3",
        "b16_v4", "b16_v5", "b16_v6", null, "b16_v8", "b16_v9", "b16_v10",
        "b16_v11", "b16_v12", "b16_v13", "b16_v14", "b16_v15", "b16_v16",
        null, null, "b16_v19", null, null, "b17_v2", "b17_v3", "b17_v4", "b17_v5",
        "b17_v6", "b17_v7", "b17_v8", "b17_v9", null, "b17_v11", "b17_v12",
        "b17_v13", "b17_v14", "b17_v15", "b17_v16", null, null, null, null, null, "b18_v2",
        "b18_v3", "b18_v4", "b18_v5", "b18_v6", "b18_v7", "b18_v8", "b18_v9",
        null, "b18_v11", "b18_v12", "b18_v13", "b18_v14", "b18_v15", "b18_v16", null, null,
        null, null, "b19_v1", "b19_v2", "b19_v3", "b19_v4", null, "b19_v6", null, "b19_v8",
        "b19_v9", "b19_v10", "b19_v11", "b19_v12", "b19_v13", "b19_v14", null, "b19_v16",
        null, null, null, null, null, "b20_v2", "b20_v3", "b20_v4", null, "b20_v6", "b20_v7",
        "b20_v8", "b20_v9", "b20_v10", "b20_v11", "b20_v12", "b20_v13", "b20_v14",
        "b20_v15", "b20_v16", null, null, null, null, null, "b21_v2", "b21_v3", "b21_v4", "b21_v5",
        "b21_v6", "b21_v7", "b21_v8", null, "b21_v10", "b21_v11", "b21_v12", "b21_v13",
        "b21_v14", "b21_v15", null, "b21_v17", null, "b21_v19", null, "b22_v1", null, "b22_v3",
        "b22_v4", null, "b22_v6", "b22_v7", "b22_v8", "b22_v9", null, null, "b22_v12", "b22_v13",
        "b22_v14", "b22_v15", null, null, null, null, null, "b23_v1", "b23_v2", "b23_v3", "b23_v4",
        "b23_v5", "b23_v6", null, "b23_v8", "b23_v9", "b23_v10", "b23_v11", "b23_v12",
        "b23_v13", "b23_v14", "b23_v15", "b23_v16", null, null, null, null, null, "b24_v2", "b24_v3",
        "b24_v4", "b24_v5", null, "b24_v7", "b24_v8", "b24_v9", "b24_v10", "b24_v11",
        "b24_v12", "b24_v13", "b24_v14", "b24_v15", "b24_v16", null, null, null, null,
        "b25_v1", "b25_v2", "b25_v3", "b25_v4", "b25_v5", "b25_v6", "b25_v7", "b25_v8",
        "b25_v9", null, "b25_v11", "b25_v12", null, "b25_v14", "b25_v15", "b25_v16",
        null, null, null, null, "b26_v1", "b26_v2", "b26_v3", null, "b26_v5",
        null, "b26_v7", "b26_v8", "b26_v9", "b26_v10", null, "b26_v12", "b26_v13",
        "b26_v14", "b26_v15", null, "b26_v17", null, null, null, null, "b27_v2", "b27_v3", "b27_v4",
        "b27_v5", null, "b27_v7", "b27_v8", "b27_v9", "b27_v10", null, "b27_v12",
        "b27_v13", "b27_v14", "b27_v15", "b27_v16", null, null, null, "b27_v20",
        null, "b28_v2", "b28_v3", "b28_v4", "b28_v5", "b28_v6", null, "b28_v8",
        "b28_v9", "b28_v10", "b28_v11", "b28_v12", "b28_v13", "b28_v14", "b28_v15",
        "b28_v16", null, null, null, null, null, "b29_v2", "b29_v3", "b29_v4", "b29_v5", "b29_v6",
        null, "b29_v8", "b29_v9", "b29_v10", "b29_v11", "b29_v12", "b29_v13", "b29_v14",
        "b29_v15", "b29_v16", null, null, null, null, "b30_v1", "b30_v2", "b30_v3", "b30_v4",
        "b30_v5", "b30_v6", null, "b30_v8", "b30_v9", "b30_v10", null, "b30_v12",
        "b30_v13", "b30_v14", "b30_v15", null, null, null, null, null, null, "b31_v2", "b31_v3",
        "b31_v4", null, "b31_v6", "b31_v7", "b31_v8", "b31_v9", null,
        "b31_v11", "b31_v12", "b31_v13", "b31_v14", "b31_v15", "b31_v16", null,
        null, null, null, null, "b32_v2", "b32_v3", "b32_v4", "b32_v5", "b32_v6", "b32_v7", null,
        "b32_v9", null, null, "b32_v12", "b32_v13", "b32_v14", "b32_v15", null,
        null, null, "b32_v19", null, "b33_v1", "b33_v2", "b33_v3", "b33_v4", "b33_v5", null, null, "b33_v8",
        "b33_v9", null, "b33_v11", "b33_v12", "b33_v13", "b33_v14", "b33_v15", "b33_v16",
        null, null, null, null, "b34_v1", "b34_v2", "b34_v3", "b34_v4", "b34_v5", "b34_v6", null, "b34_v8",
        "b34_v9", "b34_v10", "b34_v11", "b34_v12", "b34_v13", "b34_v14", "b34_v15", null,
        null, null, "b34_v19", null, null, "b35_v2", "b35_v3", "b35_v4", "b35_v5", null, "b35_v7", "b35_v8",
        null, "b35_v10", "b35_v11", "b35_v12", "b35_v13", "b35_v14", "b35_v15", null,
        null, null, null, null, null, "b36_v2", "b36_v3", "b36_v4", "b36_v5", "b36_v6", "b36_v7", "b36_v8",
        null, null, null, "b36_v12", "b36_v13", "b36_v14", "b36_v15", "b36_v16",
        null, null, null, null, null, "b37_v2", "b37_v3", "b37_v4", "b37_v5", "b37_v6", "b37_v7", "b37_v8",
        "b37_v9", "b37_v10", null, "b37_v12", "b37_v13", "b37_v14", "b37_v15", null,
        null, null, null, null, "b38_v1", "b38_v2", "b38_v3", "b38_v4", "b38_v5", null, "b38_v7", "b38_v8",
        "b38_v9", "b38_v10", "b38_v11", "b38_v12", "b38_v13", "b38_v14", "b38_v15", null,
        null, null, null, null, null, "b39_v2", "b39_v3", "b39_v4", "b39_v5", "b39_v6", null, "b39_v8",
        "b39_v9", "b39_v10", "b39_v11", "b39_v12", "b39_v13", "b39_v14", "b39_v15", "b39_v16",
        null, null, null, null, null, "b40_v2", "b40_v3", "b40_v4", "b40_v5", "b40_v6", "b40_v7", "b40_v8",
        "b40_v9", "b40_v10", "b40_v11", "b40_v12", "b40_v13", "b40_v14", "b40_v15", "b40_v16",
        null, null, null, null
    )

     val itemNumber = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,
        1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,)

     val listNumber = listOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
        3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,
        6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,
        9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
        11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,
        13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,
        15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,
        17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,18,
        19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,
        21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,
        23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,
        25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,26,
        27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,28,
        29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,
        31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,31,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,
        33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,34,
        35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,
        37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,37,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,38,
        39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,)

    val checkVariantTrue = listOf(
        2,1,1,4,2,2,4,3,1,3,1,3,3,1,3,4,3,4,3,2,2,1,1,3,3,1,3,3,1,3,3,2,3,3,1,3,2,1,3,3,
        1,3,3,2,2,3,2,1,3,1,3,2,2,1,3,3,4,2,3,3,2,2,1,1,2,1,1,2,2,1,1,2,3,3,2,4,1,4,1,2,
        1,2,1,1,3,1,3,3,2,3,1,2,2,1,1,3,2,1,2,1,3,2,3,3,2,1,1,2,3,3,1,3,3,2,2,3,3,4,1,2,
        1,2,1,2,2,2,1,3,2,2,1,3,2,2,3,3,3,3,2,2,2,3,3,1,1,3,1,2,3,3,2,1,1,2,2,3,2,1,1,2,
        2,2,1,3,1,2,2,1,3,2,3,3,2,2,3,3,4,3,2,2,2,1,3,3,2,1,1,2,1,3,2,2,3,3,3,3,2,4,3,1,
        2,1,3,2,3,3,1,1,2,3,3,1,1,2,1,2,3,2,3,2,2,3,2,1,2,3,2,1,3,2,2,3,2,3,3,3,2,2,1,3,
        3,2,3,2,3,1,1,3,2,3,3,3,1,3,2,2,1,2,3,2,1,2,2,3,2,1,1,3,1,2,1,1,3,3,2,2,1,3,1,3,
        4,2,3,3,4,1,1,3,1,2,3,1,3,2,1,2,4,3,2,2,4,2,2,2,3,2,3,2,3,2,1,1,2,1,3,2,3,2,1,2,
        1,1,2,2,3,1,2,3,2,2,3,2,1,1,2,4,3,2,3,1,1,3,1,2,2,2,3,2,2,3,3,2,3,3,2,3,2,2,3,3,
        2,3,1,3,2,2,3,1,1,3,3,3,2,1,3,1,2,3,1,3,4,1,3,2,2,3,1,3,2,2,3,1,3,2,3,1,2,1,2,2,
        2,3,1,3,3,3,1,3,2,3,2,3,3,3,2,4,3,2,1,2,1,2,2,3,2,3,1,2,3,4,2,1,1,3,2,3,2,2,3,1,
        2,1,2,2,3,2,1,1,3,3,3,3,2,2,2,1,3,2,3,3,3,2,1,2,2,2,2,3,2,3,3,2,1,3,3,2,1,2,3,3,
        1,3,1,2,4,2,1,2,3,3,3,3,1,3,2,3,2,4,3,1,3,2,2,1,2,2,1,2,3,2,3,2,2,3,2,2,1,3,2,2,
        2,1,1,3,3,3,2,1,1,2,2,3,3,2,2,2,2,3,1,3,1,1,3,3,1,3,3,2,1,2,1,3,3,1,1,1,2,2,4,4,
        3,2,1,3,1,2,1,1,2,3,3,4,3,1,3,2,4,3,2,3,1,2,2,2,3,2,3,1,3,2,3,3,3,2,4,3,1,3,1,2,
        4,1,1,2,4,2,1,1,3,2,1,1,1,2,4,3,2,3,3,2,2,2,1,4,3,3,1,3,4,4,3,1,3,2,3,3,2,3,1,1,
        2,1,3,2,3,1,3,1,4,4,3,3,4,2,3,1,2,3,2,3,3,2,2,2,1,3,1,2,3,2,3,3,1,2,1,2,3,3,2,3,
        3,3,2,3,1,2,3,2,3,1,2,3,3,2,2,1,1,3,3,2,1,2,1,3,3,2,1,3,1,3,2,2,3,3,2,3,3,2,3,1,
        2,3,2,3,1,1,3,1,3,3,1,3,2,3,2,3,3,2,3,2,2,2,2,3,1,3,2,4,2,1,2,2,3,3,2,1,3,3,2,2,
        3,2,2,4,3,3,3,1,2,3,1,3,3,1,2,2,2,3,2,1,1,1,3,1,2,3,1,3,1,3,1,1,4,2,3,3,1,4,1,3,
    )

    val numberListThemes = listOf(
        1,2,2,2,3,4,5,5,5,2, 7,8,9,9,9,21,24,11,12,12,
        1,2,2,2,3,4,5,5,5,15, 7,8,9,9,9,22,17,11,12,13,
        1,2,2,2,3,18,5,5,5,15, 7,8,9,9,9,19,20,11,12,13,
        1,2,2,2,3,4,5,5,5,15, 7,8,9,23,9,21,17,11,12,13,
        1,2,2,2,3,4,5,5,5,6, 7,8,4,9,9,22,10,11,12,12,

        1,2,2,2,3,4,5,5,5,6, 7,8,4,9,18,23,20,11,12,25,
        14,2,2,2,3,4,5,5,5,6, 7,8,9,9,9,21,24,11,12,13,
        14,2,2,2,4,4,5,5,5,15, 15,8,9,9,9,19,10,11,12,13,
        14,2,2,2,3,4,5,5,5,15, 15,8,9,9,9,19,20,11,12,12,
        1,2,2,2,3,9,5,5,5,12, 7,8,9,9,9,22,10,11,10,13,

        1,2,2,2,3,18,5,5,5,15, 7,8,9,9,9,6,10,25,12,12,
        14,2,2,2,3,4,5,5,5,6, 7,8,4,9,9,19,10,11,12,13,
        1,2,2,2,3,4,5,5,5,6, 7,8,9,9,2,16,10,11,12,13,
        1,2,2,2,3,4,5,5,5,15, 7,8,9,9,9,19,17,11,12,12,
        1,2,2,2,3,4,26,5,5,6, 7,8,9,9,2,16,10,11,12,13,

        14,2,2,2,3,4,26,5,5,6, 7,8,9,9,9,19,24,11,12,13,
        1,2,2,2,3,4,26,5,5,6, 7,8,9,9,9,16,17,25,12,12,
        1,2,2,2,3,4,5,5,5,6, 7,8,9,9,9,16,17,25,12,12,
        1,2,2,2,3,18,5,5,5,6, 15,8,9,9,9,0,10,25,12,13,
        14,2,2,2,3,4,26,5,5,15, 7,2,9,9,9,16,10,25,12,12,

        14,2,2,2,3,4,5,5,5,15, 7,8,9,9,9,21,10,25,12,13,
        1,2,2,2,3,18,5,5,5,15, 7,8,9,9,9,21,10,11,12,12,
        1,2,2,2,3,4,5,5,5,15, 7,8,4,9,2,8,10,25,12,12,
        14,2,2,2,3,4,5,5,5,6, 7,8,9,9,9,22,10,11,12,13,
        1,2,2,2,3,4,5,5,5,6, 7,8,4,9,9,22,10,11,12,13,

        1,2,2,2,3,4,5,5,5,15, 7,8,9,9,9,23,24,11,12,12,
        14,2,2,2,3,4,5,5,5,15, 7,8,4,9,9,22,17,25,12,12,
        14,2,2,2,3,4,26,5,5,15, 7,8,9,9,9,19,10,11,12,12,
        1,2,2,2,3,4,5,5,5,15, 7,8,9,9,9,19,10,11,12,13,
        1,2,2,2,3,4,5,5,5,15, 7,8,9,9,9,23,10,25,12,12,

        14,2,2,2,3,4,5,5,5,6, 7,8,9,9,9,19,10,11,12,12,
        14,2,2,2,3,4,5,5,5,6, 7,8,9,9,9,19,24,11,12,13,
        1,2,2,2,3,18,5,5,5,15, 7,8,9,9,9,19,10,25,12,12,
        1,2,2,2,3,4,26,5,5,15, 15,8,9,9,9,22,10,25,12,13,
        1,2,2,8,3,4,26,5,5,6, 7,8,9,9,9,22,10,11,12,12,

        1,2,2,2,3,18,5,5,5,15, 7,8,9,9,9,21,10,25,12,12,
        14,2,2,2,3,4,5,5,5,5, 7,8,9,9,9,19,20,11,12,13,
        1,2,2,2,3,18,5,5,5,6, 7,8,9,9,9,23,24,25,12,13,
        14,2,2,2,3,4,26,5,5,6, 7,8,9,9,9,19,17,11,12,12,
        14,2,2,2,3,4,26,5,5,15, 7,8,9,9,9,22,17,25,12,13,
    )
    val numberList2Themes = listOf(
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,8,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,

        0,0,0,0,0, 0,0,0,0,0, 0,0,5,0,0, 0,0,0,0,0,
        0,0,3,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,

        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 21,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,18,0, 0,0,0,0,0,
        0,0,0,0,0, 22,0,0,0,0, 0,0,0,0,9, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,9, 0,0,0,0,0,

        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,8,0,0,0, 22,0,0,0,0,

        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,9, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,

        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,8,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,

        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,6,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,

        0,0,8,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,8,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
        0,0,0,8,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0,
    )

}