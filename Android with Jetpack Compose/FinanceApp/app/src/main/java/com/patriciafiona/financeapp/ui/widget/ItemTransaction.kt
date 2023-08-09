package com.patriciafiona.financeapp.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.patriciafiona.financeapp.data.entities.Transaction
import com.patriciafiona.financeapp.data.entities.TransactionType
import com.patriciafiona.financeapp.ui.theme.Green
import com.patriciafiona.financeapp.utils.DateFormater
import java.util.Locale

@Composable
fun ItemTransaction(transaction: Transaction) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircleImage(
            img = transaction.user.profilePicture,
            desc = "avatar",
            size = 50,
            padding = 8
        )
        Column {
            Text(
                text = transaction.user.firstName,
                style = TextStyle(
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = DateFormater(transaction.date),
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 11.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${if(transaction.type == TransactionType.RECEIVED) {"+"} else {"-"} } ${transaction.amount}",
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    color = if(transaction.type == TransactionType.RECEIVED) { Green } else { Color.Red }
                )
            )
            Text(
                text = transaction.type.toString().lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}