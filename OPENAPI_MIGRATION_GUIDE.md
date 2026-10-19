# Java API wrapper migration to generated OpenAPI models

This document records the jcrapi2 migration to **5.0.0** and provides a reusable procedure for another Java API wrapper project. Replace project names, packages, endpoints, and schema definitions with those of the target project.

## 1. Scope and evidence

The requested starting version is 4.0.17. The available local history and changelog do not identify a 4.0.17 release: the last tagged baseline is `v4.0.15` (`ace6352`). This guide reconstructs the migration from that baseline to the inspected `5.0.0` working tree at `2568258`; it does not claim a verified 4.0.17-to-5.0.0 diff or a record of earlier conversations.

Useful migration commits include:

| Commit | Recorded subject |
| --- | --- |
| `1ef9a7e` | First shot |
| `def2314` | Save status |
| `45cb1e8` | First restructuring |
| `0377a77` | Clean up |
| `8f55292` | Apply agents.md |
| `e781f2d` | Integrate supercell essential gain |
| `f0c65b4`, `b7dfcdc` | Update tests |
| `dd02305` | Remove unused class |
| `1d6287d`, `6b76a68` | README updates |
| `2568258` | Update libraries |

The checked-in specification is **Swagger 2.0** (`swagger: "2.0"`). OpenAPI Generator consumes it directly. This migration did not convert the specification to OpenAPI 3 or replace the complete HTTP client with a generated client.

## 2. Resulting architecture

Before migration, endpoint-specific handwritten DTOs described API payloads, and an external essentials library provided transport and shared abstractions.

After migration:

- `src/main/resources/api.yaml` is the maintained source of payload models.
- Maven generates Java models under `target/generated-sources/openapi/src/main/java` in `jcrapi2.api.generated.model` and compiles them into the library JAR.
- Existing API interfaces, implementations, request builders, `Future<T>` results, pagination, and raw-response behavior remain in use.
- Response wrappers extend generated object models or contain generated collection elements.
- Shared API, connector, and common infrastructure lives locally under `jcrapi2.essentials`.
- Gson remains the JSON serializer; generated API clients and supporting files are disabled.

For another project, use this approach when preserving an existing public request/transport layer is desirable. Full client generation would be a separate architectural change.

## 3. Capture and maintain the API specification

Added `get-swagger-def.sh` to retrieve the provider's definition with Bearer authorization. It accepts a token argument or `CLASH_ROYALE_TOKEN`, defaults output to `src/main/resources/api.yaml`, and uses `curl --fail-with-body --silent --show-error`.

For another project:

1. Locate its actual specification endpoint; the jcrapi2 script requests `https://api.clashroyale.com/v1/`, which is provider-specific.
2. Obtain authorized access and keep credentials out of source control and fixtures.
3. Save the specification inside the repository and inspect it before generation.
4. Compare its models with real response snapshots. Successful generation alone does not establish payload correctness.
5. Preserve local corrections when refreshing the upstream specification; the current download script overwrites its output and does not merge corrections.

The migration corrected these schema details:

| Concern | jcrapi2 treatment | Why it matters |
| --- | --- | --- |
| Localized names | `JsonLocalizedName` represents a string | Real name fields are strings |
| Icon URLs | Shared `IconUrls`, including `heroMedium` | Preserve hero icons and nested icon data |
| Dynamic player progress | `additionalProperties` referencing `TrophyRoadProgress` | Preserve arbitrary keys, including the empty string and seasonal/event identifiers |
| Enum wire values | Correct values including `inviteOnly`, `singleChallenge`, `elder`, and `warDeckPick` | Match JSON values precisely |
| Missing arrays | Generate containers with null defaults | Preserve the distinction between absent and empty values |

The dynamic-map schema pattern is:

```yaml
PlayerProgress:
  type: object
  additionalProperties:
    $ref: '#/definitions/TrophyRoadProgress'
```

Do not replace dynamically keyed objects with a fixed list of Java properties. New provider keys would otherwise be silently discarded.

## 4. Configure model generation in Maven

Added this plugin configuration to `pom.xml`. For another project, change `modelPackage` and the specification path as needed.

```xml
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <version>7.10.0</version>
    <executions>
        <execution>
            <id>generate-api-models</id>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>${project.basedir}/src/main/resources/api.yaml</inputSpec>
                <generatorName>java</generatorName>
                <library>feign</library>
                <modelPackage>jcrapi2.api.generated.model</modelPackage>
                <output>${project.build.directory}/generated-sources/openapi</output>
                <generateApis>false</generateApis>
                <generateSupportingFiles>false</generateSupportingFiles>
                <generateApiTests>false</generateApiTests>
                <generateApiDocumentation>false</generateApiDocumentation>
                <generateModelTests>false</generateModelTests>
                <generateModelDocumentation>false</generateModelDocumentation>
                <configOptions>
                    <serializationLibrary>gson</serializationLibrary>
                    <annotationLibrary>none</annotationLibrary>
                    <useEnumCaseInsensitive>true</useEnumCaseInsensitive>
                    <containerDefaultToNull>true</containerDefaultToNull>
                    <hideGenerationTimestamp>true</hideGenerationTimestamp>
                    <useBeanValidation>false</useBeanValidation>
                    <openApiNullable>false</openApiNullable>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```

The generate goal participates in `generate-sources`. Run `mvn generate-sources` to inspect its output. Edit the schema and regenerate instead of editing generated Java files.

The `feign` template choice produces the Gson-compatible standalone models used here; it does not require adding a Feign runtime for this model-only setup. Annotation dependencies were added for generated sources:

```xml
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>1.3.2</version>
</dependency>
<dependency>
    <groupId>com.google.code.findbugs</groupId>
    <artifactId>jsr305</artifactId>
    <version>3.0.2</version>
</dependency>
```

Confirm actual generated imports before adopting these dependencies in another project, especially one using Jakarta annotations.

## 5. Adapt the existing response layer

Updated response wrappers across cards, challenges, clans, global tournaments, leaderboards, locations/rankings/seasons, players, and tournaments. Removed unused handwritten payload classes after replacing their references.

Three response shapes require separate treatment:

| JSON shape | Existing implementation example | Adaptation |
| --- | --- | --- |
| Object | `PlayerResponse`, `ClanResponse`, `CardsResponse` | Extend the matching generated model and implement `IResponse` |
| Paginated object with `items` | `ClansResponse extends PaginationResponse<Clan>` | Keep the pagination wrapper and switch its element type to a generated model |
| Top-level array | `EventsResponse extends ArrayList<TrailEvent>` | Keep a list response implementing `IResponse`, with generated elements |

Object/list wrappers retain `reason`, `message`, and transient `RawResponse` metadata. Object wrappers use Lombok equality with `callSuper = true` so inherited generated payload fields participate in comparisons.

Representative object wrapper structure:

```java
@Data
@EqualsAndHashCode(callSuper = true)
public class CardsResponse extends Items implements IResponse {
    @SerializedName("reason")
    private String reason;

    @SerializedName("message")
    private String message;

    private transient RawResponse rawResponse;
}
```

Here, `Items` is the generated cards payload model, not a generic collection class. Its `getItems()` returns generated `Item` objects.

For another project, inventory every endpoint's root JSON shape before changing wrappers. Check generated getter types, nested types, inherited equality, and collection deserialization individually. Keep metadata out of serialized API payloads.

## 6. Bring shared essentials into the project

Removed the external `supercell-api-wrapper-essentials` dependency, its version property, and its dedicated repository. Integrated the required classes under:

- `jcrapi2.essentials.api`: `Api`, `ApiContext`, `BaseApi`, `ExecutorServiceDecorator`.
- `jcrapi2.essentials.common`: request/response contracts, pagination, raw responses, and utilities.
- `jcrapi2.essentials.connector`: connector contract, request context, exception, standard HTTP connector, and filesystem cache.

Updated imports throughout production code and tests. `JCrApi` and API implementations use these local abstractions. `RequestContext` is a record with `url()`, `apiKey()`, `request()`, and `responseClass()` accessors.

Preserved transport behavior includes Bearer authentication, encoded path parameters, query parameters, asynchronous execution, Gson deserialization, optional raw response bodies/headers, and connector errors surfaced through futures. The filesystem connector caches successful non-empty responses for five minutes under `java.io.tmpdir` using a supplied prefix.

This integration accompanied the OpenAPI migration but is not a requirement of OpenAPI Generator. In another project, retain the existing shared dependency if it already supports generated models. If integrating shared code locally, preserve its licensing and test its behavior.

## 7. Add the events endpoint

Added `EventApi`, `EventApiImpl`, `EventsRequest`, and `EventsResponse`, and registered the implementation in `DefaultApiClasses`.

`GET /events` returns a top-level array of generated `TrailEvent` models. The response supports iteration directly; descriptions may be null. Added integration coverage for populated and empty arrays, error behavior, and end-to-end coverage.

This endpoint addition is specific to jcrapi2. For another wrapper, compare the provider's paths against registered APIs and add missing endpoints deliberately; generating models alone does not expose new operations through handwritten interfaces.

## 8. Strengthen tests against silent data loss

Updated shared integration assertions and endpoint tests to verify:

- Correct generated model types, including inherited object models and collection elements.
- Raw response text and headers when requested.
- Preservation of every non-null JSON field from the fixture after deserialization and serialization.
- Normal payloads, empty collections, null fields, and populated alternatives.
- Request paths, query parameters, Bearer headers, and connector failures.

The preservation assertion recursively compares fixture fields with the serialized response. It is needed because deserializing both the expected and actual result into the same incomplete model can hide dropped fields. Round-trip equality alone can also miss this problem.

Added `GeneratedModelsTest` coverage for card/support items, absent arrays, unknown additional input fields, hero icon URLs, player enums, dynamic progress keys, and model round trips.

Integrated unit tests for API context/base execution, pagination requests, utilities, standard HTTP transport, and filesystem caching. Corrected assertions referring to the old exception package and ensured WireMock servers stop after tests.

Tests use AssertJ and descriptive `<methodName>_whenXXX_thenYYY` names. Each new test has one top-level assertion invocation; shared assertion helpers may contain multiple checks.

## 9. Refresh and document fixtures

Refreshed fixtures using provider responses audited on **2026-10-16**. These are historical observations, not a statement of current service availability.

- Limited every sampled list, including nested lists, to five entries.
- Retained fields on sampled objects, nulls, empty lists, API counts, ranks, and paging cursors.
- Added compact populated alternatives for endpoints whose current responses were empty or had null season fields.
- Added events fixtures and a recorded tournament-ranking error response.
- Retained historical successful challenge/tournament-ranking responses when live data was unavailable.
- Documented provenance, unavailable routes, and sample limitations in `src/test/resources/README.md`.

At that audit, `/challenges` and tested historical tournament rankings returned 404. Legacy `/clans/{clanTag}/warlog` returned 404 with a temporary-disable message; `/currentwar` returned 410 with a permanent-removal message. Both legacy clan-war routes remain in the schema. A path existing in the specification does not establish that it works live.

For another project, keep real response snapshots independent of generated-model serialization and record the difference between historical samples, current responses, and synthetic contract examples.

## 10. Consumer-facing breaking changes

The release uses a major version because public Java types changed:

1. Replace removed handwritten DTO imports with types under `jcrapi2.api.generated.model`.
2. Replace external essentials imports with `jcrapi2.essentials` imports.
3. Update nested getter expectations, including generated enums instead of strings.
4. Access player progress through `Map<String, TrophyRoadProgress>` rather than fixed getters.
5. Review generic collection types and code relying on old constructors, builders, or setters.
6. Adapt custom connectors to the local `Connector` and `RequestContext` contracts.

Existing API interfaces, request builders, futures, pagination controls, and raw-response flags remain the intended application entry points. Payload type compatibility still requires consumer changes.

## 11. Release documentation and accompanying dependency updates

Updated `VERSION.txt` to `v5.0.0`, the Maven artifact version to `5.0.0`, changelog release notes, and README installation examples. Reworked README examples and documentation for generated models, events, pagination, connectors, custom APIs, migration, build architecture, and tests. Added `AGENTS.md` contributor instructions.

Java 21 and Maven 3.8.8 or later remain the documented build requirements. Java 21 was already configured at the inspected baseline; it was not introduced by this migration.

The final inspected POM also contains these dependency/plugin changes relative to `v4.0.15`. They are accompanying updates, not a minimum OpenAPI migration dependency set:

| Component | Baseline | Inspected final configuration |
| --- | --- | --- |
| Gson | 2.11.0 | 2.14.0 |
| Commons IO | 2.18.0 | 2.22.0 |
| JaCoCo | 0.8.12 | 0.8.15 |
| JUnit | 5.11.4 | 6.1.3 |
| Lombok | 1.18.36 | 1.18.48 |
| Maven Dependency Plugin | 3.8.1 | 3.11.0 |
| Maven Enforcer Plugin | 3.5.0 | 3.6.3 |
| Exec Maven Plugin | 3.5.0 | 3.6.4 |
| Spotless Maven Plugin | 2.44.2 | 3.10.4 |
| Maven Surefire Plugin | 3.5.2 | 3.6.0 |
| Mockito | 5.15.2 | 5.24.0 |
| SLF4J | 2.0.14 | 2.0.19 |
| WireMock | 3.10.0 | 3.13.2 |
| AssertJ | Not declared | 4.0.0-M1, test scope |
| javax.annotation-api | Not declared | 1.3.2 |
| jsr305 | Not declared | 3.0.2 |
| OkHttp | Not declared | 5.5.0 |
| OpenAPI Generator CLI | Not declared | 7.26.0 |
| OpenAPI Generator Maven Plugin | Not declared | 7.10.0 |

The Maven plugin version **7.10.0** controls this project's build-time generation. The separately declared CLI **7.26.0** does not override that version. Review whether the CLI and OkHttp need to be published as normal dependencies in a target project; the model-only plugin setup does not by itself justify copying them. Likewise, adopting AssertJ's milestone release and a JUnit major upgrade should be separate, deliberate decisions.

## 12. Reusable implementation and validation checklist

1. Record the target project's actual baseline and public compatibility requirements.
2. Inventory endpoints, root response shapes, DTO references, serializer behavior, and shared transport contracts.
3. Capture the provider specification and independent response fixtures.
4. Add pinned model-only generation to Maven and inspect the generated imports and types.
5. Correct schema mismatches using real payload evidence, including enum values, dynamic objects, icons, and null/empty semantics.
6. Adapt object, paginated, and list wrappers while preserving response metadata.
7. Update imports and consumers; remove obsolete DTOs only after checking references and following the project's deletion policy.
8. Integrate essentials only if the target project needs that architectural change.
9. Add generated-model, endpoint, transport, and error regression tests using independent fixtures.
10. Document breaking changes, examples, fixture provenance, and version metadata.
11. Format Java, verify formatting, run tests, then review the complete diff.

For a project with this Maven setup, the validation commands are:

```bash
mvn generate-sources
mvn spotless:apply
mvn spotless:check
mvn test
mvn verify
```

Use an existing Maven wrapper when the target project provides one. Keep build/cache/temp writes within the workspace if its contributor rules require that. `mvn verify` also reaches the configured version checks and source-JAR attachment; it does not deploy the artifact.

Live tests are a separate authorized step. In jcrapi2 they require `API_KEY`, suitable sample identifiers in `EndToEnd.java`, and:

```bash
mvn -PendToEnd test
```

Do not infer that historical fixture audits validate a new build or today's endpoints. This guide was prepared through local source/history inspection; no Maven build or live API tests were executed as part of this documentation-only task.

## 13. Files to use as implementation references

- `pom.xml`: generator, annotation dependencies, test dependencies, formatting, and release checks.
- `src/main/resources/api.yaml`: maintained schema and payload corrections.
- `get-swagger-def.sh`: provider-specific schema retrieval.
- `src/main/java/jcrapi2/api/intern/`: preserved APIs/requests and adapted response wrappers.
- `src/main/java/jcrapi2/api/intern/events/`: complete additional endpoint example.
- `src/main/java/jcrapi2/api/intern/DefaultApiClasses.java`: API registration.
- `src/main/java/jcrapi2/essentials/`: integrated transport and shared contracts.
- `src/test/java/jcrapi2/IntegrationTestBase.java`: independent payload-preservation checks.
- `src/test/java/jcrapi2/api/generated/model/GeneratedModelsTest.java`: schema regression coverage.
- `src/test/java/jcrapi2/essentials/`: shared infrastructure tests.
- `src/test/resources/README.md`: fixture provenance and endpoint audit limitations.
- `README.md`, `CHANGELOG.md`, `VERSION.txt`, `AGENTS.md`: consumer migration notes, release metadata, and contributor rules.

No commit, push, publication, or deployment is part of this migration procedure without explicit human authorization.
