[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)
![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven.svg?label=Maven%20Central)

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/zcbbpo/Bilibili-Get">
    <img src="img/bili.jpg" alt="Logo" width="300" height="100">
  </a>

  <h3 align="center">Bilibili Video Get</h3>

  <p align="center">
    A java implements to download video for uid or cid
    <br />
  </p>
</p>




<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://s1.hdslb.com/bfs/static/jinkela/home/asserts/ic_launcher.png)

This project for java download bilibili video. If you want to get more function please see <a href="https://github.com/soimort/you-get">you-get</a>


<!-- GETTING STARTED -->
## Getting Started

Build maven and import this dependency to your project.

### Prerequisites

* java
* maven
```sh
maven clean install
```

<!-- USAGE EXAMPLES -->
## Usage

```Java
public static void main(String[]args){
    Path path = Paths.get("/Users/home/path/to/storage/video/");
    BiliBiliHelper biliBiliHelper = new BiliBiliHelper(path);
    // download all video for this user id.
    // user id like [https://space.bilibili.com/123996559]
    biliBiliHelper.downloadForUid("123996559");
    // download for avid
    biliBiliHelper.downloadForAid("av19645863");
} //Java
```

<!-- CONTACT -->
## Contact

Jim - jim.cao.jc@live.com

Project Link: [https://github.com/zcbbpo/Bilibili-Get](https://github.com/your_username/repo_name)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[stars-shield]: https://img.shields.io/github/stars/zcbbpo/Bilibili-Get.svg?style=flat-square
[stars-url]: https://github.com/zcbbpo/Bilibili-Get/stargazers
[issues-shield]: https://img.shields.io/github/issues/zcbbpo/Bilibili-Get.svg?style=flat-square
[issues-url]: https://github.com/zcbbpo/Bilibili-Get/issues
[license-shield]: https://img.shields.io/github/license/zcbbpo/Bilibili-Get.svg?style=flat-square
[license-url]: https://img.shields.io/github/license/apache/maven.svg?label=License
[product-screenshot]: https://s1.hdslb.com/bfs/static/jinkela/home/asserts/ic_launcher.png