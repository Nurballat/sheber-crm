package com.masterfixx.shebercrm.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.masterfixx.shebercrm.model.Lead
import com.masterfixx.shebercrm.model.sampleLeads
import com.masterfixx.shebercrm.ui.components.SectionHeader
import com.masterfixx.shebercrm.ui.components.StatusChip
import com.masterfixx.shebercrm.ui.theme.SheberCRMTheme
import com.masterfixx.shebercrm.ui.theme.Spacing
import androidx.compose.ui.unit.dp

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeadDetailScreen(
    leadId: Int,
    leads: List<Lead> = sampleLeads,
    onBackClick: () -> Unit
) {
    // Находим заявку по id, который пришёл через навигацию.
    val lead = leads.find { it.id == leadId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Заявка #${leadId}") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        if (lead == null) {
            Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Spacing.md)) {
                Text("Заявка не найдена")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(Spacing.md)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(Spacing.sm))
                            SectionHeader("КЛИЕНТ")
                        }
                        StatusChip(status = lead.status)
                    }
                    Text(lead.name, style = MaterialTheme.typography.titleMedium)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.width(16.dp))
                        Spacer(modifier = Modifier.width(Spacing.xs))
                        Text(lead.phone, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(Spacing.md)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Home, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(Spacing.sm))
                        SectionHeader("ОБЪЕКТ")
                    }
                    Text(lead.objectType, style = MaterialTheme.typography.titleMedium)
                    Text(lead.address, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(Spacing.md)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Videocam, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(Spacing.sm))
                        SectionHeader("УСЛУГА")
                    }
                    Text("Установка видеонаблюдения", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.width(Spacing.sm))
                    InfoRow("Количество камер", lead.cameraCount.toString())
                    InfoRow("Тип камеры", lead.cameraType)
                    InfoRow("Регистратор", lead.recorder)
                }
            }

            if (lead.notes.isNotBlank()) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(Spacing.md)) {
                        SectionHeader("КОММЕНТАРИЙ")
                        Text(lead.notes, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LeadDetailScreenPreview() {
    SheberCRMTheme {
        LeadDetailScreen(leadId = 1, onBackClick = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LeadDetailScreenPreviewDark() {
    SheberCRMTheme(darkTheme = true) {
        LeadDetailScreen(leadId = 2, onBackClick = {})
    }
}