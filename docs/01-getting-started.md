# Getting Started

First let's create a new project using Quarkus and LangChain4j.

Tools used to create this project:

- Java 21
- IntelliJ EAP
- Postman
- Minikube
- Docker or Podman
- Docker or Podman Desktop
- Minikube or Kind for K8s
- OpenAI key with environment variable $OPENAI_API_KEY 
- (Optional) tmux in the terminal is helpful but obviously not required
- (Optional) Linux: tree, curl etc...
- (Optional) zsh for the terminal is visually helpful if giving a demo from this


````Bash
quarkus create app -x rest -x langchain4j-openai -x langchain4j-core com.devcorner.developers:quarkus-halloween-ai:1.0-SNAPSHOT
cd quarkus-halloween-ai
````
> [!NOTE]
> So, what is all this?
> The Quarkus LangChain4j extension allows us to seamlessly integrate Large Language Models (LLMs) into our Quarkus applications.

## Working with Prompts

One of the simplest interactions we can do to use an LLM is: Prompting.  This means, we will ask questions to the LLM and 
receive an answer in natural language from a given Model, such as OpenAI, Ollama or several others.

From this point forward, whether doing a demo or using this as a tutorial, open the project from your favorite IDE, 
I'm currently using Intellij, and you can use the terminal for the project there.  This would be the easiest, but you can 
also open a separate terminal.  I prefer tmux so, I can split the terminal instead of using tabs.  But, that is simply preference.

Whatever the case, let's get started.

### Connect with OpenAI

Open application.properties file from src/main/resources and add the following to connect to OpenAI.

````Java
quarkus.langchain4j.openai.api-key=${OPENAI_API_KEY}
````

### Create the AI service

Create an interface for the AI service.

