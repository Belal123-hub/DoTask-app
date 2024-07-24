package com.example.project2.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.project2.R
import com.example.project2.ui.theme.nunitoFontFamily

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.todolisaticon),
            contentDescription = stringResource(R.string.icon_for_first_page)
        )

        Text(
            text = stringResource(R.string.text1),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            fontFamily = nunitoFontFamily
            )
    }
}
