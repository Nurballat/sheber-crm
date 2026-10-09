package com.masterfixx.shebercrm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.masterfixx.shebercrm.model.Lead
import com.masterfixx.shebercrm.model.LeadStatus
import com.masterfixx.shebercrm.model.sampleLeads
import com.masterfixx.shebercrm.ui.screens.AddLeadScreen
import com.masterfixx.shebercrm.ui.screens.LeadDetailScreen
import com.masterfixx.shebercrm.ui.screens.LeadListScreen

// Названия маршрутов в одном месте, чтобы не писать строки руками в разных файлах.
private object Routes {
    const val LIST = "list"
    const val ADD = "add"
    const val DETAIL = "detail/{leadId}"
    fun detail(leadId: Int) = "detail/$leadId"
}

@Composable
fun SheberNavHost() {
    val navController = rememberNavController()

    // Состояние списка заявок поднято сюда (state hoisting), в общего "родителя"
    // экранов List/Detail/Add — поэтому все три экрана видят одни и те же данные.
    // mutableStateListOf, а не обычный remember { mutableStateOf(list) }, потому что
    // нам нужно добавлять элементы (list.add(...)), а не заменять весь список целиком.
    val leads = remember { mutableStateListOf(*sampleLeads.toTypedArray()) }

    NavHost(navController = navController, startDestination = Routes.LIST) {
        composable(Routes.LIST) {
            LeadListScreen(
                leads = leads,
                onLeadClick = { leadId ->
                    navController.navigate(Routes.detail(leadId))
                },
                onAddClick = {
                    navController.navigate(Routes.ADD)
                }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("leadId") { type = NavType.IntType })
        ) { backStackEntry ->
            val leadId = backStackEntry.arguments?.getInt("leadId") ?: -1
            LeadDetailScreen(
                leadId = leadId,
                leads = leads,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.ADD) {
            AddLeadScreen(
                onBackClick = { navController.popBackStack() },
                onSaveClick = { name, phone, address, objectType, cameraCount, cameraType, recorder ->
                    val newId = (leads.maxOfOrNull { it.id } ?: 0) + 1
                    leads.add(
                        Lead(
                            id = newId,
                            name = name,
                            phone = phone,
                            address = address,
                            objectType = objectType,
                            cameraCount = cameraCount,
                            cameraType = cameraType,
                            recorder = recorder,
                            status = LeadStatus.NEW,
                            createdAt = "Сегодня"
                        )
                    )
                    navController.popBackStack()
                }
            )
        }
    }
}