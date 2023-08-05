# Simple Search

## Description

The simple search is a command line driven text search engine written in java. This tool read all the text files in the given directory, building an in-memory representation of the files
and their contents (inverted index), and then give a command prompt at which interactive searches can be performed.

## Requirements

Tools used:
- OpenJDK 17.0.5
- Apache Maven 3.9.0
- Docker 20.10.20

## Tests

You can run unit tests with the following command: 

 ```shell
 mvn test
 ```

The code coverage report will be generated in the `target/site` repository. 

 ## Build

If you want to build the application with maven, run the following command:

```shell
mvn clean package
```

The generated application will be available under the `target` folder.

## Running Cli

You can run the Cli with the following command:

```shell
java -jar target/simple-search.jar [searchPath]
```

### Docker

If you want to build the application with Docker, run the following command:

```shell
docker build -t simple-search .
```

And then, execute with:

```shell
docker run -it -v [localSearchPath]:/search simple-search .
```
