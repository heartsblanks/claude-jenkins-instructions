Paste the following as a single task into OpenAI Codex while it is connected to your existing repository:

heartsblanks/claude-jenkins-instructions

I’ve adjusted it so the sample pipeline repo and sample shared-library repo each get their own repo-specific CLAUDE.md and rules, rather than sharing one generic Jenkins file. Anthropic documents root CLAUDE.md as project memory/instructions, and nested CLAUDE.md files can apply when Claude works in those subtrees. 

Work in the existing GitHub repository:
heartsblanks/claude-jenkins-instructions
Goal:
Create a reference repository demonstrating cost-efficient, safe, concise Claude Code instructions for two distinct Jenkins repository types:
1. a Jenkins Scripted Pipeline repository
2. a Jenkins Shared Library repository
IMPORTANT:
These must be treated as two separate example repositories with different responsibilities.
Do NOT use one shared CLAUDE.md for both examples.
Each sample repository must contain:
- its own CLAUDE.md
- its own .claude/rules/ file(s)
- realistic example source code
- a short README explaining how to copy/use the instructions in a real repository
The root repository itself is only a reference/examples repository.
Do not add an unnecessary root CLAUDE.md.
Create the following structure exactly:
claude-jenkins-instructions/
├── README.md
├── pipeline-repo/
│   ├── CLAUDE.md
│   ├── README.md
│   ├── Jenkinsfile
│   └── .claude/
│       └── rules/
│           └── jenkinsfile.md
└── shared-library-repo/
    ├── CLAUDE.md
    ├── README.md
    ├── .claude/
    │   └── rules/
    │       └── shared-library.md
    ├── vars/
    │   └── buildApplication.groovy
    └── src/
        └── org/
            └── example/
                └── PipelineUtils.groovy
============================================================
ROOT README.md
============================================================
Create README.md with this content:
# Claude Instructions for Jenkins
Reference examples for configuring Claude Code efficiently and safely for Jenkins development.
This repository contains two independent examples:
- `pipeline-repo/` — Jenkins Scripted Pipeline repository
- `shared-library-repo/` — Jenkins Shared Library repository
They intentionally use separate `CLAUDE.md` and `.claude/rules/` files because pipeline repositories and shared-library repositories have different responsibilities and risk profiles.
## Why separate instructions?
A pipeline repository usually owns:
- Jenkinsfile orchestration
- application build stages
- parameters
- agents
- environment configuration
- calls into shared libraries
- application-specific CI/CD behavior
A shared-library repository usually owns reusable Jenkins APIs consumed by multiple pipelines.
Changes to a shared library can therefore have a larger blast radius and require additional attention to:
- backward compatibility
- public step signatures
- CPS compatibility
- shared state
- serialization
- caller impact
- reusable API behavior
Keeping the instructions repository-specific also reduces unnecessary Claude context.
## Cost-efficiency principles
The examples instruct Claude to prefer:
search → targeted read → minimal change → targeted validation → stop
instead of:
broad repository exploration → large analysis → unrelated refactoring → exhaustive validation
The instructions also discourage unnecessary progress commentary, repeated summaries, and exploration after the task is complete.
Correctness always takes priority over token savings.
## Usage
For a Jenkins pipeline repository, copy:
- `pipeline-repo/CLAUDE.md`
- `pipeline-repo/.claude/rules/jenkinsfile.md`
into the corresponding locations in the real pipeline repository.
For a Jenkins Shared Library repository, copy:
- `shared-library-repo/CLAUDE.md`
- `shared-library-repo/.claude/rules/shared-library.md`
into the corresponding locations in the real shared-library repository.
Then adapt repository-specific build, test, lint, deployment, credential, and shared-library conventions.
Do not blindly copy application-specific commands from these examples.
## Important principle
`CLAUDE.md` should primarily describe how Claude should work in that repository.
Detailed architecture that already exists in README files or architecture documentation should generally remain there instead of being duplicated into `CLAUDE.md`.
============================================================
PIPELINE REPOSITORY
pipeline-repo/CLAUDE.md
============================================================
Create pipeline-repo/CLAUDE.md with:
# Jenkins Pipeline Repository Instructions
## Repository Purpose
This repository contains Jenkins pipeline orchestration for an application.
The pipeline uses Jenkins Scripted Pipeline with Groovy unless the existing code explicitly shows otherwise.
The Jenkinsfile may consume functionality from a separate Jenkins Shared Library.
Treat the shared library as an external reusable API unless its source is part of this repository.
---
## Core Principles
Optimize for:
1. correctness
2. minimal scope
3. minimal unnecessary context usage
4. pipeline safety
5. proportional validation
Make the smallest change that fully satisfies the request.
Preserve existing pipeline behavior outside the requested scope.
Do not refactor, reformat, clean up, or improve unrelated pipeline code.
---
## Working Method
For normal tasks:
1. Identify the affected stage, function, parameter, or shared-library call.
2. Search for relevant usages before reading large files.
3. Read only the surrounding code necessary to understand the behavior.
4. Reuse existing pipeline patterns.
5. Implement the smallest correct change.
6. Validate the affected behavior.
7. Review the final diff.
8. Stop.
Prefer:
search → targeted read → change → targeted validation
over:
broad exploration → large analysis → refactor → full validation
Do not inspect unrelated stages "just in case."
---
## Planning Discipline
Do not create detailed plans for simple or localized Jenkinsfile changes.
For straightforward tasks:
inspect → implement → validate
For changes spanning multiple stages, execution environments, or shared-library interfaces, form a short internal plan first.
Do not output the plan unless requested or clarification is required.
---
## Context and Cost Efficiency
Minimize unnecessary context consumption without sacrificing correctness.
- Search before reading large files.
- Read only relevant Jenkinsfile sections and directly related files.
- Do not repeatedly read code already understood unless it may have changed.
- Do not inspect unrelated application source unless required to understand the pipeline behavior.
- Do not explore the shared-library implementation unless it exists locally or the task specifically requires it.
- Reuse information already gathered during the task.
- Stop investigating when enough information exists to implement safely.
Do not create documentation, diagrams, cleanup changes, abstractions, or unrelated tests unless required.
Correctness takes priority over token savings.
---
## Scope Control
Stay strictly within the requested pipeline behavior.
Do not:
- fix unrelated stages
- rename unrelated variables
- reorganize the Jenkinsfile
- convert Scripted Pipeline to Declarative Pipeline
- reformat the entire Jenkinsfile
- upgrade plugins or dependencies incidentally
- change agents or execution environments without requirement
- modify deployment behavior unless requested
- alter shared-library interfaces without requirement
If an unrelated problem creates meaningful risk to the requested change, mention it briefly rather than automatically fixing it.
---
## Scripted Pipeline Requirement
Treat pipeline code as Jenkins Scripted Pipeline unless the repository clearly indicates otherwise.
Do not convert working Scripted Pipeline constructs into Declarative Pipeline syntax.
Preserve existing:
- `node`
- `stage`
- closures
- helper methods
- shared-library calls
- environment handling
- credentials handling
- build result behavior
---
## Shared Library Usage
Treat shared-library methods as public APIs.
Before changing how a shared-library method is called:
1. search for existing usages in this repository
2. confirm the existing parameter pattern
3. preserve established interfaces unless the requirement explicitly changes them
Do not invent shared-library methods or parameters.
If the shared-library implementation is not available in this repository, do not speculate about its internals.
Base changes on the public usage visible here.
---
## Jenkins/Groovy Safety
Pay particular attention to:
- Groovy variable scope
- Jenkins CPS behavior
- closures
- serialization
- environment variables
- Jenkins credentials
- shell quoting
- Groovy versus shell interpolation
- `sh` return values
- `returnStdout`
- exception handling
- `try/catch/finally`
- `currentBuild.result`
- parallel execution
- workspace behavior
Do not introduce `@NonCPS` unless necessary and its consequences are understood.
---
## Shell Commands
Treat shell execution as a boundary between Groovy and the operating system.
When modifying `sh` commands:
- preserve quoting carefully
- distinguish Groovy interpolation from shell interpolation
- avoid exposing secrets
- preserve exit-code behavior
- preserve stdout capture behavior
- quote paths and variables where appropriate
- consider trailing newlines from `returnStdout`
Do not add `|| true` or otherwise suppress a real failure merely to make the pipeline green.
---
## Failure Semantics
Do not hide Jenkins failures.
When modifying:
- `try/catch/finally`
- `catchError`
- `warnError`
- `error`
- `currentBuild.result`
- shell exit handling
preserve the intended stage and build outcome.
A successful-looking Jenkins build is not a substitute for correct execution.
---
## Credentials and Security
Never expose secrets.
Do not:
- print credentials
- echo tokens
- interpolate secrets into logs
- hard-code passwords
- hard-code API keys
- hard-code tokens
- expose secret values through debug output
Use the existing Jenkins credential mechanism.
Do not change credential IDs or credential configuration unless explicitly requested.
---
## Environment Assumptions
Do not assume the presence of:
- executables
- Jenkins plugins
- Docker
- particular agents
- environment variables
- credentials
- filesystem paths
unless established by the Jenkinsfile or project documentation.
Do not change agent selection or runtime environment merely to simplify implementation.
---
## Validation
Use proportional validation.
After changing the Jenkinsfile:
1. inspect the changed Groovy syntax
2. check Scripted Pipeline semantics
3. inspect affected shared-library calls
4. inspect shell quoting/interpolation when changed
5. verify credentials handling when relevant
6. run the narrowest available validation
7. inspect the final diff
Do not trigger production Jenkins jobs, deployments, releases, or external side effects unless explicitly requested.
If full Jenkins runtime validation is unavailable, state that concisely.
---
## Diff Discipline
Before finishing:
- inspect the final diff
- ensure every changed line contributes to the request
- remove accidental formatting changes
- remove temporary debugging code
- remove unused variables introduced by the change
- ensure unrelated files were not changed
Do not rewrite the whole Jenkinsfile when a localized edit is sufficient.
---
## Git Discipline
Do not commit, push, merge, rebase, force-push, create pull requests, or modify branches unless explicitly requested.
Do not discard unrelated existing user changes.
---
## Output and Commentary
Keep commentary minimal.
Do not narrate routine actions such as:
- searching
- opening files
- reading Jenkinsfile sections
- running normal commands
- checking references
- making straightforward edits
Do not provide step-by-step reasoning.
Do not repeatedly announce what you are about to do.
Prefer tool actions over commentary.
Provide commentary only when:
- clarification is required
- an important ambiguity exists
- a blocker occurs
- an operation has meaningful risk
- an unexpected result materially changes the implementation
If a command succeeds and needs no explanation, continue without commentary.
---
## Final Response
Keep the final response concise.
Include only:
- what changed
- files changed
- validation performed
- unresolved issue or assumption, if any
For small changes, use no more than 3–5 bullets.
Do not provide:
- detailed walkthroughs
- step-by-step reasoning
- repository summaries
- large code excerpts already visible in the diff
- unrelated improvement suggestions
- repeated summaries
---
## Stop Condition
Stop when:
1. requested behavior is implemented
2. relevant changes have been reviewed
3. proportional validation is complete
4. no required work remains
Do not continue exploring for optional improvements after the requested task is complete.
============================================================
PIPELINE RULE
pipeline-repo/.claude/rules/jenkinsfile.md
============================================================
Create pipeline-repo/.claude/rules/jenkinsfile.md with:
---
paths:
  - "Jenkinsfile"
  - "Jenkinsfile.*"
  - "**/*.groovy"
---
# Jenkinsfile Rules
These rules apply when modifying pipeline Groovy code.
## Preserve Scripted Pipeline
This repository uses Jenkins Scripted Pipeline.
Do not convert it to Declarative Pipeline unless explicitly requested.
Preserve existing stage and node structure where possible.
## Make Surgical Changes
For Jenkinsfile tasks:
1. locate the affected stage or helper
2. inspect directly related calls
3. modify only the required behavior
4. validate the changed path
5. stop
Do not scan unrelated stages unless needed to understand dependencies.
## Shared Library Calls
Before changing a shared-library invocation:
- search existing calls
- preserve parameter names and types
- do not invent undocumented parameters
- avoid changing the public contract unless explicitly required
## Jenkins CPS
Be cautious with:
- closures
- iterators
- mutable state captured by closures
- serialization
- Jenkins steps inside helpers
Do not add `@NonCPS` merely to resolve a compilation problem.
## Shell
For `sh`:
- distinguish Groovy interpolation from shell interpolation
- preserve failure behavior
- avoid leaking credentials
- preserve `returnStatus` and `returnStdout` semantics
- quote variables where appropriate
Do not suppress failures with `|| true` unless the behavior is explicitly intentional.
## Parallel
When changing `parallel`, inspect:
- shared mutable variables
- closure capture
- workspace conflicts
- concurrent writes
- environment changes
- artifact name collisions
Do not introduce parallelism merely as an optimization.
## Validation
Prefer targeted checks over broad unrelated builds.
Never trigger production deployment simply to validate a Jenkinsfile edit.
============================================================
PIPELINE SAMPLE
pipeline-repo/Jenkinsfile
============================================================
Create this realistic Scripted Pipeline example:
@Library('company-shared-library@main') _
properties([
    parameters([
        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'test'],
            description: 'Deployment target'
        ),
        booleanParam(
            name: 'RUN_TESTS',
            defaultValue: true,
            description: 'Run automated tests'
        )
    ])
])
node('linux') {
    try {
        stage('Checkout') {
            checkout scm
        }
        stage('Build') {
            buildApplication(
                application: 'sample-service',
                command: './gradlew clean assemble'
            )
        }
        if (params.RUN_TESTS) {
            stage('Test') {
                sh './gradlew test'
            }
        }
        stage('Package') {
            sh '''
                set -e
                mkdir -p build/package
                cp build/libs/*.jar build/package/
            '''
        }
        stage('Deploy') {
            withCredentials([
                string(
                    credentialsId: 'sample-deployment-token',
                    variable: 'DEPLOYMENT_TOKEN'
                )
            ]) {
                sh '''
                    set -e
                    ./scripts/deploy.sh \
                      --environment "$ENVIRONMENT" \
                      --token "$DEPLOYMENT_TOKEN"
                '''
            }
        }
        currentBuild.result = 'SUCCESS'
    } catch (Exception ex) {
        currentBuild.result = 'FAILURE'
        throw ex
    } finally {
        stage('Cleanup') {
            deleteDir()
        }
    }
}
============================================================
PIPELINE README
pipeline-repo/README.md
============================================================
Create:
# Example Jenkins Pipeline Repository
This directory represents an application repository containing a Jenkins Scripted Pipeline.
It demonstrates a pipeline that:
- checks out source
- calls a Jenkins Shared Library
- builds an application
- optionally runs tests
- packages an artifact
- uses Jenkins credentials for deployment
- performs cleanup in `finally`
## Claude instructions
`CLAUDE.md` defines repository-wide working behavior.
`.claude/rules/jenkinsfile.md` contains rules specifically relevant to Jenkins/Groovy pipeline files.
In a real repository, customize:
- build commands
- test commands
- agent labels
- deployment behavior
- credential IDs
- shared-library names
- validation commands
Do not treat this sample deployment script or credential ID as production guidance.
============================================================
SHARED LIBRARY
shared-library-repo/CLAUDE.md
============================================================
Create shared-library-repo/CLAUDE.md with:
# Jenkins Shared Library Repository Instructions
## Repository Purpose
This repository contains reusable Jenkins Shared Library code consumed by multiple Jenkins pipelines.
Shared-library changes may affect many downstream repositories.
Treat public steps and helper methods as reusable APIs.
Backward compatibility and blast-radius control are therefore especially important.
---
## Core Principles
Optimize for:
1. correctness
2. backward compatibility
3. minimal blast radius
4. minimal unnecessary context usage
5. proportional validation
Make the smallest change that fully satisfies the request.
Do not refactor unrelated library APIs or implementation.
---
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
search → targeted reads → compatibility check → minimal change → targeted validation
over:
broad exploration → redesign → unrelated refactoring → exhaustive validation
---
## Public API Safety
Anything under `vars/` may be called directly from Jenkinsfiles and should be treated as a public API unless clearly documented otherwise.
Before changing a public step:
1. search all usages available in the repository
2. inspect parameter names and defaults
3. determine whether callers depend on current return values or failure behavior
4. preserve compatibility where possible
Do not:
- rename public steps casually
- remove parameters casually
- change required/optional parameter behavior without requirement
- change return types without requirement
- change exception/failure semantics without requirement
Prefer additive, backward-compatible changes when practical.
---
## `vars/` and `src/`
Use Jenkins Shared Library conventions.
`vars/` should contain pipeline-facing global steps and reusable DSL entry points.
`src/` should contain reusable Groovy classes and helpers where appropriate.
Do not move code between `vars/` and `src/` without a clear reason.
Keep public pipeline-facing APIs small.
---
## Jenkins CPS and Serialization
Shared-library code executes in Jenkins Pipeline CPS contexts.
Pay particular attention to:
- closures
- serialization
- Jenkins steps invoked from helpers
- objects retained across pipeline suspension points
- mutable state
- static state
- iteration helpers
- CPS/non-CPS boundaries
Do not introduce `@NonCPS` unless necessary and its implications are understood.
Never call Jenkins Pipeline steps from an `@NonCPS` method.
Avoid retaining non-serializable objects across suspension points.
---
## Context and Cost Efficiency
Minimize unnecessary repository exploration.
- Search for the public step or method first.
- Search for callers before reading broad implementation.
- Read only directly relevant classes.
- Do not inspect unrelated shared-library modules.
- Do not repeatedly analyze code already understood.
- Stop when sufficient context exists to implement safely.
Because shared-library changes can affect multiple callers, compatibility searches are required even when the implementation edit itself is small.
Correctness and compatibility take priority over token savings.
---
## Scope Control
Do not:
- refactor unrelated shared-library steps
- redesign APIs without requirement
- reorganize package structures incidentally
- rename unrelated public methods
- format entire files
- upgrade dependencies incidentally
- alter credentials behavior without requirement
- modify downstream pipeline behavior outside the requested scope
If an unrelated issue creates substantial compatibility risk, report it rather than automatically fixing it.
---
## Groovy and Jenkins Safety
Pay attention to:
- Groovy dynamic typing
- map parameters
- default parameter behavior
- Jenkins step availability
- CPS transformation
- closures
- exception propagation
- environment variables
- shell quoting
- credentials
- serialization
Do not assume a plugin or Jenkins step exists unless established by existing usage or documentation.
---
## Shell Commands
For shared-library helpers wrapping `sh`:
- preserve caller-visible failure behavior
- preserve return values
- preserve `returnStdout` / `returnStatus`
- avoid Groovy interpolation of secrets
- quote shell variables appropriately
Do not silently suppress failures.
---
## State
Avoid hidden mutable global state.
Be cautious with:
- static mutable fields
- singleton-style state
- cross-build shared state
- mutation of caller-provided maps
Prefer explicit inputs and outputs.
---
## Error Handling
Preserve the public failure contract.
Do not catch broad exceptions merely to make a step appear successful.
If existing callers depend on an exception or non-zero shell status, preserve that behavior unless the requirement explicitly changes it.
---
## Tests and Validation
Shared-library changes require compatibility-aware validation.
After changing a public step:
1. inspect all available callers
2. check parameter compatibility
3. check return/failure behavior
4. review CPS implications
5. run the narrowest relevant tests
6. inspect the final diff
Broader validation is justified when a widely reused public API changes.
Do not trigger production jobs or deployments merely for validation.
---
## Dependency Discipline
Do not add or upgrade dependencies unless required.
Prefer standard Groovy/Jenkins capabilities and existing repository utilities.
---
## Security
Never expose Jenkins secrets.
Do not:
- log credentials
- hard-code credentials
- interpolate tokens into visible command output
- weaken credential handling
Preserve established Jenkins credential mechanisms.
---
## Diff Discipline
Before finishing:
- inspect every changed line
- remove temporary debugging code
- remove accidental formatting changes
- remove unused code introduced by the change
- ensure unrelated APIs were not modified
Keep changes surgical.
---
## Git Discipline
Do not commit, push, merge, rebase, force-push, create pull requests, or change branches unless explicitly requested.
Preserve unrelated existing changes.
---
## Output and Commentary
Keep commentary minimal.
Do not narrate routine repository exploration.
Do not provide step-by-step reasoning.
Prefer tool actions over commentary.
Provide commentary only for:
- clarification
- compatibility concerns
- blockers
- meaningful risk
- unexpected findings that change implementation
Do not repeatedly summarize the same change.
---
## Final Response
Keep the final response concise.
Include only:
- what changed
- affected public API, if any
- files changed
- validation performed
- compatibility risk or assumption, if any
For small changes, use no more than 3–5 bullets.
---
## Stop Condition
Stop when:
1. requested behavior is implemented
2. caller compatibility has been checked
3. relevant validation is complete
4. the final diff is focused
5. no required work remains
Do not continue into optional cleanup or API redesign.
============================================================
SHARED LIBRARY RULE
shared-library-repo/.claude/rules/shared-library.md
============================================================
Create:
---
paths:
  - "vars/**/*.groovy"
  - "src/**/*.groovy"
---
# Jenkins Shared Library Rules
## Public API
Treat `vars/*.groovy` pipeline steps as public APIs.
Before modifying one:
- locate callers
- preserve existing parameters/defaults where possible
- preserve return behavior
- preserve error behavior
- avoid breaking existing Jenkinsfiles
Do not rename or remove a public step without explicit requirement.
## Blast Radius
Shared-library code may be consumed by many repositories.
A small implementation change can have large downstream impact.
Prefer backward-compatible and localized changes.
## CPS Safety
Review changes for:
- serializable state
- closure capture
- Jenkins steps inside helpers
- CPS/non-CPS boundaries
Do not add `@NonCPS` unless necessary.
Never invoke Jenkins Pipeline steps from `@NonCPS` methods.
## `vars/`
Keep global pipeline-facing steps straightforward.
Prefer thin orchestration over large amounts of hidden state.
## `src/`
Use `src/` for reusable Groovy helper classes.
Do not assume ordinary Groovy classes automatically have access to Jenkins Pipeline steps.
Pass required pipeline/script context explicitly when necessary.
## State
Avoid static mutable state and cross-build state.
Do not mutate caller-provided maps unless that behavior is intentional and documented.
## Shell and Credentials
When wrapping shell execution:
- preserve exit semantics
- preserve return semantics
- avoid secret interpolation
- quote shell variables
- do not suppress legitimate failures
## Validation
When changing a public API, caller compatibility checks are part of required validation.
Run broader validation only when the blast radius warrants it.
============================================================
SHARED LIBRARY SAMPLE
shared-library-repo/vars/buildApplication.groovy
============================================================
Create:
def call(Map config = [:]) {
    String application = requireValue(config, 'application')
    String command = requireValue(config, 'command')
    echo "Building ${application}"
    sh(
        label: "Build ${application}",
        script: command
    )
}
private String requireValue(Map config, String key) {
    def value = config[key]
    if (value == null || value.toString().trim().isEmpty()) {
        error "Missing required parameter: ${key}"
    }
    value.toString()
}
============================================================
SHARED LIBRARY SAMPLE CLASS
shared-library-repo/src/org/example/PipelineUtils.groovy
============================================================
Create:
package org.example
class PipelineUtils implements Serializable {
    private final def steps
    PipelineUtils(def steps) {
        this.steps = steps
    }
    String normalizedEnvironment(String environment) {
        String value = environment?.trim()?.toLowerCase()
        if (!(value in ['dev', 'test', 'prod'])) {
            steps.error("Unsupported environment: ${environment}")
        }
        value
    }
    boolean fileExists(String path) {
        steps.fileExists(path)
    }
}
============================================================
SHARED LIBRARY README
shared-library-repo/README.md
============================================================
Create:
# Example Jenkins Shared Library Repository
This directory represents a Jenkins Shared Library repository.
It demonstrates:
- a pipeline-facing global step under `vars/`
- a reusable Groovy class under `src/`
- passing Jenkins step context into a helper class
- validating public step parameters
## Claude instructions
`CLAUDE.md` emphasizes:
- backward compatibility
- public API safety
- blast-radius awareness
- Jenkins CPS behavior
- targeted repository exploration
- concise output
`.claude/rules/shared-library.md` provides additional rules specifically for Groovy shared-library source.
## Real repositories
Adapt the example instructions to include actual:
- unit test commands
- lint commands
- Jenkins plugin requirements
- supported Jenkins versions
- public step conventions
- release/versioning process
Do not duplicate large architecture documents inside `CLAUDE.md` if they already exist elsewhere.
============================================================
IMPLEMENTATION REQUIREMENTS
============================================================
When implementing this task:
1. Confirm the target repository is:
   heartsblanks/claude-jenkins-instructions
2. Inspect the existing repository before editing.
3. Preserve any existing user content unless it conflicts directly with the requested structure.
4. Create all directories and files listed above.
5. Do not create unrelated files.
6. Do not add CI/CD workflows.
7. Do not add dependencies.
8. Do not create an actual nested `.git` repository inside either sample directory.
The directories `pipeline-repo/` and `shared-library-repo/` are examples of how two independent real repositories should look, not Git submodules and not nested Git repositories.
9. Validate:
   - expected files exist
   - Markdown is readable
   - YAML frontmatter in rule files is syntactically reasonable
   - sample Groovy code has balanced braces/parentheses
   - Jenkinsfile is clearly Scripted Pipeline
   - sample pipeline calls `buildApplication` consistently with the shared-library example
10. Review the final diff and remove accidental or unrelated changes.
11. Commit and push the changes to the repository if your current Codex GitHub environment is configured to do so.
Use commit message:
Add Claude Jenkins instruction examples
12. If direct push is not permitted, create the changes on a branch and open a pull request instead.
Suggested branch:
add-claude-jenkins-instructions
Suggested PR title:
Add Claude Jenkins instruction examples
13. Final response should be concise and report:
   - files created
   - validation performed
   - commit/PR status
   - any blocker
Do not redesign or expand the requested structure unless necessary.

One small note: I intentionally made the two CLAUDE.md files different rather than cloning the same generic text. The pipeline version optimizes around stages, deployment behavior and calls into a library; the shared-library version adds stronger requirements around backward compatibility and caller blast radius. That separation is exactly what you want if these files will eventually be copied into real independent repositories.

Anthropic also supports user-level memory at ~/.claude/CLAUDE.md, but for what you’re building here, keeping the important instructions at the repository level is preferable because they travel with the repository and describe its actual conventions. 