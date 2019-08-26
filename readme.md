[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/zcbbpo/Bilibili-Get">
    <img src="img/bili.jpg" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Bilibili Video Get</h3>

  <p align="center">
    A java implements to download video for uid or cid
    <br />
  </p>
</p>




<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://www.bilibili.com/)

This project for java download bilibili video. If you want to get more function please see <a href="https://github.com/soimort/you-get">you-get</a>


<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
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

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Jim - jim.cao.jc@live.com

Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=flat-square
[contributors-url]: https://github.com/zcbbpo/Bilibili-Get/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=flat-square
[forks-url]: https://github.com/zcbbpo/Bilibili-Get/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=flat-square
[stars-url]: https://github.com/zcbbpo/Bilibili-Get/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=flat-square
[issues-url]: https://github.com/zcbbpo/Bilibili-Get/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=flat-square
[license-url]: https://github.com/zcbbpo/Bilibili-Get/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.png