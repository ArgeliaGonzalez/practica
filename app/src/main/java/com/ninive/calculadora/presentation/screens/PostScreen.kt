@file:OptIn(ExperimentalMaterial3Api::class)

package com.ninive.calculadora.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ninive.calculadora.presentation.viewmodels.PostViewmodels

@Composable
fun PostsScreen(viewModel: PostViewmodels.PostsViewModel = viewModel()) {
    val posts by viewModel.posts.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Posts") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(posts) { post ->
                PostItem(post)
            }
        }
    }
}

@Composable
fun PostItem(post: PostViewmodels.Post) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
        }
    }
}