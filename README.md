# quarkus-holidays-ai

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-holidays-ai-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not 
compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- LangChain4j ([guide](https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html)): Provides the basic integration with LangChain4j
- LangChain4j OpenAI ([guide](https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html)): Provides the basic integration with LangChain4j

## Using this project as a demo or tutorial

### Getting Started

Although the intent of this project was to learn about using LangChain4j with Quarkus, this project can be built and deployed, the following documentation will take you through a step-by-step 
process to recreate it as a demo or use it as a tutorial to build your own.  So, dig in and have fun.

- [01 Getting Started](./docs/01-getting-started.md)
- [02 Working with Memory](./docs/02-working-with-memory.md)
- [03 Working with Agents and Tools](./docs/03-working-with-agents-and-tools.md)
- [04 Working with RAG](./docs/04-working-with-rag.md)
- [05 Working with Advanced RAG](./docs/05-working-with-advanced-rag.md)
- [06 Working with Images](./docs/06-working-with-images.md)
- [07 Adding Guardrails](./docs/07-adding-some-guardrails.md)

What did we explore with this exercise?

- We integrated an LLM into a Quarkus application
- Worked with prompts, chains and memory and used annotations to pass prompts and structure interactions
- Used functions to interact with the system components
- Leveraged easy RAG and RAG using a vectorized database
- Implemented guardrails to mitigate misbehaviors

We basically touched on the tip of the iceberg regarding what Quarkus+Langchain4j could do.  To learn from a more comprehensive
set of tutorials, please see the credits section below.

## Credits

Much/most of this was taken and repurposed from these wonderful source/tutorial materials:

- [Quarkus Tutorial](https://redhat-developer-demos.github.io/quarkus-tutorial/quarkus-tutorial/index.html)
- [Reactive Systems In Java](https://github.com/cescoffier/reactive-systems-in-java)
- [Quarkus LangChain4j Workshop](https://github.com/cescoffier/quarkus-langchain4j-workshop)
- [Devoxx Java Meets AI: Build LLM-Powered Apps with LangChain4j By Deandrea, Andrianakis, Escoffier](https://www.youtube.com/watch?v=jzuP6l54kWA)
- [Quarkusio Quarkus Insights #163: Latest in Quarkus LangChain4j](https://www.youtube.com/watch?v=EeR_8HMFwN4)
- [Adam Bien Java, LLMs, and Seamless AI Integration with langchain4j, Quarkus and MicroProfile](https://www.youtube.com/watch?v=jze1vH2rZ2U)

