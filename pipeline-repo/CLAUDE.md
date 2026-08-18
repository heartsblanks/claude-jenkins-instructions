---

# Pipeline repository

## 2. `pipeline-repo/CLAUDE.md`

```markdown
# Jenkins Pipeline Repository Instructions

## Repository Purpose

This repository contains application code and Jenkins CI/CD orchestration.

The pipeline uses Jenkins Scripted Pipeline with Groovy unless existing code explicitly shows otherwise.

The Jenkinsfile may consume functionality from a separate Jenkins Shared Library.

Treat shared-library methods as external reusable APIs unless their source exists in this repository.

## Core Principles

Optimize for:

1. correctness
2. minimal scope
3. minimal unnecessary context
4. pipeline safety
5. proportional validation

Make the smallest change that fully satisfies the request.

Preserve existing behavior outside the requested scope.

Do not refactor, reformat, clean up, or improve unrelated code.

## Working Method

For normal tasks:

1. Identify the affected file, stage, function, parameter, or shared-library call.
2. Search for relevant definitions and usages.
3. Read only the surrounding code needed to understand the behavior.
4. Reuse existing patterns and helpers.
5. Implement the smallest correct change.
6. Validate the affected behavior.
7. Review the final diff.
8. Stop.

Prefer:

search → targeted read → change → targeted validation

over:

broad exploration → large analysis → refactor → full validation

Do not inspect unrelated areas "just in case."

If an ambiguity would materially change the implementation, ask for clarification.

## Planning

Do not produce detailed plans for simple or localized changes.

For straightforward tasks:

inspect → implement → validate

For complex changes spanning multiple files, stages, or interfaces, form a short internal plan.

Do not output the plan unless requested or clarification is required.

## Context and Cost Efficiency

Minimize unnecessary context without sacrificing correctness.

- Search before reading large files.
- Read only files relevant to the task.
- Prefer targeted sections of large files.
- Do not repeatedly read code already understood unless it may have changed.
- Do not explore unrelated modules.
- Do not inspect unrelated Jenkins stages.
- Do not repeatedly analyze the same code.
- Reuse information already gathered.
- Stop investigating when enough information exists to implement safely.

Avoid reading generated or dependency content unless required.

Do not create documentation, diagrams, reports, abstractions, cleanup changes, or unrelated tests unless requested or necessary.

Correctness takes priority over token savings.

## Repository Guidance

Before a non-trivial change, identify relevant existing guidance such as:

- README
- architecture documentation
- contribution guidelines
- build configuration
- test configuration

Read only documentation relevant to the task.

Do not load large architecture documents automatically for small localized changes.

## Scope Control

Stay strictly within the requested behavior.

Do not:

- fix unrelated issues
- perform opportunistic refactoring
- rename unrelated variables
- reorganize unrelated code
- reformat entire files
- upgrade dependencies incidentally
- change agents without requirement
- change deployment behavior unless requested
- change shared-library interfaces without requirement
- introduce dependencies when existing functionality is sufficient

If an unrelated issue creates meaningful risk to the requested change, mention it briefly instead of fixing it automatically.

## Existing Patterns First

Before creating new code:

1. search for an existing implementation
2. search for similar usage
3. reuse existing helpers where appropriate
4. prefer extending established patterns over parallel implementations

Do not guess APIs, methods, parameters, plugin behavior, configuration, or return types.

Verify unfamiliar functionality from existing repository usage or relevant documentation.

## Jenkins Pipeline

Preserve Jenkins Scripted Pipeline semantics.

Do not convert Scripted Pipeline to Declarative Pipeline unless explicitly requested.

Pay particular attention to:

- Groovy variable scope
- Jenkins CPS behavior
- serialization
- closures
- Jenkins steps inside closures
- environment variables
- credentials
- shell quoting and interpolation
- `sh` return values
- `returnStdout`
- exception handling
- `try/catch/finally`
- build result handling
- parallel execution
- workspace behavior
- shared-library calls

Do not introduce `@NonCPS` unless necessary and its implications are understood.

## Shared Library Usage

Treat shared-library methods as public APIs.

Before changing a shared-library invocation:

1. search existing usages
2. verify parameter names and patterns
3. preserve established interfaces unless explicitly changing them

Do not invent shared-library methods or parameters.

If the shared-library implementation is not present, do not speculate about its internals.

## Shell Commands

Treat shell execution as a boundary between Groovy and the operating system.

When modifying shell commands:

- preserve quoting
- distinguish Groovy interpolation from shell interpolation
- avoid exposing secrets
- preserve exit-code behavior
- preserve stdout/stderr behavior
- preserve `returnStatus` and `returnStdout` semantics
- quote paths and variables where appropriate

Do not add `|| true` or otherwise suppress legitimate failures merely to make the pipeline green.

## Failure Semantics

Do not hide failures.

When changing:

- `try/catch/finally`
- `catchError`
- `warnError`
- `error`
- `currentBuild.result`
- shell exit handling

preserve intended stage and build behavior.

A successful-looking build is not a substitute for correct execution.

## Security

Never expose credentials or secrets.

Do not:

- print credentials
- echo tokens
- hard-code passwords
- hard-code API keys
- hard-code authentication tokens
- expose secrets through command output
- expose Jenkins credentials through unsafe interpolation

Use existing Jenkins credential mechanisms.

Do not modify credential configuration or permissions unless explicitly requested.

## Environment Assumptions

Do not assume the presence of:

- executables
- Jenkins plugins
- Docker
- particular agents
- credentials
- environment variables
- filesystem layouts

unless established by repository configuration or documentation.

Do not change the execution environment merely to simplify implementation.

## Validation

Use proportional validation.

After making a change:

1. review changed code
2. check affected references
3. check Groovy/Jenkins semantics when relevant
4. inspect shell quoting/interpolation when changed
5. verify credential handling when relevant
6. run the narrowest relevant available validation
7. inspect the final diff

Run broader validation only when the change can reasonably affect broader functionality.

Do not trigger production jobs, deployments, releases, or other external side effects unless explicitly requested.

If validation cannot be performed, state that concisely.

## Test Discipline

Do not change tests merely to make failing behavior pass.

When a test fails:

1. determine whether the implementation is wrong
2. determine whether the failure is unrelated
3. change the test only if the requested behavior legitimately changes its expectation

Do not weaken assertions or disable validation without justification.

## Diff Discipline

Before finishing, inspect the final diff.

Verify:

- every changed line contributes to the request
- accidental formatting changes are removed
- temporary debugging code is removed
- temporary files are removed
- unused code introduced by the change is removed
- unrelated files were not modified

Do not rewrite an entire file when a localized edit is sufficient.

## Git Discipline

Do not commit, push, merge, rebase, force-push, create pull requests, or modify branches unless explicitly requested.

Do not discard unrelated existing changes.

Treat unrelated modifications as user-owned.

## Output and Commentary

Default to no commentary during routine implementation.

Do not narrate:

- searches
- file reads
- repository exploration
- successful commands
- reference checks
- straightforward edits
- routine validation

Do not provide:
- progress updates
- step-by-step reasoning
- repeated summaries
- statements of intent such as "I will now..." or "Next I will..."
- commentary merely confirming that a normal action succeeded

Prefer silent tool execution.

Commentary is permitted only when:
- clarification from the user is required
- a material ambiguity could change the implementation
- a blocker prevents progress
- a requested operation carries meaningful risk
- an unexpected result requires changing the approach

When commentary is necessary, keep it to the minimum needed to continue safely.

## Final Response

Keep the final response concise.

Include only:
- what changed
- files changed
- validation performed
- unresolved issue or assumption, if any

For small changes, use no more than 3–5 bullets.

Do not include:
- step-by-step reasoning
- implementation narration
- repository summaries
- repeated explanations
- large code excerpts already visible in the diff
- unrelated recommendations
- optional next steps unless requested

If the task is complete and validated, report completion briefly and stop.

## Stop Condition

Stop when:

1. requested behavior is implemented
2. relevant changes have been reviewed
3. proportional validation is complete
4. no required work remains

Do not continue exploring for optional improvements.

Do not perform optional cleanup or optimization unless requested.