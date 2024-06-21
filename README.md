> [!WARNING]  
> This project has been archived and is no longer being actively developed by Elhub.

# common-konfig

## Table of Contents

* [About](#about)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Testing](#testing)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
* [Owners](#owners)
* [License](#license)


## About

**common-konfig** is a simple, extensible type-safe API for adding configuration properties from multiple sources
to a Kotlin/Java project.

## Getting Started

### Prerequisites

A Kotlin or Java project.

### Installation

Add the dependency to your project. For Gradle:

```kotlin
    implementation("no.elhub.common:common-konfig:${konfig-version})
```

Or maven:
```xml
        <dependency>
            <groupId>no.elhub.common</groupId>
            <artifactId>common-konfig</artifactId>
            <version>${konfig_version}</version>
        </dependency>
```

## Usage

You can use common-konfig in a variety of ways, but the simplest way (in Kotlin) is simply to create an object that
extends Configurable. For example:

```kotlin
import no.elhub.common.konfig.Configurable
import no.elhub.common.konfig.sources.ResourceFile

object MyConfiguration : Configurable(arrayOf(ResourceFile("my.properties"))) {
  val aProperty = property<String>("aProperty")
  val anotherProperty = property<Int>("another.property.with.weird.name")
  val thirdProperty = property<String>("databasename")
}
```

The configurable takes a number of PropertySource objects, with the precedence of the objects in the array reflecting
the precedence the Configurable will give the properties in the code. Currently the project has only a few
sources that it can handle, but it is easy to add new ones by implementing them for the `PropertySource` interface.

## Testing

To test the module, run:
```bash
./gradlew test
```

## Roadmap

See the
[open issues](https://jira.elhub.cloud/issues/?jql=project%20%3D%20TD%20AND%20component%20%3D%20common-konfig%20AND%20resolution%20%3D%20Unresolved)
for a list of proposed features (and known issues).

This project still needs additional development to add support for more types, property sources, etc.

## Contributing

Contributing, issues and feature requests are welcome. See the
[Contributing](https://github.com/elhub/common-konfig/blob/main/CONTRIBUTING.md) file.

## Owners

This project is developed by [Elhub](https://github.com/elhub). For the specific development group responsible for this
code, see the [Codeowners](https://github.com/elhub/common-konfig/blob/main/CODEOWNERS) file.

## License

This project is [MIT](https://link-to/LICENSE.md) licensed.
