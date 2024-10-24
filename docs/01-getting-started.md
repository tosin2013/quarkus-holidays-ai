# Getting Started

First let's create a new project using Quarkus and LangChain4j.

Tools used to create this project:

- Java 21
- Quarkus CLI
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

### Compile and run the Quarkus application the first time

Throughout this project, we will be typically running using the dev UI.

We can get this started by running the following in our IDE or other terminal:

````Bash
quarkus dev
````

This located at http://localhost:8080/q/dev-ui if using a browser.

This should look something like the following in a browser:

![01-dev-ui](../images/01-dev-ui.png)

### Postman Collection

Even though we will eventually add a UI to the project and create a chatbot, we will want to test our API calls and to do 
so, we will go ahead and create a Postman collection for those API calls. 

![02-postman-collection](../images/02-postman-collection.png)


> [!NOTE]
> You may have a personal preference to use the curl command.  Nothing is stopping you.  We just prefer a way to repeat the 
> external testing and save it off in Postman.

### Create the AI service

Create an interface for the AI service.

````Java
package com.devcorner.developers;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface Assistant {
    String chat(String message);
}
````

> [!NOTE]
> Most of the code we will be adding should continue to work with Quarkus running in dev mode.  If not, hit ctrl+c from your
> terminal and restart $ quarkus dev when you are ready.

### Creating the prompt-base resource

Let's create the implementation for the resource we want to send the prompts to.  We are really just connecting to the OpenAI
service at this point.

````Java
package com.devcorner.developers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/halloween")
public class HalloweenQuestionResource {

    @Inject
    Assistant assistant;

    @GET
    @Path("/costume")
    @Produces(MediaType.TEXT_PLAIN)
    public String whatShouldMyHalloweenCostumeBe() {
        return assistant.chat("What should my halloween costume be?");
    }
}
````
We should now be able to create a new test in Postman using the URL http://localhost:8080/halloween/costume and see what happens.

Because the AI API is non-deterministic, you will not get the exact same response each time.  We will describe how to deal with 
this later in this material.  But, for now, it should look something like the following:

![03-halloween-costume](../images/03-halloween-costume.png)

## Adding K8s Jib Extension

Let's use the Kubernetes extension to create a Kubernetes deployment file and use the Quarkus Jib Extension to create and push the container 
image to our registry.

In our case, we're assuming most folks will be using Docker and for our example, we are using minikube, but you could also
use Kind or a single node OpenShift.

````Bash
quarkus ext add quarkus-kubernetes quarkus-container-image-jib
````
### Add the configuration properties

Add the following to **application.properties**.

> [!NOTE]
> If using Docker, you may need to perform the following
> docker logout
> docker login
> Perform the web login
> It should look something like:
> ![docker-login](../images/07-halloween-docker-login.png)
> You may have to perform the following:
> In the $USER/.docker/config.json replace : "credsStore": "desktop" => "credStore":"desktop"
> This may require a docker logout and docker login again

> [!NOTE]
> When using Minikube, there is no external load balancer service
> The work around is either manually assign an external IP to the service
> in the service's yaml configuration or
> change the service-type to NodePort
> or
> with minikube, open another terminal and run $ minikube tunnel

````Java
quarkus.container-image.group=patterncatalyst
quarkus.container-image.name=quarkus-halloween-ai
quarkus.container-image.tag=1.0-SNAPSHOT
quarkus.kubernetes.service-type=load-balancer
````

### Authenticate and push the image to the registry

````Bash
docker login <default or quay.io here>
````

Now, create the artifact, build the container and push it to the registry using jib.

````Bash
quarkus image build jib --no-tests -D"quarkus.container-image.push=true"
````

Your image should now be in the registry:

![docker-hub](../images/08-docker-hub.png)

### Deploy to Kubernetes

Let's take a slight detour through deploying to Kubernetes.

When a Kubernetes extension is present in the classpath, Quarkus will scaffold a Kubernetes deployment file in your 
target/ folder during the package phase. We can apply it to deploy the application to our Kubernetes cluster:

> [!NOTE]
> You will need the kubectl or oc cli tool installed locally for the apply command below. Here are instructions to 
> install the oc tool and log in to your OpenShift Sandbox. 
> Hint: your favorite package manager (dnf/brew/choco) can likely be used for the installation. 
> Eg. dnf install kubectl or choco install kubernetes-client or choco install openshift-client

<tabs>
    <tab title="kubectl">
        <code-block lang="plain text">
            kubectl apply -f target/kubernetes/kubernetes.yml
        </code-block>
    </tab>
    <tab title="oc">
        <code-block lang="plain text">
            oc apply -f target/kubernetes/kubernetes.yml
        </code-block>
    </tab>
</tabs>

If using Minikube, you should be able to see the services and mappings to get the IP:PORT

Run $ kubectl get services to see the port assignment.

Then you should be able to run Postman and get something like the following when later running the web socket portion:

![postman-websocket](../images/09-websocket.png)

