package com.masterfixx.shebercrm.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.masterfixx.shebercrm.model.LeadStatus
import com.masterfixx.shebercrm.ui.theme.OnStatusChip
import com.masterfixx.shebercrm.ui.theme.SheberCRMTheme
import com.masterfixx.shebercrm.ui.theme.Spacing
import com.masterfixx.shebercrm.ui.theme.StatusInProgress
import com.masterfixx.shebercrm.ui.theme.StatusInstalled
import com.masterfixx.shebercrm.ui.theme.StatusNew

// Переиспользуется в LeadCard (список) и на экране деталей.
@Composable
fun StatusChip(status: LeadStatus, modifier: Modifier = Modifier) {
    val color = when (status) {
        LeadStatus.NEW -> StatusNew
        LeadStatus.IN_PROGRESS -> StatusInProgress
        LeadStatus.INSTALLED -> StatusInstalled
    }
    Text(
        text = status.label,
        color = OnStatusChip,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
            .background(color, RoundedCornerShape(Spacing.md))
            .padding(horizontal = Spacing.sm, vertical = Spacing.xs)
    )
}

@Preview(showBackground = true)
@Composable
private fun StatusChipPreview() {
    SheberCRMTheme {
        StatusChip(status = LeadStatus.IN_PROGRESS)
    }
}