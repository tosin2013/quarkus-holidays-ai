- Add a section looking at the embedding store
- Add reactive streaming result
- Add the pgvector DB with more entries to add to easy RAG
  - Add section looking at application properties
  - quarkus.langchain4j.easy-rag.max-segment-size=100
  - quarkus.langchain4j.easy-rag.max-overlap-size=25
  - quarkus.langchain4j.easy-rag.max-results=3
- Create several canned questions for the websocket interface
  - Currently, ask it for details about id 123-456 Eric Deandrea
  - Currently, ask it for details about any section in the halloween-superhero-costume.txt file
- Add the bits to return the Dall.E image
- Add several guardrails
- Currently works in minikube with jib component (works on my machine ha!)
- Make it work on OpenShift 
- The email service only works currently in dev mode
- Add bits to save the images that are precanned and precanned prompts

Ideas
- Save off several images and poems and use them as prompts for calls
- Use a different model to compare output from the first model

curl https://api.openai.com/v1/images/generations \
-H "Authorization: Bearer $OPENAI_API_KEY" \
-H "Content-Type: application/json" \
-d '{
"model": "dall-e-3",
"prompt": "Create a vibrant and playful image featuring a mannequin dressed in a single child-friendly Halloween costume. Choose a costume like a superhero, animal, or fantasy character. Position the mannequin in a well-lit store display, using eye-level perspective to create an inviting and engaging atmosphere. Use bright and contrasting colors to highlight the costume, ensuring a fun, lively, and imaginative mood. The scene should be captured in high resolution, with soft lighting that highlights all the details of the costume.",
"n": 1,
"size": "1024x1024"
}'



Flow
Introduce the end result.  In other words, here it is all running in OpenShift on Azure or something.  Ask it some questions.  Pull back a Dall.E 3 image or two.
Then let's jump into the code and explore how we built it up and what it all means when we add stuff.
As we add each section, chat with Clement about what it is and what it does while someone is coding.
Get back to the end result working on the developer machine.
Briefly explain how we put it into OpenShift
Leave behind step by step instructions so someone can do it themselves.
We can clone the repo and put it on the developerscorner github
(Optional) We might do something with the podman ai granite model.  I find it is rather big and slow on my machine.  It literally sucks up 4 - 5 gb download and then when running, i'm see it up to 20 - 23 gb on my 32 gb machine and swapping.  It gets very slow quickly.
Additionally, it uses the openai api and you will not be able to configure both the openai and granite at the same time in properties - might ask Clement how that could be done other than two separate projects

https://huggingface.co/spaces/black-forest-labs/FLUX.1-schnell

