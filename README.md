# Magneto's Mutant Detection Project

API-REST Project developed to identify when a human has a mutant DNA, in order to recruit mutants to the Mangeto's army to fight against the X-Men



## Code Coverage
![image](https://user-images.githubusercontent.com/18384730/113320491-2ec22a00-92d8-11eb-9991-57435c2323a6.png)

## How do I test the API?
Only people authorized by Magneto have the API-EndPoint to identify when a human is a mutant. If you are an authorized person who has the URL, you can test the API:
- Through a ***POST***  method sending the DNA sequence in a JSON like this:
 ```JSON
 {
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
 ```
- If you want to know the stats of the project you can consume the appropiate Endpoint through a ***GET*** method.

### Project developed with Micronaut Framework
---
### Micronaut 2.4.1 Documentation
- [User Guide](https://docs.micronaut.io/2.4.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/2.4.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/2.4.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

#### Handler
[AWS Lambda Handler](https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html)

Handler: io.micronaut.function.aws.proxy.MicronautLambdaHandler

#### Feature aws-lambda documentation
- [Micronaut AWS Lambda Function documentation](https://micronaut-projects.github.io/micronaut-aws/latest/guide/index.html#lambda)

