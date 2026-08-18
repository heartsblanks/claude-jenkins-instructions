---
paths:
  - "Jenkinsfile"
  - "Jenkinsfile.*"
  - "**/*.groovy"
---

# Jenkins Scripted Pipeline Rules

## Pipeline Type

This repository uses Jenkins Scripted Pipeline with Groovy.

Do not convert working Scripted Pipeline constructs to Declarative Pipeline unless explicitly requested.

Preserve existing:

- node structure
- stages
- closures
- parameters
- agents
- environment handling
- credentials handling
- shared-library calls
- failure behavior

## Surgical Changes

For pipeline changes:

1. locate the affected stage or helper
2. inspect directly related code
3. inspect relevant usages
4. modify only required behavior
5. perform targeted validation
6. stop

Do not inspect unrelated stages unless required to understand a dependency.

## Shared Library Calls

Before changing a shared-library call:

- search existing usages
- preserve parameter names and types
- preserve established calling patterns
- do not invent undocumented parameters
- avoid changing public contracts unless required

If implementation is unavailable, do not guess its internals.

## CPS Safety

Be cautious with:

- closures
- closure capture
- iterators
- mutable state
- serialization
- Jenkins steps inside helpers
- objects surviving pipeline suspension

Do not introduce `@NonCPS` merely to bypass a problem.

Never move Jenkins Pipeline steps into `@NonCPS` code.

## Shell

For `sh`:

- distinguish Groovy interpolation from shell interpolation
- preserve exit behavior
- preserve `returnStatus`
- preserve `returnStdout`
- avoid leaking credentials
- quote variables appropriately

Do not suppress legitimate failures with `|| true`.

## Credentials

Use existing Jenkins credential mechanisms.

Never expose secrets through:

- Groovy interpolation
- shell output
- debugging
- generated command strings

Do not change credential IDs unless required.

## Failure Behavior

Preserve intended behavior of:

- `error`
- `catchError`
- `warnError`
- exceptions
- `currentBuild.result`
- shell exit codes

Do not turn genuine failures into successful builds.

## Parallel Execution

When changing `parallel`, inspect:

- closure capture
- shared mutable state
- workspace conflicts
- concurrent file writes
- environment modifications
- artifact-name collisions

Do not introduce parallel execution merely as an optimization.

## Environment

Do not assume:

- plugin availability
- binary availability
- agent operating system
- Docker availability
- credentials
- filesystem layout
- environment variables

unless established by the repository.

## Validation

Prefer targeted checks over broad unrelated builds.

Check:

- Groovy syntax
- Scripted Pipeline semantics
- changed shared-library calls
- shell quoting
- credential handling
- failure behavior

Never trigger a production deployment solely to validate a pipeline edit.