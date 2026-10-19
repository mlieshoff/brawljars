# API response fixtures and endpoint audit

Successfully audited on 2026-10-19 through the proxy configured in
`EndToEnd.java`: `https://bsproxy.royaleapi.dev/v1`.
The provider specification and all eleven documented GET operations returned
HTTP 200. The eleven Java end-to-end tests also passed through this proxy using
an uncached connector.

| Route | Audit result | Implementation |
| --- | --- | --- |
| `/` (specification) | 200 | Not a library operation |
| `/brawlers` | 200 | Retained |
| `/brawlers/16000000` | 200 | Retained |
| `/events/rotation` | 200 | Retained |
| `/gamemodes` | 200 | Added during migration |
| `/rankings/global/players` | 200 | Retained |
| `/rankings/global/clubs` | 200 | Retained |
| `/rankings/global/brawlers/16000000` | 200 | Retained |
| `/players/%2328UP80RRY` | 200 | Retained |
| `/players/%2328UP80RRY/battlelog` | 200 | Retained |
| `/clubs/%23JUURPCV9` | 200 | Retained |
| `/clubs/%23JUURPCV9/members` | 200 | Retained |

List operations were requested with `limit=5` where specified by the schema.
Player and club tags came from existing fixtures. End-to-end tests additionally
exercise country rankings for `DE` and brawler `16000054`.

The freshly retrieved specification describes the same eleven GET operations
as the checked-in specification. `/gamemodes` was the only missing wrapper
operation before this migration; all eleven now have implementations. No
endpoints were removed because every documented route worked. This audit does
not establish availability of undocumented routes or every possible identifier.
Local schema corrections for localized strings, battle results, and undefined
List references remain necessary and were preserved.

## Provenance and sampling

- The eleven successful endpoint fixtures were captured independently from real
  proxy responses during this audit, not generated from Java model serialization.
- Every list, including nested lists, is limited to its first five entries.
  Object fields, nulls, empty lists, totals, ranks, and cursors are preserved on
  sampled objects. Counts and cursors describe the provider response and are not
  recalculated to match truncated nested lists.
- Old battle timestamps remain in responses for the sampled inactive player.
  Fetching a response today does not imply that its embedded data is recent.
  The proxy's upstream caching and freshness were not independently verified.
- `gamemode-findAll.json` now contains a real response, replacing the earlier
  synthetic example. `gamemode-findAll-empty.json` remains a synthetic empty-list
  contract example.

The default tests replay fixtures with WireMock and check request paths, query
parameters, Bearer headers, raw responses, and preservation of every non-null
fixture field. They do not contact Supercell. The game-mode integration tests
cover populated/empty responses, pagination, and connector failures.

The opt-in `endToEnd` profile uses the configured proxy and an uncached connector.
Provide credentials through `API_KEY` and run `mvn -PendToEnd test`;
`-DapiUrl=...` optionally overrides the endpoint. Credentials must permit the
proxy's outbound IP (`45.79.218.79` at this audit).
