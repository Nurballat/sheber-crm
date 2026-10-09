package com.masterfixx.shebercrm.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.masterfixx.shebercrm.model.Lead
import com.masterfixx.shebercrm.model.LeadStatus
import com.masterfixx.shebercrm.ui.theme.SheberCRMTheme
import com.masterfixx.shebercrm.ui.theme.Spacing

// Переиспользуется: вызывается один раз на каждый элемент LazyColumn
// (то есть используется во многих местах экрана списка).
@Composable
fun LeadCard(lead: Lead, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = lead.name,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    StatusChip(status = lead.status)
                }
                Text(
                    text = "Установка видеонаблюдения (${lead.cameraCount} камер)",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(Spacing.xs))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.width(14.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(Spacing.xs))
                    Text(
                        text = lead.address,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.width(14.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(Spacing.xs))
                    Text(text = lead.createdAt, style = MaterialTheme.typography.bodySmall)
                }
            }
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Открыть детали заявки"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LeadCardPreview() {
    SheberCRMTheme {
        LeadCard(
            lead = Lead(
                1, "Иванов Алексей", "+7 777 123 45 67",
                "Алматы, Бостандыкский район, очень длинный адрес который не влезет",
                "Частный дом", 4, "IP (Hikvision)", "NVR", LeadStatus.NEW, "28 сентября"
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LeadCardPreviewDark() {
    SheberCRMTheme(darkTheme = true) {
        LeadCard(
            lead = Lead(
                1, "Иванов Алексей", "+7 777 123 45 67",
                "Алматы, Бостандыкский район", "Офис", 8, "IP (Dahua)", "NVR", LeadStatus.IN_PROGRESS, "27 сентября"
            ),
            onClick = {}
        )
    }
}