# Working with Retrieval Augmented Generation (RAG)

Sometimes, we will require user-specific data beyond the training set used for the LLM.  These can include documents and data
from various sources.  This is where RAG is typically employed.  

Using RAG, we can combine our own data with the LLM to enrich the possibilities offered by the application.

Following, we're going to embed a document for the LLM, which will use it to determine rules.

## Adding the Websockets and Context Propagation Extensions

For this first pass at RAG, we will take the **easy** way out and employ LangChain4j Easy RAG.

````Bash
quarkus ext add websockets-next langchain4j-easy-rag
````

## Add properties to our configuration

````Java

quarkus.langchain4j.openai.chat-model.temperature=0.0
quarkus.langchain4j.easy-rag.path=src/main/resources/catalog

costume.minpersonsage=12
costume.maxpersonage=20
costume.type=superhero
costume.name=Thor
costume.ownerfirstname=Eric
costume.ownerlastname=Deandrea
costume.id=123-456

quarkus.langchain4j.openai.chat-model.model-name=gpt-4o
````

1. The "temperature" parameter in an AI language models controls the randomness of text generation.  Lower values result in 
    more predictable outputs, while higher values encourage creativity and diversity in responses.
2. Path is literally the path to where documents are stored for the Reetrieval Augmentation Generation (ie. the documents the Ai model will use to build local knowledge.)
3. Sample costume data
4. The specific model to use. **IMPORTANT:** gpt3.5-turbo is much cheaper but results will be slower and less reliable

## Embedding the document

Copy and paste the following into a document under **src/main/resources/catalog** with the name halloween-superhero-costume.txt.

````Text
Rules for Creating a Halloween SuperHero Costume
This document outlines the guidelines for creating a Halloween costume inspired by a superhero, for individuals aged 12 to 20. Whether you're aiming for authenticity or a creative twist, these rules will help you craft the perfect look.
Costume Guidelines
1. Character Authenticity:
Your costume should reflect the traditional appearance of the superhero as depicted in comic books and movies. This includes elements such as their armor, cape, color and props.
2. Age-Appropriate Design:
Ensure that your costume is suitable for your age group. It should be fun and creative without being overly revealing or inappropriate.
3. Safety Considerations:
When creating your costume, prioritize safety. Avoid materials that could cause injury, such as sharp edges or heavy items. If using props like Mjolnir, ensure they are lightweight and won’t pose a danger to yourself or others.
4. DIY vs. Store-Bought:
You can either create your costume from scratch (DIY) or purchase pieces from stores. If purchasing, choose high-quality items that represent the character well.
5. Costume Accessories:
Incorporate accessories like Thor's helmet, a cape, and boots. Use colors typically associated with the superhero.  In example, the superhero Thor is associated with: red, silver, blue, and black.
6. Personal Touch:
Feel free to add your own flair to the costume. This could include custom designs, unique materials, or additional superhero elements.
7. Group Costumes:
If you're dressing up with friends, consider coordinating your costumes to represent a group of superheroes from the Marvel or DC universe.
8. Respectful Representation:
Be mindful of cultural and personal sensitivities when representing the superhero, especially considering the characters background, setting and the character's origins.
9. Comfort and Mobility:
Ensure that your costume allows for easy movement and is comfortable to wear for extended periods, especially if you'll be participating in activities or events.
10. Enjoyment:
Above all, have fun with your costume! Embrace the spirit of Halloween and enjoy the creative process.
Conclusion
Following these guidelines will help you create an exciting and appropriate superhero costume for Halloween. Remember to showcase your creativity and enjoy the festivities!
````