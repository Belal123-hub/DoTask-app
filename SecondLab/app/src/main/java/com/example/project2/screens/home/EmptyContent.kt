package com.example.project2.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.project2.R
import com.example.project2.ui.theme.black1

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
    onCreateNewTaskClicked: () -> Unit
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.todolisaticon), contentDescription = stringResource(
                R.string.icon_for_first_page
            ),
            modifier = Modifier
                .width(500.dp)
                .height(500.dp)
        )

        Text(
            text = stringResource(R.string.text1),
            modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onCreateNewTaskClicked,
            shape = RoundedCornerShape(23.dp),
            colors = ButtonDefaults.buttonColors(black1),
            modifier = Modifier
                .size(width = 56.dp, height = 56.dp)
                .align(alignment = Alignment.End)
        ) {
            Icon(
                painter = painterResource(R.drawable.shape),
                contentDescription = stringResource(R.string.AddIcon2), modifier = Modifier.size(width = 24.dp, height = 24.dp)
            )
        }
    }
}
