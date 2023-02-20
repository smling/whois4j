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

|                   | Apache Common Net Library [^1]          | Whois XML API [^2]                          |                        whois4j                         |
|-------------------|-----------------------------------------|---------------------------------------------|:------------------------------------------------------:|
| Data source       | Less. <br/>(Only lookup whois.nina.org) | Variant<br/>(but not mentioned in website.) | Variant<br/>(source depends on domain name root zone.) |
| API Key required  | No                                      | Yes                                         |                           No                           |
| Structural Data   | No                                      | Yes                                         |                     To-be support                      |
| Geo location Data | No                                      | Yes                                         |                     To-be support                      |

[^1]https://github.com/NandanDesai/Apache-Commons-Net-WhoisClient

[^2]https://github.com/whois-api-llc/whois2

# Installation
It stores in GitHub repository so need to customize for custom repository

## Maven
Set tings in `setting.xml` for point to GitHub package repository.
```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub smling whois4j Packages</name>
        <url>https://maven.pkg.github.com/smling/whois4j</url>
        <releases><enabled>true</enabled></releases>
        <snapshots><enabled>true</enabled></snapshots>
    </repository>
</repositories>
```
Settings in `pom.xml` for adding dependency.
```xml
<dependency>
    <groupId>com.github.smling</groupId>
    <artifactId>whois4j</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Gradle

Settings in `build.gradle`, which will add custom repository and package.
```groove
repositories {
    maven {
        name = "GitHub smling whois4j Packages"
        url = "https://maven.pkg.github.com/smling/whois4j"
    }
}
dependencies {
    implementation('com.github.smling:whois4j:1.0.0-SNAPSHOT')
}
```
# Usage
Sample code to get whois response of domain `github.com`.
```java
import com.github.smling.WhoIsClient;

public class sample {
    public static void main(String[] args) {
        WhoIsClient whoIsClient = new WhoIsClient();
        String result = whoIsClient.lookup("github.com");
        System.out.println("whois lookup response: \n" + result);
    }
}
```
# Package version
See [GitHub Package](https://github.com/smling/whois4j/packages/1793913).