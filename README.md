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
| Data source       | Less. <br/>(Only lookup [whois.nina.org](https://whois.iana.org/)) | Variant<br/>(but not mentioned in website.) | Variant<br/>(source depends on domain name root zone.) |
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
Executed result:
```text
whois lookup response: 
   Domain Name: GITHUB.COM
   Registry Domain ID: 1264983250_DOMAIN_COM-VRSN
   Registrar WHOIS Server: whois.markmonitor.com
   Registrar URL: http://www.markmonitor.com
   Updated Date: 2022-09-07T09:10:44Z
   Creation Date: 2007-10-09T18:20:50Z
   Registry Expiry Date: 2024-10-09T18:20:50Z
   Registrar: MarkMonitor Inc.
   Registrar IANA ID: 292
   Registrar Abuse Contact Email: abusecomplaints@markmonitor.com
   Registrar Abuse Contact Phone: +1.2086851750
   Domain Status: clientDeleteProhibited https://icann.org/epp#clientDeleteProhibited
   Domain Status: clientTransferProhibited https://icann.org/epp#clientTransferProhibited
   Domain Status: clientUpdateProhibited https://icann.org/epp#clientUpdateProhibited
   Name Server: DNS1.P08.NSONE.NET
   Name Server: DNS2.P08.NSONE.NET
   Name Server: DNS3.P08.NSONE.NET
   Name Server: DNS4.P08.NSONE.NET
   Name Server: NS-1283.AWSDNS-32.ORG
   Name Server: NS-1707.AWSDNS-21.CO.UK
   Name Server: NS-421.AWSDNS-52.COM
   Name Server: NS-520.AWSDNS-01.NET
   DNSSEC: unsigned
   URL of the ICANN Whois Inaccuracy Complaint Form: https://www.icann.org/wicf/
>>> Last update of whois database: 2023-02-20T09:57:32Z <<<

For more information on Whois status codes, please visit https://icann.org/epp

NOTICE: The expiration date displayed in this record is the date the
registrar's sponsorship of the domain name registration in the registry is
currently set to expire. This date does not necessarily reflect the expiration
date of the domain name registrant's agreement with the sponsoring
registrar.  Users may consult the sponsoring registrar's Whois database to
view the registrar's reported date of expiration for this registration.

TERMS OF USE: You are not authorized to access or query our Whois
database through the use of electronic processes that are high-volume and
automated except as reasonably necessary to register domain names or
modify existing registrations; the Data in VeriSign Global Registry
Services' ("VeriSign") Whois database is provided by VeriSign for
information purposes only, and to assist persons in obtaining information
about or related to a domain name registration record. VeriSign does not
guarantee its accuracy. By submitting a Whois query, you agree to abide
by the following terms of use: You agree that you may use this Data only
for lawful purposes and that under no circumstances will you use this Data
to: (1) allow, enable, or otherwise support the transmission of mass
unsolicited, commercial advertising or solicitations via e-mail, telephone,
or facsimile; or (2) enable high volume, automated, electronic processes
that apply to VeriSign (or its computer systems). The compilation,
repackaging, dissemination or other use of this Data is expressly
prohibited without the prior written consent of VeriSign. You agree not to
use electronic processes that are automated and high-volume to access or
query the Whois database except as reasonably necessary to register
domain names or modify existing registrations. VeriSign reserves the right
to restrict your access to the Whois database in its sole discretion to ensure
operational stability.  VeriSign may restrict or terminate your access to the
Whois database for failure to abide by these terms of use. VeriSign
reserves the right to modify these terms at any time.

The Registry database contains ONLY .COM, .NET, .EDU domains and
Registrars.
```
# Package version
See [GitHub Package](https://github.com/smling/whois4j/packages/1793913) for more details.
