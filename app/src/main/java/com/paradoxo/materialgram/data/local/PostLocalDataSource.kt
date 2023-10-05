package com.paradoxo.materialgram.data.local

import com.paradoxo.materialgram.domain.model.BasePost
import com.paradoxo.materialgram.domain.model.Media
import com.paradoxo.materialgram.domain.model.Post
import com.paradoxo.materialgram.domain.model.User

class PostLocalDataSource {

    private val imageArrayList = arrayListOf(
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(1).jpeg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(1).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(2).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(3).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(4).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(5).jpeg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(5).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(10).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(6).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(7).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(8).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(9).jpg",
        "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/image%20(4).jpeg"
    )

    private val medias = listOf(
        Media(
            imageArrayList[0],
            ""
        ), Media(
            imageArrayList[1],
            ""
        ), Media(
            imageArrayList[2],
            ""
        ), Media(
            imageArrayList[3],
            ""
        ), Media(
            imageArrayList[4],
            ""
        ), Media(
            imageArrayList[5],
            ""
        )
    )

    val posts = listOf(
        Post(
            basePost = basePosts[0],
            medias = medias,
        )
    )
}

val users = listOf(
    User(
        name = "Thor",
        avatar = "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/profile-pics/thor.png",
        followers = 5000000,
        following = 100,
        posts = 1000,
        description = "God of Thunder",
        website = "https://www.example.com/thor",
        isFollowing = false,
        isMe = false
    ),
    User(
        name = "Odin",
        avatar = "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/profile-pics/odin.png",
        followers = 10000,
        following = 10,
        posts = 500,
        description = "King of Asgard",
        website = "https://www.example.com/odin",
        isFollowing = false,
        isMe = false
    ),
    User(
        name = "Lady Sif",
        avatar = "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/profile-pics/lady-sif.png",
        followers = 5000,
        following = 20,
        posts = 200,
        description = "Warrior",
        website = "",
        isFollowing = false,
        isMe = false
    ),
    User(
        name = "Mobius",
        avatar = "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/profile-pics/mobius.png",
        followers = 100000,
        following = 50,
        posts = 800,
        description = "Time Variance Authority",
        website = "https://www.example.com/Mobius",
        isFollowing = false,
        isMe = false
    ),
    User(
        name = "Hela",
        avatar = "https://raw.githubusercontent.com/K6pkus/sample-api/main/loki-files/images/profile-pics/hela.png",
        followers = 50000,
        following = 30,
        posts = 600,
        description = "Goddess of Death",
        website = "",
        isFollowing = false,
        isMe = false
    )
)

val basePosts = listOf(
    BasePost(
        description = "hj nem Odin na causa",
        likes = 616,
        comments = 110,
        time = "1h",
        user = users.last()
    ),
    BasePost(
        description = "Plural Strings em ação",
        likes = 1,
        comments = 10,
        time = "1h",
        user = users.first()
    ),
    BasePost(
        description = "O personagem Loki foi introduzido no Universo Cinematográfico Marvel (MCU) no filme Thor (2011), e Hiddleston foi escalado para o papel depois que o diretor Kenneth Branagh ficou impressionado com sua audição. Hiddleston voltou a interpretar Loki em Thor: The Dark World (2013), Thor: Ragnarok (2017), Avengers: Infinity War (2018) e Avengers: Endgame (2019). Em setembro de 2018, a Marvel Studios estava desenvolvendo uma série limitada centrada em Loki e estrelada por Hiddleston. Waldron foi contratado em fevereiro de 2019, e Herron foi contratada no mês seguinte. A fotografia principal começou em janeiro de 2020, mas foi interrompida em março devido à pandemia COVID-19. A produção foi retomada em setembro e foi concluída em dezembro.",
        likes = 5500,
        comments = 100,
        time = "1h",
        user = users[1]
    ),
    BasePost(
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget ultricies aliquam, nunc nisl aliquet nunc, quis aliquam nisl",
        likes = 100,
        comments = 10,
        time = "1h",
        user = users[2]
    ),
    BasePost(
        description = "O tempo está acabando para o Deus da Trapaça. ⌛\n Assista ao novo trailer de #Loki, uma série Original da Marvel Studios.",
        likes = 5464,
        comments = 87,
        time = "1h",
        user = users[3]
    )
)