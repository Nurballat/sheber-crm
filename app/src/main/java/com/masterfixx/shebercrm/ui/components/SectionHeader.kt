package com.masterfixx.shebercrm.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.masterfixx.shebercrm.ui.theme.SheberCRMTheme
import com.masterfixx.shebercrm.ui.theme.Spacing

// Переиспользуется: "КЛИЕНТ", "ОБЪЕКТ", "УСЛУГА" на экране деталей
// и на экране добавления заявки — везде один и тот же стиль подписи секции.
@Composable
fun SectionHeader(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(bottom = Spacing.xs)
    )
}

@Preview(showBackground = true)
@Composable
private fun SectionHeaderPreview() {
    SheberCRMTheme {
        SectionHeader(text = "КЛИЕНТ")
    }
}