package com.ninive.calculadora.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class PostViewmodels {

    data class Post(
        val userId: Int,
        val id: Int,
        val title: String,
        val body: String
    )

    class PostsViewModel : ViewModel() {

        private val _posts = MutableStateFlow<List<Post>>(emptyList())
        val posts: StateFlow<List<Post>> = _posts

        init {
            loadPosts()
        }

        private fun loadPosts() {
            viewModelScope.launch {
                // Aquí simulas la data que pasaste
                _posts.value = listOf(
                    Post(1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                        "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"),
                    Post(1, 2, "qui est esse",
                        "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"),
                    Post(1, 3, "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                        "et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"),
                    Post(1, 4, "eum et est occaecati",
                        "ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit"),
                    Post(1, 5, "nesciunt quas odio",
                        "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque"),
                    Post(1, 6, "dolorem eum magni eos aperiam quia",
                        "ut aspernatur corporis harum nihil quis provident sequi\nmollitia nobis aliquid molestiae\nperspiciatis et ea nemo ab reprehenderit accusantium quas\nvoluptate dolores velit et doloremque molestiae"),
                    Post(1, 7, "magnam facilis autem",
                        "dolore placeat quibusdam ea quo vitae\nmagni quis enim qui quis quo nemo aut saepe\nquidem repellat excepturi ut quia\nsunt ut sequi eos ea sed quas"),
                    Post(1, 8, "dolorem dolore est ipsam",
                        "dignissimos aperiam dolorem qui eum\nfacilis quibusdam animi sint suscipit qui sint possimus cum\nquaerat magni maiores excepturi\nipsam ut commodi dolor voluptatum modi aut vitae"),
                    Post(1, 9, "nesciunt iure omnis dolorem tempora et accusantium",
                        "consectetur animi nesciunt iure dolore\nenim quia ad\nveniam autem ut quam aut nobis\net est aut quod aut provident voluptas autem voluptas"),
                    Post(1, 10, "optio molestias id quia eum",
                        "quo et expedita modi cum officia vel magni\ndoloribus qui repudiandae\nvero nisi sit\nquos veniam quod sed accusamus veritatis error")
                )
            }
        }
    }
}