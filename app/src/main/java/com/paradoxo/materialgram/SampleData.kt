package com.paradoxo.materialgram


val imageArrayList = arrayListOf(
    "https://br.web.img2.acsta.net/pictures/21/05/12/17/18/2995518.jpg",
    "https://s2-techtudo.glbimg.com/7qzY1aLEzujgplYOee8Cl9llf7M=/0x0:1143x760/924x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2023/X/o/fuEkmnRmK6K0oFQElqiw/loki-season-2-marvel-techtudo-03.jpg",
    "https://disneyplusbrasil.com.br/wp-content/uploads/2022/07/Loki-Disney-Plus.jpg",
    "https://hips.hearstapps.com/hmg-prod/images/loki5-0loki-episode-3-loki-tom-hiddleston-sylvie-sophia-di-martino-2-1624435689.jpg",
    "https://hips.hearstapps.com/hmg-prod/images/ke-huy-quan-loki-season-2-64c7bc6510c88.jpg?crop=0.583xw:0.875xh;0.242xw,0.0625xh&resize=1200:*",
    "https://scontent-gru2-2.xx.fbcdn.net/v/t39.30808-6/220659169_4079585812117574_4005665219047029102_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=c2c701&_nc_ohc=bt-I39WOk1MAX_gCnHd&_nc_ht=scontent-gru2-2.xx&oh=00_AfAK5S4tQF7hj5QgZypgSrFr-WDPtN9hye5IcpvY9IL4xA&oe=65149FDF",
    "https://tm.ibxk.com.br/2023/08/11/11115053447089.jpg?ims=1200x675"
)

val medias = listOf(
    Image(
        imageArrayList[0],
        ""
    ), Image(
        imageArrayList[1],
        ""
    ), Image(
        imageArrayList[2],
        ""
    ), Image(
        imageArrayList[3],
        ""
    ), Image(
        imageArrayList[4],
        ""
    ), Image(
        imageArrayList[5],
        ""
    ), Image(
        imageArrayList[6],
        ""
    )
)

val posts = listOf(
    Post(
        medias.shuffled(),
        "Loki é uma série de televisão americana criada por Michael Waldron para o serviço de streaming Disney+, baseada no personagem de mesmo nome da Marvel Comics. Ela se passa no Universo Cinematográfico Marvel (MCU), compartilhando continuidade com os filmes da franquia, e é a terceira série da Fase Quatro do MCU. A série é produzida pela Marvel Studios, com Waldron servindo como roteirista principal e Kate Herron como diretora. Tom Hiddleston reprisa seu papel como Loki da série de filmes, com Owen Wilson, Gugu Mbatha-Raw, Wunmi Mosaku, Eugene Cordero, Tara Strong e Sophia Di Martino também estrelando. Em Loki, o Deus da Trapaça escapa com o Tesseract durante os eventos de Vingadores: Ultimato (2019) e é levado para a Autoridade de Variação Temporal (AVT), uma organização burocrática que existe fora do tempo e do espaço e monitora a linha do tempo. Ele é forçado a consertar as rachaduras na linha do tempo que ele causou, com a ajuda de uma variante feminina de si mesmo.",
        323,
        10,
        "1h"
    ),
    Post(
        listOf(medias.first()),
        "O personagem Loki foi introduzido no Universo Cinematográfico Marvel (MCU) no filme Thor (2011), e Hiddleston foi escalado para o papel depois que o diretor Kenneth Branagh ficou impressionado com sua audição. Hiddleston voltou a interpretar Loki em Thor: The Dark World (2013), Thor: Ragnarok (2017), Avengers: Infinity War (2018) e Avengers: Endgame (2019). Em setembro de 2018, a Marvel Studios estava desenvolvendo uma série limitada centrada em Loki e estrelada por Hiddleston. Waldron foi contratado em fevereiro de 2019, e Herron foi contratada no mês seguinte. A fotografia principal começou em janeiro de 2020, mas foi interrompida em março devido à pandemia COVID-19. A produção foi retomada em setembro e foi concluída em dezembro.",
        5500,
        100,
        "1h"
    ),
    Post(
        medias.shuffled(),
        "Loki estreou em 9 de junho de 2021, e os episódios são lançados semanalmente até 14 de julho, totalizando seis episódios. A série recebeu elogios da crítica por sua direção, roteiro, atuações (particularmente de Hiddleston, Wilson e Di Martino), humor e trilha sonora. Uma segunda temporada está em desenvolvimento.",
        100,
        10,
        "1h"
    ),
    Post(
        medias.shuffled(),
        "Aquele que permanece, também conhecido como o Homem do Tempo, é um personagem fictício que aparece nas histórias em quadrinhos americanas publicadas pela Marvel Comics. Ele é o último diretor da Autoridade de Variação Temporal (AVT) e criador dos Guardiões do Tempo. Ele é retratado como um ser benevolente que mantém a linha do tempo do universo Marvel em ordem, embora ele tenha sido retratado como um vilão em algumas histórias.",
        5464,
        87,
        "1h"
    ),
)
