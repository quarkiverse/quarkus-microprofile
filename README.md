# Quarkus MicroProfile 6.1 Extension

[![Build](https://github.com/quarkiverse/quarkus-microprofile/workflows/Build/badge.svg?branch=main)](https://github.com/quarkiverse/quarkus-microprofile/actions?query=workflow%3ABuild+branch%3Amain)
[![License](https://img.shields.io/github/license/quarkiverse/quarkiverse-jberet.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Central](https://img.shields.io/maven-central/v/io.quarkiverse.microprofile/quarkus-microprofile-parent?color=green)](https://search.maven.org/search?q=a:quarkus-microprofile-parent)

The Quarkus MicroProfile Extension bundles all [MicroProfile](https://microprofile.io) 6.1 specifications and 
[SmallRye](https://smallrye.io) implementations into a single extension dependency for easy consumption.

## Usage

To use the extension, add the dependency to the target project:

```xml
<dependency>
    <groupId>io.quarkiverse.microprofile</groupId>
    <artifactId>quarkus-microprofile</artifactId>
    <version>3.3.0</version>
</dependency>
```

:information_source: **Recommended Quarkus version: `3.9.0` or higher**

## Older MicroProfile Versions

| MP Version                                     | Extension Version | Quarkus Version | Java Version | Compatible Certification Request                                         |
|------------------------------------------------|-------------------|-----------------|--------------|--------------------------------------------------------------------------|
| [4.1](https://microprofile.io/compatible/4-1/) | 2.7.2.Final       | 2.7.2.Final     | 11           | [:white_check_mark:](https://github.com/eclipse/microprofile/issues/247) |
| [5.0](https://microprofile.io/compatible/5-0/) | 3.0.0.Final       | 3.0.0.Final     | 11           | [:white_check_mark:](https://github.com/eclipse/microprofile/issues/315) |
| [6.1](https://microprofile.io/compatible/6-1/) | 3.1.0             | 3.6.7           | 11           | :hourglass_flowing_sand:                                                 |
| [6.1](https://microprofile.io/compatible/6-1/) | 3.2.0             | 3.7.2           | 17           | :hourglass_flowing_sand:                                                 |
| [6.1](https://microprofile.io/compatible/6-1/) | 3.3.0             | 3.9.0           | 17           | :hourglass_flowing_sand:                                                 |
