# Introduction

LAM is a service that provides a Large Action Model for applications with extreme ease and flexibility.

# How to run for developers

## Requirements

- Docker engine running
- Gemini API token

## Configuration

- Docker: this project uses TestContainers library to do its integration testing. The only requirement is to run docker in the background. The rest will be handled automatically.
- Gemini Token: To configure a local gemini token go to google [ai studio](https://aistudio.google.com/app/apikey) and get an api token, then go to llm-client-prime in `/src/test/resources` create a new file called `secrets-test.properties` and add the following property `API_KEY=(your api key here)`

Run the tests to ensure that everything is working correctly.