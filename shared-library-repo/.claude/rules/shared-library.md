---
paths:
  - "vars/**/*.groovy"
  - "src/**/*.groovy"
---

# Jenkins Shared Library Rules

## Public API

Treat pipeline-facing `vars/*.groovy` steps as public APIs.

Before modifying one:

- locate available callers
- preserve existing parameters and defaults where possible
- preserve return behavior
- preserve error behavior
- avoid breaking existing Jenkinsfiles

Do not rename or remove a public step without explicit requirement.

## Blast Radius

Shared-library code may be consumed by many repositories.

A small implementation change can have significant downstream impact.

Prefer backward-compatible and localized changes.

Check callers before modifying public behavior.

## CPS Safety

Review changes for:

- serializable state
- closure capture
- Jenkins steps inside helpers
- CPS/non-CPS boundaries
- objects retained across suspension points

Do not add `@NonCPS` unless necessary.

Never invoke Jenkins Pipeline steps from `@NonCPS` methods.

## `vars/`

Keep global pipeline-facing steps straightforward.

Prefer thin orchestration and explicit parameters over hidden state.

Treat step names and parameter contracts as public interfaces.

## `src/`

Use `src/` for reusable Groovy helper classes.

Do not assume ordinary Groovy classes automatically have access to Jenkins Pipeline steps.

Pass required pipeline/script context explicitly when necessary.

## State

Avoid:

- static mutable state
- cross-build state
- hidden global mutation
- unnecessary mutation of caller-provided maps

Prefer explicit inputs and outputs.

## Shell and Credentials

When wrapping shell execution:

- preserve exit semantics
- preserve return semantics
- avoid secret interpolation
- quote shell variables
- do not suppress legitimate failures

Use existing Jenkins credential mechanisms.

## Compatibility

Before changing a public API, determine whether the change affects:

- existing callers
- parameter defaults
- optional parameters
- return values
- exceptions
- build failure behavior

Prefer additive changes over breaking changes.

## Validation

Caller compatibility checks are part of required validation for public API changes.

Prefer targeted validation.

Run broader validation when the public API's blast radius warrants it.

Do not trigger production jobs merely for validation.