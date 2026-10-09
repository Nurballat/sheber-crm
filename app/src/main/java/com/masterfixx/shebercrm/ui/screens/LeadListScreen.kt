package com.masterfixx.shebercrm.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.masterfixx.shebercrm.R
import com.masterfixx.shebercrm.model.Lead
import com.masterfixx.shebercrm.model.LeadStatus
import com.masterfixx.shebercrm.model.sampleLeads
import com.masterfixx.shebercrm.ui.components.EmptyState
import com.masterfixx.shebercrm.ui.components.LeadCard
import com.masterfixx.shebercrm.ui.theme.Spacing
import com.masterfixx.shebercrm.ui.theme.SheberCRMTheme
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

// null в списке фильтров = "Все"
private val filters: List<LeadStatus?> = listOf(null) + LeadStatus.values().toList()

private fun filterLabel(status: LeadStatus?): String = status?.label ?: "Все"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeadListScreen(
    leads: List<Lead> = sampleLeads,
    onLeadClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    // remember + mutableStateOf: выбранный фильтр — интерактивный элемент экрана.
    var selectedFilter by remember { mutableStateOf<LeadStatus?>(null) }

    val visibleLeads = if (selectedFilter == null) {
        leads
    } else {
        leads.filter { it.status == selectedFilter }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.ic_shield_logo),
                            contentDescription = "Логотип компании SHEBER TECH",
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(Spacing.sm))
                        Text("SHEBER TECH — Заявки")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Добавить заявку")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Заголовок "Заявки" + счётчик, как в мокапе
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = Spacing.md, vertical = Spacing.sm),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Заявки", style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = "${leads.size} всего",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // LazyRow — горизонтальный ряд чипов фильтра по статусу
            LazyRow(
                contentPadding = PaddingValues(horizontal = Spacing.md, vertical = Spacing.sm),
                horizontalArrangement = Arrangement.spacedBy(Spacing.sm)
            ) {
                items(filters) { status ->
                    FilterChip(
                        selected = selectedFilter == status,
                        onClick = { selectedFilter = status },
                        label = { Text(filterLabel(status)) },
                        modifier = Modifier.defaultMinSize(minHeight = 48.dp)
                    )
                }
            }

            if (visibleLeads.isEmpty()) {
                EmptyState(message = "Заявок с таким статусом пока нет")
            } else {
                // LazyColumn — список заявок, минимум 10 элементов из sampleLeads
                LazyColumn(
                    contentPadding = PaddingValues(Spacing.md),
                    verticalArrangement = Arrangement.spacedBy(Spacing.sm)
                ) {
                    items(visibleLeads, key = { it.id }) { lead ->
                        LeadCard(lead = lead, onClick = { onLeadClick(lead.id) })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LeadListScreenPreview() {
    SheberCRMTheme {
        LeadListScreen(onLeadClick = {}, onAddClick = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LeadListScreenPreviewDark() {
    SheberCRMTheme(darkTheme = true) {
        LeadListScreen(onLeadClick = {}, onAddClick = {})
    }
}