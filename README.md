# Claude Instructions for Jenkins

Reference templates for using Claude Code efficiently and safely with Jenkins Scripted Pipeline and Jenkins Shared Library repositories.

This repository contains two independent examples:

- `pipeline-repo/` — application repository using Jenkins Scripted Pipeline
- `shared-library-repo/` — Jenkins Shared Library repository

They intentionally use separate `CLAUDE.md` and `.claude/rules/` files because pipeline repositories and shared-library repositories have different responsibilities and risk profiles.

## Structure

```text
.
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
    ├── vars/
    │   └── buildApplication.groovy
    ├── src/
    │   └── org/example/
    │       └── PipelineUtils.groovy
    └── .claude/
        └── rules/
            └── shared-library.md