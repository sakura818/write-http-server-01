# HTTPサーバ [![CircleCI](https://circleci.com/gh/sakura818/write-http-server-01.svg?style=svg)](https://circleci.com/gh/sakura818/write-http-server-01)

## Description

HttpServer

## Requirement

- Java 1.8.0
- Gradle 3.4.1

## Usage

1. ターミナルでgit cloneしたフォルダに移動
2. ターミナルで./gradlew runと入力
3. ブラウザでhttp://localhost:8080/index.html などを入力

## Installation

    $ git clone https://github.com/sakura818/write-http-server-01

## Anything Else

- ポートは8080番を使用しています。
- 簡易的な機能しかありません。
- リクエストを同時に処理はできません。
- ファイルの文字コードはUTF-8を使用してください。
- 1GBのファイルでも対応することができます。
1GBのダミーファイルの作り方は(Macの場合)、ターミナルで以下のように入力すると作成できます。
$ dd if=/dev/zero of=largeCapacity.txt bs=1073741824 count=1

## Author

sakura818

## License

[MIT]
