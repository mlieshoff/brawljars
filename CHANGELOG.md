## Changelog

## [Unreleased]

## [4.0.0] - 2023-12-18

### Added

- new dependency supercell-api-wrapper-essentials

### Changed

- classes in package brawljars.api.* moved to supercell.api.wrapper.essentials.api.*
- classes in package brawljars.connector moved to supercell.api.wrapper.essentials.connector
- classes in package brawljars.api.connector moved to supercell.api.wrapper.essentials.api.connector
- classes in package brawljars.build moved to supercell.api.wrapper.essentials.build
- classes in package brawljars.common moved to supercell.api.wrapper.essentials.common

## [3.0.8] - 2023-12-18

### Added

- Maven enforcer plugin -> Minimum Maven version is now 3.8.8

### Changed

- Lombok from 1.18.24 to 1.18.30
- JaCoCo from 0.8.10 to 0.8.11
- Apache Commons IO from 2.11.0 to 2.15.1
- JUnit from 5.8.2 to 5.10.1
- SLF4j from 1.8.0-beta2 to 2.0.9
- Mockito from 4.5.1 to 5.8.0
- WireMock from 2.27.2 to 3.0.1
- GSON from 2.9.0 to 2.10.1
- Maven Source Plugin from 3.0.1 to 3.3.0
- Maven Surefire Plugin from 2.22.2 to 3.2.3

## [3.0.7] - 2023-12-12

### Removed

- Qodana

## [3.0.6] - 2023-09-28

### Added

- Test Coverage via Qodana

## [3.0.5] - 2023-09-26

### Added

- Release version check on build time

## [3.0.4] - 2023-09-23

### Added

- Qodana code quality support via actions
- Badges for Nightlies and Qodana

## [3.0.3] - 2023-09-23

### Added

- Usage of Java 17 HTTP Client

### Fixed

- Some minor code cleaning regarding Java 17 features

### Changed

- ApiContext to be a record

### Removed

- Apache HTTP Client dependency
- Some redundant overridden methods in request objects

## [3.0.2] - 2023-07-12

### Added

- Create release job #41

[unreleased]: https://github.com/mlieshoff/brawljars/compare/v4.0.0...HEAD
[4.0.0]: https://github.com/mlieshoff/brawljars/compare/v3.0.8...v4.0.0
[3.0.8]: https://github.com/mlieshoff/brawljars/compare/v3.0.7...v3.0.8
[3.0.7]: https://github.com/mlieshoff/brawljars/compare/v3.0.6...v3.0.7
[3.0.6]: https://github.com/mlieshoff/brawljars/compare/v3.0.5...v3.0.6
[3.0.5]: https://github.com/mlieshoff/brawljars/compare/v3.0.4...v3.0.5
[3.0.4]: https://github.com/mlieshoff/brawljars/compare/v3.0.3...v3.0.4
[3.0.3]: https://github.com/mlieshoff/brawljars/compare/v3.0.2...v3.0.3
[3.0.2]: https://github.com/mlieshoff/brawljars/compare/v3.0.2...v3.0.2