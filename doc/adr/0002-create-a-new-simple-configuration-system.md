# 2. Create a new simple configuration system

Date: 2021-04-28

## Status

Proposed

## Context

We have a need of a simple way to configure our projects from property files, etc. Today, we make use of
common-config. However:
- common-config includes a bunch of dependencies that bleed upstream unnecessarily
- it is based on constretto, which at this point appears to be dead (v3.0 has been wip for several years at this
  point)
- it exposes the constretto API, which is unfortunate - this makes it hard to switch over to a new underlying
  property system
- it does a lot moe than we actually require for most common use cases

For all of the above reasons, we feel that it is time to get rid of common-config.

We would like to replace it with something that is similarly elegant to use, allowing us to code idiomatic
Kotlin objects. Constretto - without modifications - makes this fairly complicated (also, see issues above with
the activity on the project). Other alternatives we've considered:
- [Apache Commons Configuration](http://commons.apache.org/proper/commons-configuration/)
- [Lightbend Config](https://github.com/lightbend/config)
- [Netflix Archaius](https://github.com/Netflix/archaius)
- [Owner](http://owner.aeonbits.org/docs/usage/)
- [Konfig](https://github.com/npryce/konfig)

The Java-based projects generally do not provide an elegant API for Kotlin. The sole Kotlin-based library (Konfig)
is by a sole developer (incidentally the dev of the app we use for ADR) but no longer seems to be maintained.

One possibility could be to develop common-konfig as a Kotlin wrapper around apache commons configuration, but
without allowing the commons-configuration API to bleed through.

## Decision

We will create a new module for dynamic configuration called common-konfig, to replace the functionality
of the existing constretto-based common-config code.

## Consequences

We will move all of our Kotlin and Java projects to using common-konfig over time. We will expand the functionality
of common-konfig as we see the need for it develop.
