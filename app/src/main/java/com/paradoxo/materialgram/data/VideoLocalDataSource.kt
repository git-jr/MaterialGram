package com.paradoxo.materialgram.data

import com.paradoxo.materialgram.domain.model.Video

class VideoLocalDataSource {
    val videos = listOf(
        Video(
            "https://github.com/K6pkus/Imgs/raw/main/video_16_9_demo.mp4",
            "Video 0",
            "https://raw.githubusercontent.com/git-jr/sample-files/main/images/android%20invaders.png"
        ),
        Video(
            "https://github.com/K6pkus/sample-api/raw/main/lv_0_20230929192816.mp4",
            "Video 1",
            "https://raw.githubusercontent.com/git-jr/sample-files/main/images/torta.png"
        ),
        Video(
            "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
            "Video 2",
            "https://raw.githubusercontent.com/git-jr/sample-files/main/images/tiles%20android.png"
        ),
        Video(
            "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
            "Video 3",
            "https://raw.githubusercontent.com/git-jr/sample-files/main/images/lollipop.png"
        ),

        )
}