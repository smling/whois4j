[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=smling_whois4j&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=smling_whois4j)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=smling_whois4j&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=smling_whois4j)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=smling_whois4j&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=smling_whois4j)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=smling_whois4j&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=smling_whois4j)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=smling_whois4j&metric=coverage)](https://sonarcloud.io/summary/new_code?id=smling_whois4j)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=smling_whois4j&metric=bugs)](https://sonarcloud.io/summary/new_code?id=smling_whois4j)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=smling_whois4j&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=smling_whois4j)

# whois4j
Maven-based library project for whois lookup.

## Why whois4j?
Compare with existing Java library. there are several advantage as below:

|                   | Apache Common Library            | Whois XML API                          | whois4j                                          |
|-------------------|----------------------------------|----------------------------------------|--------------------------------------------------|
| Data source       | Less. Only lookup whois.nina.org | Variant, but not mentioned in website. | Variant source depends on domain name root zone. |
| API Key required  | No                               | Yes                                    | No                                               |
| Structural Data   | No                               | Yes                                    | To-be support                                    |
| Geo location Data | No                               | Yes                                    | To-be support                                    |

# Installation
See [GitHub Package](https://github.com/smling/whois4j/packages/1793913).

# Development
You can fork or clone this repository to customize and fit your needed.
## Compile
```shell
mvn clean install
```
