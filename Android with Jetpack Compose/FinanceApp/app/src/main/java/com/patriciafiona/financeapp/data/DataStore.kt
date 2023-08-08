package com.patriciafiona.financeapp.data

import com.patriciafiona.financeapp.R
import com.patriciafiona.financeapp.data.entities.Transaction
import com.patriciafiona.financeapp.data.entities.TransactionType
import com.patriciafiona.financeapp.data.entities.User

object DataStore{
    val users: ArrayList<User> = arrayListOf(
        User(
            firstName = "Agatha",
            lastName = "Marleine",
            profilePicture = R.drawable.user_01
        ),
        User(
            firstName = "Jack",
            lastName = "Reinance",
            profilePicture = R.drawable.user_02
        ),
        User(
            firstName = "Olivia",
            lastName = "Harold",
            profilePicture = R.drawable.user_03
        ),
        User(
            firstName = "Ricky",
            lastName = "Willson",
            profilePicture = R.drawable.user_04
        ),
        User(
            firstName = "Tesha",
            lastName = "Illxartion",
            profilePicture = R.drawable.user_05
        ),
        User(
            firstName = "Ellon",
            lastName = "Musk",
            profilePicture = R.drawable.user_06
        ),
        User(
            firstName = "Hellen",
            lastName = "Denver",
            profilePicture = R.drawable.user_07
        ),
        User(
            firstName = "Sonny",
            lastName = "Kouliex",
            profilePicture = R.drawable.user_08
        ),
        User(
            firstName = "Zayn",
            lastName = "Casper",
            profilePicture = R.drawable.user_09
        ),
    )

    val recentSend: ArrayList<User> = arrayListOf(
        users[4], users[1], users[8], users[3], users[2]
    )

    val transactionsHistory: ArrayList<Transaction> = arrayListOf(
        Transaction(
            user = users[7],
            type = TransactionType.OUTGOING,
            amount = 12.63,
            date = "08-08-2023"
        ),
        Transaction(
            user = users[1],
            type = TransactionType.RECEIVED,
            amount = 8.00,
            date = "02-07-2023"
        ),
        Transaction(
            user = users[5],
            type = TransactionType.RECEIVED,
            amount = 5.99,
            date = "24-06-2023"
        ),
        Transaction(
            user = users[3],
            type = TransactionType.OUTGOING,
            amount = 3.19,
            date = "21-06-2023"
        ),
    )
}