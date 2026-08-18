# Example Jenkins Shared Library Repository

This directory represents a Jenkins Shared Library repository.

The example demonstrates:

- a pipeline-facing global step under `vars/`
- a reusable Groovy helper under `src/`
- passing Jenkins Pipeline context into a helper class
- parameter validation
- public API considerations

## Claude configuration

`CLAUDE.md` contains repository-level instructions emphasizing:

- backward compatibility
- public API safety
- blast-radius awareness
- targeted context usage
- proportional validation
- minimal commentary

`.claude/rules/shared-library.md` contains rules specifically for Jenkins Shared Library Groovy source.

## Using these files

Copy the following into a real Jenkins Shared Library repository:

- `CLAUDE.md`
- `.claude/rules/shared-library.md`

Then customize them for the library's actual:

- public steps
- test commands
- lint commands
- supported Jenkins versions
- plugin requirements
- release/versioning process
- compatibility requirements

The example `vars/` and `src/` files are demonstrations only.

Do not duplicate large architecture documents inside `CLAUDE.md` when that information already exists elsewhere.