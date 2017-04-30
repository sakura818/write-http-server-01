# HTTPサーバ [![CircleCI](https://circleci.com/gh/sakura818/write-http-server-01.svg?style=svg)](https://circleci.com/gh/sakura818/write-http-server-01)

## Description

HTTPサーバ

***DEMO:***

![Demo]

## Requirement

- Java 1.8.0
- Gradle 3.4.1

## Usage

1. ターミナルでgit cloneしたフォルダに移動
2. ターミナルで./gradlew runと入力
3. ブラウザでhttp://localhost:8080/hello.html などに移動

## Installation

    $ git clone https://github.com/sakura818/write-http-server-01

## Anything Else

ポートは8080番を使用しています。
write-http-server-01/src/main/java/Documentに表示できるデータが入っています。
HTTP/1.1しか想定していないためHTTP/1.0やHTTP/2には応答しない可能性があります。
簡易的な機能しかありません。

## Author

sakura818

## License

[MIT]
