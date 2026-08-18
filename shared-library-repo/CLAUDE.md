# Jenkins Shared Library Repository Instructions

## Repository Purpose

This repository contains reusable Jenkins Shared Library code consumed by multiple Jenkins pipelines.

Shared-library changes can affect many downstream repositories.

Treat pipeline-facing steps and reusable interfaces as public APIs.

Backward compatibility and blast-radius control are therefore especially important.

## Core Principles

Optimize for:

1. correctness
2. backward compatibility
3. minimal blast radius
4. minimal unnecessary context
5. proportional validation

Make the smallest change that fully satisfies the request.

Do not refactor unrelated APIs or implementation.

## Working Method

For normal tasks:

1. Identify the affected public step, helper, or class.
2. Search for its definition and usages.
3. Determine whether the change affects a public interface.
4. Read only directly related implementation and tests.
5. Reuse existing patterns.
6. Implement the smallest compatible change.
7. Validate affected callers and behavior.
8. Review the final diff.
9. Stop.

Prefer:

search → targeted read → compatibility check → minimal change → targeted validation

over:

broad exploration → redesign → unrelated refactoring → exhaustive validation

## Planning

Do not create detailed plans for localized changes.

For straightforward tasks:

inspect → compatibility check → implement → validate

For changes affecting multiple public APIs or consumers, form a short internal plan.

Do not output it unless requested or clarification is required.

## Context and Cost Efficiency

Minimize unnecessary repository exploration.

- Search for the affected public step or method first.
- Search for callers before broad implementation reading.
- Read only directly relevant classes and tests.
- Do not inspect unrelated library modules.
- Do not repeatedly analyze code already understood.
- Reuse information already gathered.
- Stop when sufficient context exists to implement safely.

Compatibility searches are required for public API changes even when the implementation change is small.

Correctness and compatibility take priority over token savings.

## Public API Safety

Treat pipeline-facing code under `vars/` as public API unless clearly documented otherwise.

Before changing a public step:

1. search available usages
2. inspect parameter names and defaults
3. determine whether callers depend on return values
4. determine whether callers depend on failure behavior
5. preserve compatibility where possible

Do not casually:

- rename public steps
- remove parameters
- make optional parameters mandatory
- change parameter semantics
- change return types
- change failure semantics

Prefer additive, backward-compatible changes when practical.

## Existing Patterns First

Before adding a new shared-library helper:

1. search `vars/`
2. search `src/`
3. search existing usages
4. determine whether suitable functionality already exists

Prefer extending existing functionality when it preserves a clear API.

Do not create duplicate helpers for behavior already supported.

## `vars/` and `src/`

Follow Jenkins Shared Library conventions.

Use `vars/` for pipeline-facing global steps and DSL entry points.

Use `src/` for reusable Groovy classes and helpers where appropriate.

Do not move code between `vars/` and `src/` without a clear reason.

Keep public pipeline-facing APIs small and understandable.

## CPS and Serialization

Shared-library code executes in Jenkins Pipeline CPS contexts.

Pay particular attention to:

- closures
- serialization
- Jenkins steps inside helpers
- objects retained across suspension points
- mutable state
- static state
- iteration helpers
- CPS/non-CPS boundaries

Do not introduce `@NonCPS` unless necessary and its implications are understood.

Never call Jenkins Pipeline steps from an `@NonCPS` method.

Avoid retaining non-serializable objects across suspension points.

## Groovy and Jenkins Safety

Pay attention to:

- Groovy dynamic typing
- map parameters
- default values
- Jenkins step availability
- CPS transformation
- closures
- exception propagation
- environment variables
- credentials
- shell quoting

Do not guess Jenkins/plugin APIs, method parameters, or return types.

Verify unfamiliar behavior using existing repository usage or relevant documentation.

## State

Avoid hidden mutable global state.

Be cautious with:

- static mutable fields
- singleton-style state
- cross-build state
- mutable objects retained across pipeline suspension
- mutation of caller-provided maps

Prefer explicit inputs and outputs.

## Shell Commands

When shared-library helpers execute shell commands:

- preserve caller-visible failure behavior
- preserve return semantics
- preserve `returnStdout` and `returnStatus`
- avoid secret interpolation
- quote shell variables appropriately

Do not silently suppress failures.

## Error Handling

Preserve the public failure contract.

Do not catch broad exceptions merely to make a step appear successful.

If callers depend on an exception or non-zero shell status, preserve that behavior unless the requirement explicitly changes it.

## Security

Never expose Jenkins secrets.

Do not:

- log credentials
- hard-code credentials
- echo tokens
- interpolate secrets into visible output
- weaken existing credential handling

Preserve established Jenkins credential mechanisms.

## Scope Control

Do not:

- refactor unrelated library steps
- redesign APIs without requirement
- reorganize package structures incidentally
- rename unrelated public methods
- reformat entire files
- upgrade dependencies incidentally
- alter credential behavior without requirement
- modify downstream behavior outside requested scope

If an unrelated issue creates substantial compatibility risk, report it rather than automatically fixing it.

## Tests and Validation

Shared-library changes require compatibility-aware validation.

After changing a public step:

1. inspect available callers
2. check parameter compatibility
3. check return behavior
4. check failure behavior
5. review CPS implications
6. run the narrowest relevant tests
7. inspect the final diff

Broader validation is justified when a widely reused public API changes.

Do not trigger production jobs or deployments merely for validation.

Do not modify tests simply to make a failure pass.

## Dependency Discipline

Do not add or upgrade dependencies unless required.

Prefer existing repository utilities and standard Jenkins/Groovy capabilities.

## Diff Discipline

Before finishing:

- inspect every changed line
- remove temporary debugging code
- remove accidental formatting changes
- remove unused code introduced by the change
- ensure unrelated APIs were not modified

Keep changes surgical.

## Git Discipline

Do not commit, push, merge, rebase, force-push, create pull requests, or modify branches unless explicitly requested.

Preserve unrelated existing changes.

## Output and Commentary

Keep commentary minimal.

Do not narrate routine repository exploration.

Do not provide step-by-step reasoning.

Do not repeatedly announce what you are about to do.

Prefer tool actions over commentary.

Provide commentary only for:

- clarification
- compatibility concerns
- blockers
- meaningful risk
- unexpected findings that materially change implementation

Do not repeatedly summarize the same change.

## Final Response

Keep the final response concise.

Include only:

- what changed
- affected public API, if any
- files changed
- validation performed
- compatibility risk or assumption, if any

For small changes, use no more than 3–5 bullets.

Do not include detailed walkthroughs or unrelated recommendations.

## Stop Condition

Stop when:

1. requested behavior is implemented
2. caller compatibility has been checked
3. relevant validation is complete
4. final diff is focused
5. no required work remains

Do not continue into optional cleanup or API redesign.