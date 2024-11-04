# Additional RAG Pattern

In this section, we will learn about using the RAG pattern with our own customizations and embedding model.

## Changing the properties file

Open the **application.properties** file and remove the following configuration.  It's easiest to simply comment it out for later
reference.

````Java
quarkus.langchain4j.easy-rag.path=src/main/resources/rag
quarkus.langchain4j.easy-rag.max-segment-size=100
quarkus.langchain4j.easy-rag.max-overlap-size=25
quarkus.langchain4j.easy-rag.max-results=3
````

