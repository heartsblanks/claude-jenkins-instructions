# Example Jenkins Pipeline Repository

This directory represents an application repository using Jenkins Scripted Pipeline.

The sample demonstrates:

- source checkout
- Jenkins Shared Library usage
- application build
- optional tests
- artifact packaging
- Jenkins credentials
- deployment
- cleanup using `finally`

## Claude configuration

`CLAUDE.md` contains repository-level instructions controlling how Claude approaches changes.

`.claude/rules/jenkinsfile.md` contains Jenkins/Groovy-specific rules.

The separation keeps general repository instructions and Jenkins-specific implementation concerns organized.

## Using these files

Copy the following into a real Jenkins pipeline repository:

- `CLAUDE.md`
- `.claude/rules/jenkinsfile.md`

Then customize the instructions for the repository's actual:

- build commands
- test commands
- validation commands
- agent labels
- shared libraries
- deployment process
- credential conventions

The included Jenkinsfile is an example only.

Do not copy its deployment command, credential ID, agent label, or library name into production without adapting them.